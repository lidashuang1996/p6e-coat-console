package club.p6e.coat.console.controller;

import club.p6e.coat.common.context.ResultContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.UserService;
import club.p6e.coat.console.infrastructure.context.UserContext;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import club.p6e.coat.console.infrastructure.model.UserModel;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 *
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户服务
     */
    private final UserService service;

    /**
     * 构造方法初始化
     *
     * @param service 用户服务
     */
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResultContext list(UserContext.Request request) {
        return getList(request);
    }

    @GetMapping("/list")
    public ResultContext getList(UserContext.Request request) {
        return postList(request);
    }

    @PostMapping("/list")
    public ResultContext postList(@RequestBody UserContext.Request request) {
        if (request == null) {
            request = new UserContext.Request();
        }
        if (request.getSort() != null && request.getSort().isValidationFailure(UserModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(UserContext.Request request)",
                    "Request sort validation exception."
            );
        }
        if (request.getSearch() != null && request.getSearch().isValidationFailure(UserModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(UserContext.Request request)",
                    "Request search validation exception."
            );
        }
        final UserContext.ListDto result = service.list(request);
        return ResultContext.build(CopyUtil.run(result, UserContext.ListVo.class));
    }

    @PostMapping()
    public ResultContext create(@RequestBody UserContext.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(UserContext.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getAccount() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(UserContext.Request request)",
                    "Request parameter [account] exception."
            );
        }
        if (request.getPassword() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(UserContext.Request request)",
                    "Request parameter [password] exception."
            );
        }
        final UserContext.Dto result = service.create(request);
        return ResultContext.build(CopyUtil.run(result, UserContext.Vo.class));
    }

    @GetMapping("/{id}")
    public ResultContext get(@PathVariable Integer id) {
        final UserContext.Dto result = service.get(new UserContext.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, UserContext.Vo.class));
    }

    @PutMapping("/status/{id}")
    public ResultContext updateStatus(@PathVariable Integer id, Integer status) {
        final UserContext.Dto result = service.updateStatus(
                new UserContext.Request().setId(id).setStatus(status));
        return ResultContext.build(CopyUtil.run(result, UserContext.Vo.class));
    }

    @PutMapping("/enabled/{id}")
    public ResultContext updateEnabled(@PathVariable Integer id, Integer enabled) {
        final UserContext.Dto result = service.updateEnabled(
                new UserContext.Request().setId(id).setEnabled(enabled));
        return ResultContext.build(CopyUtil.run(result, UserContext.Vo.class));
    }

    @PutMapping("/internal/{id}")
    public ResultContext updateInternal(@PathVariable Integer id, Integer internal) {
        final UserContext.Dto result = service.updateInternal(
                new UserContext.Request().setId(id).setInternal(internal));
        return ResultContext.build(CopyUtil.run(result, UserContext.Vo.class));
    }

    @PutMapping("/reset/password/{id}")
    public ResultContext resetPassword(@PathVariable Integer id) {
        final UserContext.Dto result = service.resetPassword(new UserContext.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, UserContext.Vo.class));
    }
}
