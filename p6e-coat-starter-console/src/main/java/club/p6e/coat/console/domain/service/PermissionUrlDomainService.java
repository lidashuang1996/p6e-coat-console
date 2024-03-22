package club.p6e.coat.console.domain.service;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.PermissionUrlListAggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface PermissionUrlDomainService {

    PermissionUrlEntity get(PermissionUrlId id);

    PermissionUrlEntity delete(PermissionUrlId id);

    PermissionUrlEntity create(PermissionUrlEntity pue);

    PermissionUrlEntity update(PermissionUrlEntity pue);

    PermissionUrlListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination);

}
