package club.p6e.coat.console.domain.repository;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.Repository;
import club.p6e.coat.console.domain.aggregate.KeyValueListAggregate;
import club.p6e.coat.console.domain.entity.KeyValueEntity;
import club.p6e.coat.console.domain.identifier.KvId;
import club.p6e.coat.console.domain.identifier.KvKey;

/**
 * 字典领域存储库
 *
 * @author lidashuang
 * @version 1.0
 */
public interface KeyValueDomainRepository extends Repository<KeyValueEntity, KvId> {

    /**
     * 根据 KEY 查询
     *
     * @param key KEY 对象
     * @return 实体对象
     */
    KeyValueEntity findByKey(KvKey key);


    /**
     * 列表
     *
     * @param searchable 搜索对象
     * @param sortable   排序对象
     * @param pagination 分页对象
     * @return 列表聚合根
     */
    KeyValueListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination);

}
