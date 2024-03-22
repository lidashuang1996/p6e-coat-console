package club.p6e.coat.console.application.service;

import club.p6e.coat.console.infrastructure.context.MeContext;

/**
 * 我的服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface MeService {

    /**
     * 信息
     *
     * @return 结果对象
     */
    MeContext.Info.Dto info();

    /**
     * 修改密码
     *
     * @param request 请求对象
     * @return 结果对象
     */
    MeContext.ChangePassword.Dto changePassword(MeContext.ChangePassword.Request request);

}
