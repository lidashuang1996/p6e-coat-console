package club.p6e.coat.console.domain;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;

/**
 * 空聚合对象
 *
 * @author lidashuang
 * @version 1.0
 */
public interface EmptyRepository<T extends Entity<ID>, ID extends Identifier> extends Repository<T, ID> {

    @Override
    default T save(T t) {
        return null;
    }

    @Override
    default T delete(ID id) {
        return null;
    }

    @Override
    default T findById(ID id) {
        return null;
    }

    @Override
    default ListAggregate<T> list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination) {
        return null;
    }

}
