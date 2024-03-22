package club.p6e.coat.console.application.service.impl;

import club.p6e.coat.common.global.Globals;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.MeService;
import club.p6e.coat.console.domain.entity.PasswordEntity;
import club.p6e.coat.console.domain.identifier.UserId;
import club.p6e.coat.console.domain.service.UserDomainService;
import club.p6e.coat.console.infrastructure.context.MeContext;
import org.springframework.stereotype.Service;

/**
 * 我的服务实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Service
public class MeServiceImpl implements MeService {

    /**
     * 用户领域服务
     */
    private final UserDomainService service;

    /**
     * 构造方法初始化
     *
     * @param service 用户领域服务
     */
    public MeServiceImpl(UserDomainService service) {
        this.service = service;
    }

    @Override
    public MeContext.Info.Dto info() {
        return CopyUtil.run(Globals.getUserInfo(), MeContext.Info.Dto.class);
    }

    @Override
    public MeContext.ChangePassword.Dto changePassword(MeContext.ChangePassword.Request request) {
        return CopyUtil.run(service.updatePassword(
                new UserId(Globals.getUserInfo().getId()),
                new PasswordEntity(request.getOldPassword()),
                new PasswordEntity(request.getNewPassword())
        ).convertToModel(), MeContext.ChangePassword.Dto.class);
    }

}
