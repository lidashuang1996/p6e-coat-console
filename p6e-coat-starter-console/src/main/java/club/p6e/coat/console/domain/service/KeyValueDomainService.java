package club.p6e.coat.console.domain.service;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.KeyValueListAggregate;
import club.p6e.coat.console.domain.entity.KeyValueEntity;
import club.p6e.coat.console.domain.identifier.KvId;
import club.p6e.coat.console.domain.identifier.KvKey;

/**
 * 配置的领域服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface KeyValueDomainService {

    /**
     * 读取
     *
     * @param id ID 对象
     * @return 实体对象
     */
    KeyValueEntity get(KvId id);

    /**
     * 删除
     *
     * @param id ID 对象
     * @return 实体对象
     */
    KeyValueEntity delete(KvId id);

    /**
     * 创建
     *
     * @param ce 实体对象
     * @return 实体对象
     */
    KeyValueEntity create(KeyValueEntity ce);

    /**
     * 更新
     *
     * @param ce 实体对象
     * @return 实体对象
     */
    KeyValueEntity update(KeyValueEntity ce);

    /**
     * 列表
     *
     * @param search     搜索上下文
     * @param sort       排序上下文
     * @param pagination 分页上下文
     * @return 列表聚合对象
     */
    KeyValueListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination);

    /**
     * 读取
     *
     * @param key KEY 对象
     * @return 实体对象
     */
    KeyValueEntity get(KvKey key);

}
