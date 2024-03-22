package club.p6e.coat.console.controller;

import club.p6e.coat.common.context.ResultContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.MeService;
import club.p6e.coat.console.infrastructure.context.MeContext;
import org.springframework.web.bind.annotation.*;

/**
 * 我的接口
 *
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/me")
public class MeController {

    /**
     * 我的服务
     */
    private final MeService service;

    /**
     * 构造方法初始化
     *
     * @param service 我的服务
     */
    public MeController(MeService service) {
        this.service = service;
    }

    @GetMapping("/info")
    public ResultContext info() {
        final MeContext.Info.Dto result = service.info();
        return ResultContext.build(CopyUtil.run(result, MeContext.Info.Vo.class));
    }

    @PutMapping("/change/password")
    public ResultContext changePassword(@RequestBody MeContext.ChangePassword.Request request) {
        final MeContext.ChangePassword.Dto result = service.changePassword(request);
        return ResultContext.build(CopyUtil.run(result, MeContext.ChangePassword.Vo.class));
    }

}
