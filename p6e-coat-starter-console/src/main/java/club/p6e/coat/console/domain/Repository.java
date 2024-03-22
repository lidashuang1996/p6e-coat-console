package club.p6e.coat.console.domain;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;

/**
 * 存储库
 *
 * @author lidashuang
 * @version 1.0
 */
public interface Repository<T extends Entity<ID>, ID extends Identifier> {

    /**
     * 保存/修改
     *
     * @param t 实体对象
     * @return 实体对象
     */
    T save(T t);

    /**
     * 删除
     *
     * @param id 标识符对象
     * @return 实体对象
     */
    T delete(ID id);

    /**
     * 查询
     *
     * @param id 标识符对象
     * @return 实体对象
     */
    T findById(ID id);

    /**
     * 列表
     *
     * @param searchable 搜索对象
     * @param sortable   排序对象
     * @param pagination 分页对象
     * @return 实体对象
     */
    ListAggregate<T> list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination);

}
