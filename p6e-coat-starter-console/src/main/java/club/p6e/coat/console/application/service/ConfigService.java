package club.p6e.coat.console.application.service;

import club.p6e.coat.console.infrastructure.context.ConfigContext;

/**
 * 配置服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface ConfigService {

    /**
     * 读取
     *
     * @param request 参数对象
     * @return 结果对象
     */
    ConfigContext.Dto get(ConfigContext.Request request);

    /**
     * 创建
     *
     * @param request 参数对象
     * @return 结果对象
     */
    ConfigContext.Dto create(ConfigContext.Request request);

    /**
     * 更新
     *
     * @param request 参数对象
     * @return 结果对象
     */
    ConfigContext.Dto update(ConfigContext.Request request);

    /**
     * 删除
     *
     * @param request 参数对象
     * @return 结果对象
     */
    ConfigContext.Dto delete(ConfigContext.Request request);

    /**
     * 列表
     *
     * @param request 参数对象
     * @return 结果对象
     */
    ConfigContext.ListDto list(ConfigContext.Request request);

}
