package club.p6e.coat.console.domain.service;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.console.domain.aggregate.PermissionAggregate;
import club.p6e.coat.console.domain.aggregate.PermissionUrlDetailsAggregate;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupDetailsAggregate;
import club.p6e.coat.console.domain.entity.PermissionEntity;
import club.p6e.coat.console.domain.identifier.PermissionId;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;

/**
 * 权限领域服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface PermissionDomainService {

    /**
     * 权限 URL 详情
     *
     * @param id         ID 对象
     * @param pagination 分页对象
     * @return 权限 URL 详情聚合对象
     */
    PermissionUrlDetailsAggregate urlDetails(PermissionUrlId id, PaginationContext pagination);

    /**
     * 权限 URL GROUP 详情
     *
     * @param id         ID 对象
     * @param pagination 分页对象
     * @return 权限 URL GROUP 详情聚合对象
     */
    PermissionUrlGroupDetailsAggregate urlGroupDetails(PermissionUrlGroupId id, PaginationContext pagination);

    /**
     * 权限 URL / GROUP 关联
     *
     * @param pe 实体对象
     * @return 实体对象
     */
    PermissionEntity association(PermissionEntity pe);

    /**
     * 权限 URL / GROUP 取消关联
     *
     * @param pe 实体对象
     * @return 实体对象
     */
    PermissionEntity disassociate(PermissionEntity pe);

}
