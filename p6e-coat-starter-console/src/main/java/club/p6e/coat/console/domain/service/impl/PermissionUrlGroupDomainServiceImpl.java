package club.p6e.coat.console.domain.service.impl;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupListAggregate;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupTreeAggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlGroupEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;
import club.p6e.coat.console.domain.repository.PermissionUrlGroupDomainRepository;
import club.p6e.coat.console.domain.service.PermissionUrlGroupDomainService;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import org.springframework.stereotype.Component;

/**
 * 权限 URL GROUP 领域服务实现类
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PermissionUrlGroupDomainServiceImpl implements PermissionUrlGroupDomainService {

    /**
     * 权限 URL GROUP 领域存储库
     */
    private final PermissionUrlGroupDomainRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 权限 URL GROUP 领域存储库
     */
    public PermissionUrlGroupDomainServiceImpl(PermissionUrlGroupDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public PermissionUrlGroupEntity get(PermissionUrlGroupId id) {
        final PermissionUrlGroupEntity entity = repository.findById(id);
        if (entity == null) {
            throw GlobalExceptionContext.executePermissionUrlGroupDataNotExistException(
                    this.getClass(),
                    "fun get(PermissionUrlGroupId id).",
                    "Request get data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public PermissionUrlGroupEntity delete(PermissionUrlGroupId id) {
        final PermissionUrlGroupEntity entity = repository.delete(id);
        if (entity == null) {
            throw GlobalExceptionContext.executePermissionUrlGroupDataNotExistException(
                    this.getClass(),
                    "fun delete(PermissionUrlGroupId id).",
                    "Request delete data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public PermissionUrlGroupEntity create(PermissionUrlGroupEntity pge) {
        if (pge.getId() == null) {
            return repository.save(pge);
        } else {
            throw GlobalExceptionContext.executePermissionUrlGroupDataExistException(
                    this.getClass(),
                    "fun create(PermissionUrlGroupEntity pge).",
                    "Request create data already exist."
            );
        }
    }

    @Override
    public PermissionUrlGroupEntity update(PermissionUrlGroupEntity pge) {
        final PermissionUrlGroupEntity entity = repository.save(pge);
        if (entity == null) {
            throw GlobalExceptionContext.executePermissionUrlGroupDataNotExistException(
                    this.getClass(),
                    "fun update(PermissionUrlGroupEntity pue).",
                    "Request update data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public PermissionUrlGroupTreeAggregate tree(PermissionUrlGroupId id) {
        if (id == null) {
            throw GlobalExceptionContext.executePermissionUrlGroupDataNotExistException(
                    this.getClass(),
                    "fun tree(PermissionUrlGroupId id).",
                    "Request tree data does not exist."
            );
        } else {
            if (id.getId() == 0) {
                return new PermissionUrlGroupTreeAggregate(repository.findByParent(id).getList());
            } else {
                final PermissionUrlGroupEntity entity = repository.findById(id);
                if (entity == null) {
                    throw GlobalExceptionContext.executePermissionUrlGroupDataNotExistException(
                            this.getClass(),
                            "fun tree(PermissionUrlGroupId id).",
                            "Request tree data does not exist."
                    );
                } else {
                    return new PermissionUrlGroupTreeAggregate(repository.findByParent(id).getList());
                }
            }
        }
    }

    @Override
    public PermissionUrlGroupListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination) {
        return repository.list(search, sort, pagination);
    }

}
