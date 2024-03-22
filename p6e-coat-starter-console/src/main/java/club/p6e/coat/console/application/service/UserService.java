package club.p6e.coat.console.application.service;

import club.p6e.coat.console.infrastructure.context.UserContext;

/**
 * 用户服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface UserService {

    /**
     * 读取
     *
     * @param request 参数对象
     * @return 结果对象
     */
    UserContext.Dto get(UserContext.Request request);

    /**
     * 创建
     *
     * @param request 参数对象
     * @return 结果对象
     */
    UserContext.Dto create(UserContext.Request request);

    /**
     * 创建
     *
     * @param request 参数对象
     * @return 结果对象
     */
    UserContext.Dto resetPassword(UserContext.Request request);

    /**
     * 修改
     *
     * @param request 参数对象
     * @return 结果对象
     */
    UserContext.Dto updateStatus(UserContext.Request request);

    /**
     * 修改
     *
     * @param request 参数对象
     * @return 结果对象
     */
    UserContext.Dto updateEnabled(UserContext.Request request);

    /**
     * 修改
     *
     * @param request 参数对象
     * @return 结果对象
     */
    UserContext.Dto updateInternal(UserContext.Request request);

    /**
     * 列表
     *
     * @param request 参数对象
     * @return 结果对象
     */
    UserContext.ListDto list(UserContext.Request request);

}
