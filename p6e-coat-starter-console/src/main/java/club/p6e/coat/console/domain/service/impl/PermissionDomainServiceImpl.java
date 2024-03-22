package club.p6e.coat.console.domain.service.impl;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.console.domain.aggregate.PermissionUrlDetailsAggregate;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupDetailsAggregate;
import club.p6e.coat.console.domain.entity.PermissionEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;
import club.p6e.coat.console.domain.repository.PermissionDomainRepository;
import club.p6e.coat.console.domain.service.PermissionDomainService;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import org.springframework.stereotype.Component;

/**
 * 权限领域服务实现类
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PermissionDomainServiceImpl implements PermissionDomainService {

    /**
     * 权限领域存储库
     */
    private final PermissionDomainRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 权限领域存储库
     */
    public PermissionDomainServiceImpl(PermissionDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public PermissionUrlDetailsAggregate urlDetails(PermissionUrlId id, PaginationContext pagination) {
        final PermissionUrlDetailsAggregate aggregate = repository.urlDetails(id, pagination);
        if (aggregate == null) {
            throw GlobalExceptionContext.executePermissionUrlDataNotExistException(
                    this.getClass(),
                    "fun urlDetails(PermissionId id).",
                    "Request parameter does not exist or there is already an association."
            );
        } else {
            return aggregate;
        }
    }

    @Override
    public PermissionUrlGroupDetailsAggregate urlGroupDetails(PermissionUrlGroupId id, PaginationContext pagination) {
        final PermissionUrlGroupDetailsAggregate aggregate = repository.urlGroupDetails(id, pagination);
        if (aggregate == null) {
            throw GlobalExceptionContext.executePermissionUrlGroupDataNotExistException(
                    this.getClass(),
                    "fun urlGroupDetails(PermissionId id).",
                    "Request parameter does not exist or there is already an association."
            );
        } else {
            return aggregate;
        }
    }

    @Override
    public PermissionEntity association(PermissionEntity pe) {
        final PermissionEntity entity = repository.association(pe);
        if (entity == null) {
            throw GlobalExceptionContext.executePermissionAssociationException(
                    this.getClass(),
                    "fun association(PermissionEntity pe).",
                    "Request parameter does not exist or there is already an association."
            );
        } else {
            return entity;
        }
    }

    @Override
    public PermissionEntity disassociate(PermissionEntity pe) {
        final PermissionEntity entity = repository.disassociate(pe);
        if (entity == null) {
            throw GlobalExceptionContext.executePermissionDisassociateException(
                    this.getClass(),
                    "fun disassociate(PermissionEntity pe).",
                    "Request parameter does not exist or is not associated."
            );
        } else {
            return entity;
        }
    }

}
