package club.p6e.coat.console.domain.service;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.ConfigTypeAggregate;
import club.p6e.coat.console.domain.aggregate.ConfigListAggregate;
import club.p6e.coat.console.domain.entity.ConfigEntity;
import club.p6e.coat.console.domain.identifier.ConfigId;
import club.p6e.coat.console.domain.identifier.ConfigType;

/**
 * 配置的领域服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface ConfigDomainService {

    /**
     * 读取
     *
     * @param id ID 对象
     * @return 实体对象
     */
    ConfigEntity get(ConfigId id);

    /**
     * 删除
     *
     * @param id ID 对象
     * @return 实体对象
     */
    ConfigEntity delete(ConfigId id);

    /**
     * 创建
     *
     * @param ce 实体对象
     * @return 实体对象
     */
    ConfigEntity create(ConfigEntity ce);

    /**
     * 更新
     *
     * @param ce 实体对象
     * @return 实体对象
     */
    ConfigEntity update(ConfigEntity ce);

    /**
     * 列表
     *
     * @param search     搜索上下文
     * @param sort       排序上下文
     * @param pagination 分页上下文
     * @return 列表聚合对象
     */
    ConfigListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination);

    /**
     * 类型数据查询
     *
     * @param type 实体对象
     * @return 类型聚合对象
     */
    ConfigTypeAggregate getTypeData(ConfigType type);

}
