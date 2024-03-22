package club.p6e.coat.console.domain.service;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.DictionaryListAggregate;
import club.p6e.coat.console.domain.aggregate.DictionaryTypeAggregate;
import club.p6e.coat.console.domain.entity.DictionaryEntity;
import club.p6e.coat.console.domain.identifier.DictionaryId;
import club.p6e.coat.console.domain.identifier.DictionaryType;

/**
 * 字典的领域服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface DictionaryDomainService {

    /**
     * 读取
     *
     * @param id ID 对象
     * @return 实体对象
     */
    DictionaryEntity get(DictionaryId id);

    /**
     * 删除
     *
     * @param id ID 对象
     * @return 实体对象
     */
    DictionaryEntity delete(DictionaryId id);

    /**
     * 创建
     *
     * @param de 实体对象
     * @return 实体对象
     */
    DictionaryEntity create(DictionaryEntity de);

    /**
     * 更新
     *
     * @param de 实体对象
     * @return 实体对象
     */
    DictionaryEntity update(DictionaryEntity de);

    /**
     * 列表
     *
     * @param search     搜索上下文
     * @param sort       排序上下文
     * @param pagination 分页上下文
     * @return 列表聚合对象
     */
    DictionaryListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination);

    /**
     * 类型数据查询
     *
     * @param type 实体对象
     * @return 类型聚合对象
     */
    DictionaryTypeAggregate getTypeData(DictionaryType type);

}
