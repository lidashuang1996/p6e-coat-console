package club.p6e.coat.console.domain.repository;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.Repository;
import club.p6e.coat.console.domain.aggregate.ConfigListAggregate;
import club.p6e.coat.console.domain.aggregate.ConfigTypeAggregate;
import club.p6e.coat.console.domain.aggregate.DictionaryListAggregate;
import club.p6e.coat.console.domain.aggregate.DictionaryTypeAggregate;
import club.p6e.coat.console.domain.entity.DictionaryEntity;
import club.p6e.coat.console.domain.identifier.DictionaryId;
import club.p6e.coat.console.domain.identifier.DictionaryType;

/**
 * 字典领域存储库
 *
 * @author lidashuang
 * @version 1.0
 */
public interface DictionaryDomainRepository extends Repository<DictionaryEntity, DictionaryId> {

    /**
     * 根据类型查询
     *
     * @param type 类型对象
     * @return 类型聚合根
     */
    DictionaryTypeAggregate findByType(DictionaryType type);

    /**
     * 列表
     *
     * @param searchable 搜索对象
     * @param sortable   排序对象
     * @param pagination 分页对象
     * @return 列表聚合根
     */
    DictionaryListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination);

}
