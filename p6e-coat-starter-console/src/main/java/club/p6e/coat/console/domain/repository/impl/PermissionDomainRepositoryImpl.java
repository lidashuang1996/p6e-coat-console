package club.p6e.coat.console.domain.repository.impl;

import club.p6e.coat.common.pagination.JpaPaginationConverter;
import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.console.domain.aggregate.PermissionAggregate;
import club.p6e.coat.console.domain.aggregate.PermissionUrlDetailsAggregate;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupDetailsAggregate;
import club.p6e.coat.console.domain.entity.PermissionEntity;
import club.p6e.coat.console.domain.entity.PermissionUrlEntity;
import club.p6e.coat.console.domain.entity.PermissionUrlGroupEntity;
import club.p6e.coat.console.domain.identifier.PermissionId;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;
import club.p6e.coat.console.domain.repository.PermissionDomainRepository;
import club.p6e.coat.console.infrastructure.model.PermissionUrlGroupModel;
import club.p6e.coat.console.infrastructure.model.PermissionUrlGroupRelationUrlModel;
import club.p6e.coat.console.infrastructure.model.PermissionUrlModel;
import club.p6e.coat.console.infrastructure.repository.PermissionUrlGroupRelationUrlRepository;
import club.p6e.coat.console.infrastructure.repository.PermissionUrlGroupRepository;
import club.p6e.coat.console.infrastructure.repository.PermissionUrlRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 权限领域存储库实现类
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PermissionDomainRepositoryImpl implements PermissionDomainRepository {

    /**
     * 权限 URL 存储库
     */
    private final PermissionUrlRepository permissionUrlRepository;

    /**
     * 权限 URL GROUP 存储库
     */
    private final PermissionUrlGroupRepository permissionUrlGroupRepository;

    /**
     * 权限 URL GROUP RELATION 存储库
     */
    private final PermissionUrlGroupRelationUrlRepository permissionUrlGroupRelationUrlRepository;

    /**
     * 构造方法初始化
     *
     * @param permissionUrlRepository                 权限 URL 存储库
     * @param permissionUrlGroupRepository            权限 URL GROUP 存储库
     * @param permissionUrlGroupRelationUrlRepository 权限 URL GROUP RELATION 存储库
     */
    public PermissionDomainRepositoryImpl(
            PermissionUrlRepository permissionUrlRepository,
            PermissionUrlGroupRepository permissionUrlGroupRepository,
            PermissionUrlGroupRelationUrlRepository permissionUrlGroupRelationUrlRepository
    ) {
        this.permissionUrlRepository = permissionUrlRepository;
        this.permissionUrlGroupRepository = permissionUrlGroupRepository;
        this.permissionUrlGroupRelationUrlRepository = permissionUrlGroupRelationUrlRepository;
    }

    @Override
    public PermissionEntity association(PermissionEntity pe) {
        final Optional<PermissionUrlModel> puo = permissionUrlRepository.findById(pe.getId().getUid());
        final Optional<PermissionUrlGroupModel> pgo = permissionUrlGroupRepository.findById(pe.getId().getGid());
        if (puo.isEmpty() || pgo.isEmpty()) {
            return null;
        } else {
            final PermissionUrlGroupRelationUrlModel model =
                    permissionUrlGroupRelationUrlRepository.saveAndFlush(pe.convertToModel());
            return new PermissionEntity(new PermissionId(model.getUid(), model.getGid()), model);
        }
    }

    @Override
    public PermissionEntity disassociate(PermissionEntity pe) {
        final Optional<PermissionUrlModel> puo = permissionUrlRepository.findById(pe.getId().getUid());
        final Optional<PermissionUrlGroupModel> pgo = permissionUrlGroupRepository.findById(pe.getId().getGid());
        if (puo.isEmpty() || pgo.isEmpty()) {
            return null;
        } else {
            final Optional<PermissionUrlGroupRelationUrlModel> pro =
                    permissionUrlGroupRelationUrlRepository.findById(
                            new PermissionUrlGroupRelationUrlModel.Key().setUid(puo.get().getId()).setGid(pgo.get().getId()));
            if (pro.isEmpty()) {
                return null;
            } else {
                final PermissionUrlGroupRelationUrlModel prm = pro.get();
                permissionUrlGroupRelationUrlRepository.delete(prm);
                return new PermissionEntity(new PermissionId(prm.getUid(), prm.getGid()), prm);
            }
        }
    }

    @Override
    public PermissionUrlDetailsAggregate urlDetails(PermissionUrlId id, PaginationContext pagination) {
        final Optional<PermissionUrlModel> puo = permissionUrlRepository.findById(id.getId());
        if (puo.isEmpty()) {
            return null;
        } else {
            final PermissionUrlModel pum = puo.get();
            final PageRequest pr = JpaPaginationConverter.execute(pagination);
            final PermissionUrlEntity entity = new PermissionUrlEntity(new PermissionUrlId(pum.getId()), pum);
            final List<PermissionAggregate> list = new ArrayList<>();
            final Page<PermissionUrlGroupRelationUrlModel> pug = permissionUrlGroupRelationUrlRepository.findAll(
                    (Specification<PermissionUrlGroupRelationUrlModel>)
                            (rt, qy, cb) -> cb.equal(rt.get(PermissionUrlGroupRelationUrlModel.UID), id.getId()), pr);
            for (final PermissionUrlGroupRelationUrlModel model : pug) {
                final PermissionAggregate aggregate = new PermissionAggregate(
                        new PermissionId(model.getUid(), model.getGid()), model).setPermissionUrlEntity(entity);
                permissionUrlGroupRepository
                        .findById(model.getGid())
                        .ifPresent(m -> aggregate.setPermissionUrlGroupEntity(
                                new PermissionUrlGroupEntity(new PermissionUrlGroupId(m.getId()), m)
                        ));
                list.add(aggregate);
            }
            return new PermissionUrlDetailsAggregate(
                    (pr.getPageNumber() + 1), pr.getPageSize(), pug.getTotalElements(),
                    new PermissionUrlId(pum.getId()), entity, list
            );
        }
    }

    @Override
    public PermissionUrlGroupDetailsAggregate urlGroupDetails(PermissionUrlGroupId id, PaginationContext pagination) {
        final Optional<PermissionUrlGroupModel> pgo = permissionUrlGroupRepository.findById(id.getId());
        if (pgo.isEmpty()) {
            return null;
        } else {
            final PermissionUrlGroupModel pgm = pgo.get();
            final PageRequest pr = JpaPaginationConverter.execute(pagination);
            final PermissionUrlGroupEntity entity =
                    new PermissionUrlGroupEntity(new PermissionUrlGroupId(pgm.getId()), pgm);
            final List<PermissionAggregate> list = new ArrayList<>();
            final Page<PermissionUrlGroupRelationUrlModel> pug = permissionUrlGroupRelationUrlRepository.findAll(
                    (Specification<PermissionUrlGroupRelationUrlModel>)
                            (rt, qy, cb) -> cb.equal(rt.get(PermissionUrlGroupRelationUrlModel.GID), id.getId()), pr);
            for (final PermissionUrlGroupRelationUrlModel model : pug) {
                final PermissionAggregate aggregate = new PermissionAggregate(
                        new PermissionId(model.getUid(), model.getGid()), model).setPermissionUrlGroupEntity(entity);
                permissionUrlRepository
                        .findById(model.getUid())
                        .ifPresent(m -> aggregate.setPermissionUrlEntity(
                                new PermissionUrlEntity(new PermissionUrlId(m.getId()), m)
                        ));
                list.add(aggregate);
            }
            return new PermissionUrlGroupDetailsAggregate(
                    (pr.getPageNumber() + 1), pr.getPageSize(), pug.getTotalElements(),
                    new PermissionUrlGroupId(pgm.getId()), entity, list
            );
        }
    }

}
