package club.p6e.coat.console.application.service;

import club.p6e.coat.console.infrastructure.context.DictionaryContext;

/**
 * 字典服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface DictionaryService {

    /**
     * 读取
     *
     * @param request 请求对象
     * @return 结果对象
     */
    DictionaryContext.Dto get(DictionaryContext.Request request);

    /**
     * 创建
     *
     * @param request 请求对象
     * @return 结果对象
     */
    DictionaryContext.Dto create(DictionaryContext.Request request);


    /**
     * 更新
     *
     * @param request 请求对象
     * @return 结果对象
     */
    DictionaryContext.Dto update(DictionaryContext.Request request);

    /**
     * 删除
     *
     * @param request 请求对象
     * @return 结果对象
     */
    DictionaryContext.Dto delete(DictionaryContext.Request request);

    /**
     * 列表
     *
     * @param request 请求对象
     * @return 结果对象
     */
    DictionaryContext.ListDto list(DictionaryContext.Request request);

    /**
     * 选项
     *
     * @param request 请求对象
     * @return 结果对象
     */
    DictionaryContext.Option.Dto option(DictionaryContext.Option.Request request);

}
