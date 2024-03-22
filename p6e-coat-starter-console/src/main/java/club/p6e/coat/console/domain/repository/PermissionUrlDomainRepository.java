package club.p6e.coat.console.domain.repository;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.Repository;
import club.p6e.coat.console.domain.aggregate.PermissionUrlListAggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;

/**
 * 权限 URL 领域存储库
 *
 * @author lidashuang
 * @version 1.0
 */
public interface PermissionUrlDomainRepository extends Repository<PermissionUrlEntity, PermissionUrlId> {

    /**
     * 列表
     *
     * @param searchable 搜索对象
     * @param sortable   排序对象
     * @param pagination 分页对象
     * @return 列表聚合根
     */
    PermissionUrlListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination);

}
