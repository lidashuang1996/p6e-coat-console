package club.p6e.coat.console.domain.service.impl;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.PermissionUrlListAggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;
import club.p6e.coat.console.domain.repository.PermissionUrlDomainRepository;
import club.p6e.coat.console.domain.service.PermissionUrlDomainService;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import org.springframework.stereotype.Component;

/**
 * 权限 URL 领域服务实现类
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PermissionUrlDomainServiceImpl implements PermissionUrlDomainService {

    /**
     * 权限 URL 领域存储库
     */
    private final PermissionUrlDomainRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 权限 URL 领域存储库
     */
    public PermissionUrlDomainServiceImpl(PermissionUrlDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public PermissionUrlEntity get(PermissionUrlId id) {
        final PermissionUrlEntity entity = repository.findById(id);
        if (entity == null) {
            throw GlobalExceptionContext.executePermissionUrlDataNotExistException(
                    this.getClass(),
                    "fun get(PermissionUrlId id).",
                    "Request get data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public PermissionUrlEntity delete(PermissionUrlId id) {
        final PermissionUrlEntity entity = repository.delete(id);
        if (entity == null) {
            throw GlobalExceptionContext.executePermissionUrlDataNotExistException(
                    this.getClass(),
                    "fun delete(PermissionUrlId id).",
                    "Request delete data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public PermissionUrlEntity create(PermissionUrlEntity pue) {
        if (pue.getId() == null) {
            return repository.save(pue);
        } else {
            throw GlobalExceptionContext.executePermissionUrlDataExistException(
                    this.getClass(),
                    "fun create(PermissionUrlEntity pue).",
                    "Request create data already exist."
            );
        }
    }

    @Override
    public PermissionUrlEntity update(PermissionUrlEntity pue) {
        final PermissionUrlEntity entity = repository.save(pue);
        if (entity == null) {
            throw GlobalExceptionContext.executePermissionUrlDataNotExistException(
                    this.getClass(),
                    "fun update(PermissionUrlEntity pue).",
                    "Request update data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public PermissionUrlListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination) {
        return repository.list(search, sort, pagination);
    }

}
