package club.p6e.coat.console.domain.repository.impl;

import club.p6e.coat.common.pagination.JpaPaginationConverter;
import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.JpaSearchableConverter;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.JpaSortableConverter;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupListAggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlGroupEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;
import club.p6e.coat.console.domain.repository.PermissionUrlGroupDomainRepository;
import club.p6e.coat.console.infrastructure.model.PermissionUrlGroupModel;
import club.p6e.coat.console.infrastructure.repository.PermissionUrlGroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 权限 URL GROUP 领域存储库实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PermissionUrlGroupDomainRepositoryImpl implements PermissionUrlGroupDomainRepository {

    /**
     * 权限 URL GROUP 存储库
     */
    private final PermissionUrlGroupRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 权限 URL GROUP 存储库
     */
    public PermissionUrlGroupDomainRepositoryImpl(PermissionUrlGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public PermissionUrlGroupEntity save(PermissionUrlGroupEntity pge) {
        if (pge.id() == null) {
            final PermissionUrlGroupModel pgm = repository.save(pge.convertToModel());
            return new PermissionUrlGroupEntity(new PermissionUrlGroupId(pgm.getId()), pgm);
        } else {
            final Optional<PermissionUrlGroupModel> optional = repository.findById(pge.id().getId());
            if (optional.isEmpty()) {
                return null;
            } else {
                final PermissionUrlGroupModel pgm = optional.get();
                repository.saveAndFlush(CopyUtil.run(pge.convertToModel(), pgm));
                return new PermissionUrlGroupEntity(new PermissionUrlGroupId(pgm.getId()), pgm);
            }
        }
    }

    @Override
    public PermissionUrlGroupEntity delete(PermissionUrlGroupId id) {
        final Optional<PermissionUrlGroupModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final PermissionUrlGroupModel pgm = optional.get();
            repository.delete(pgm);
            return new PermissionUrlGroupEntity(new PermissionUrlGroupId(pgm.getId()), pgm);
        }
    }

    @Override
    public PermissionUrlGroupEntity findById(PermissionUrlGroupId id) {
        final Optional<PermissionUrlGroupModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final PermissionUrlGroupModel pgm = optional.get();
            return new PermissionUrlGroupEntity(new PermissionUrlGroupId(pgm.getId()), pgm);
        }
    }

    @Override
    public PermissionUrlGroupListAggregate findByParent(PermissionUrlGroupId id) {
        final List<PermissionUrlGroupModel> list = repository.findAll(
                (Specification<PermissionUrlGroupModel>) (rt, qy, cb) ->
                        cb.equal(rt.get(PermissionUrlGroupModel.PARENT), id.getId()),
                Sort.by(Sort.Order.asc(PermissionUrlGroupModel.ID))
        );
        return new PermissionUrlGroupListAggregate(1, list.size(), list.size(),
                list.stream().map(m -> new PermissionUrlGroupEntity(new PermissionUrlGroupId(m.getId()), m)).collect(Collectors.toList()));
    }

    @Override
    public PermissionUrlGroupListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination) {
        final PageRequest pr = JpaPaginationConverter.execute(pagination);
        final Page<PermissionUrlGroupModel> data = repository.findAll((Specification<PermissionUrlGroupModel>) (rt, qy, cb) -> {
            JpaSortableConverter.injectSort(rt, qy, cb, sortable);
            JpaSearchableConverter.injectSearch(rt, qy, cb, searchable);
            return qy.getRestriction();
        }, pr);
        return new PermissionUrlGroupListAggregate(
                (pr.getPageNumber() + 1), pr.getPageSize(), data.getTotalElements(),
                data.getContent().stream().map(m -> new PermissionUrlGroupEntity(new PermissionUrlGroupId(m.getId()), m)).collect(Collectors.toList())
        );
    }

}
