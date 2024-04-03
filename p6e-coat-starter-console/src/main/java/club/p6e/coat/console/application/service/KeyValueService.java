package club.p6e.coat.console.application.service;

import club.p6e.coat.console.infrastructure.context.KeyValueContext;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface KeyValueService {

    /**
     * 读取
     *
     * @param request 参数对象
     * @return 结果对象
     */
    KeyValueContext.Dto get(KeyValueContext.Request request);

    /**
     * 读取
     *
     * @param request 参数对象
     * @return 结果对象
     */
    KeyValueContext.KeyValueMap keys(KeyValueContext.Request request);

    /**
     * 创建
     *
     * @param request 参数对象
     * @return 结果对象
     */
    KeyValueContext.Dto create(KeyValueContext.Request request);

    /**
     * 更新
     *
     * @param request 参数对象
     * @return 结果对象
     */
    KeyValueContext.Dto update(KeyValueContext.Request request);

    /**
     * 删除
     *
     * @param request 参数对象
     * @return 结果对象
     */
    KeyValueContext.Dto delete(KeyValueContext.Request request);

    /**
     * 列表
     *
     * @param request 参数对象
     * @return 结果对象
     */
    KeyValueContext.ListDto list(KeyValueContext.Request request);

}
