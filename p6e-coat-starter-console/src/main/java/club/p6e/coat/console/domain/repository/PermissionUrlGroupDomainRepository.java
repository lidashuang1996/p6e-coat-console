package club.p6e.coat.console.domain.repository;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.Repository;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupListAggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlGroupEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;

/**
 * 权限 URL GROUP 领域存储库
 *
 * @author lidashuang
 * @version 1.0
 */
public interface PermissionUrlGroupDomainRepository extends Repository<PermissionUrlGroupEntity, PermissionUrlGroupId> {

    /**
     * 查询节点下面的数据
     *
     * @param id ID 对象
     * @return 列表聚合根
     */
    PermissionUrlGroupListAggregate findByParent(PermissionUrlGroupId id);

    /**
     * 列表
     *
     * @param searchable 搜索对象
     * @param sortable   排序对象
     * @param pagination 分页对象
     * @return 列表聚合根
     */
    PermissionUrlGroupListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination);

}