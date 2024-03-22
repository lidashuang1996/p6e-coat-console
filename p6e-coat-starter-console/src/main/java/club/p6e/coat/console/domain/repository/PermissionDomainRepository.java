package club.p6e.coat.console.domain.repository;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.console.domain.EmptyRepository;
import club.p6e.coat.console.domain.aggregate.PermissionAggregate;
import club.p6e.coat.console.domain.aggregate.PermissionUrlDetailsAggregate;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupDetailsAggregate;
import club.p6e.coat.console.domain.entity.PermissionEntity;
import club.p6e.coat.console.domain.identifier.PermissionId;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;

/**
 * 权限 URL 领域存储库
 *
 * @author lidashuang
 * @version 1.0
 */
public interface PermissionDomainRepository extends EmptyRepository<PermissionAggregate, PermissionId> {

    /**
     * 关联
     *
     * @param pe 实体对象
     * @return 实体对象
     */
    PermissionEntity association(PermissionEntity pe);

    /**
     * 取消关联
     *
     * @param pe 实体对象
     * @return 实体对象
     */
    PermissionEntity disassociate(PermissionEntity pe);

    /**
     * URL 详情
     *
     * @param id         ID 对象
     * @param pagination 分页对象
     * @return URL 详情聚合根
     */
    PermissionUrlDetailsAggregate urlDetails(PermissionUrlId id, PaginationContext pagination);

    /**
     * URL GROUP 详情
     *
     * @param id         ID 对象
     * @param pagination 分页对象
     * @return URL GROUP 详情聚合根
     */
    PermissionUrlGroupDetailsAggregate urlGroupDetails(PermissionUrlGroupId id, PaginationContext pagination);
}
