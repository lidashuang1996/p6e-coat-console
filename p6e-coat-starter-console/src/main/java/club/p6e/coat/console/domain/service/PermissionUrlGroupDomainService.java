package club.p6e.coat.console.domain.service;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupListAggregate;
import club.p6e.coat.console.domain.aggregate.PermissionUrlGroupTreeAggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlGroupEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface PermissionUrlGroupDomainService {


    PermissionUrlGroupEntity get(PermissionUrlGroupId id);

    PermissionUrlGroupEntity delete(PermissionUrlGroupId id);

    PermissionUrlGroupEntity create(PermissionUrlGroupEntity pue);

    PermissionUrlGroupEntity update(PermissionUrlGroupEntity pue);

    PermissionUrlGroupTreeAggregate tree(PermissionUrlGroupId id);

    PermissionUrlGroupListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination);


}
