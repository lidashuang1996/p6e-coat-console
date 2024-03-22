package club.p6e.coat.console.controller;

import club.p6e.coat.common.context.ResultContext;
import club.p6e.coat.common.controller.BaseWebController;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.ConfigService;
import club.p6e.coat.console.infrastructure.context.ConfigContext;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import club.p6e.coat.console.infrastructure.model.ConfigModel;
import org.springframework.web.bind.annotation.*;

/**
 * 配置接口
 *
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseWebController {

    /**
     * 配置服务
     */
    private final ConfigService service;

    /**
     * 构造方法初始化
     *
     * @param service 配置服务
     */
    public ConfigController(ConfigService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResultContext list(ConfigContext.Request request) {
        return getList(request);
    }

    @GetMapping("/list")
    public ResultContext getList(ConfigContext.Request request) {
        return postList(request);
    }

    @PostMapping("/list")
    public ResultContext postList(@RequestBody ConfigContext.Request request) {
        if (request == null) {
            request = new ConfigContext.Request();
        }
        if (request.getSort() != null && request.getSort().isValidationFailure(ConfigModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(ConfigContext.Request request)",
                    "Request sort validation exception."
            );
        }
        if (request.getSearch() != null && request.getSearch().isValidationFailure(ConfigModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(ConfigContext.Request request)",
                    "Request search validation exception."
            );
        }
        final ConfigContext.ListDto result = service.list(request);
        return ResultContext.build(CopyUtil.run(result, ConfigContext.ListVo.class));
    }

    @PostMapping()
    public ResultContext create(@RequestBody ConfigContext.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(ConfigContext.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getType() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(ConfigContext.Request request)",
                    "Request parameter [type] exception."
            );
        }
        if (request.getKey() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(ConfigContext.Request request)",
                    "Request parameter [key] exception."
            );
        }
        if (request.getValue() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(ConfigContext.Request request)",
                    "Request parameter [value] exception."
            );
        }
        final ConfigContext.Dto result = service.create(request);
        return ResultContext.build(CopyUtil.run(result, ConfigContext.Vo.class));
    }

    @PutMapping("/{id}")
    public ResultContext update(@PathVariable Integer id, @RequestBody ConfigContext.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, ConfigContext.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getType() == null && request.getKey() == null && request.getValue() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, ConfigContext.Request request)",
                    "Request parameter exception."
            );
        }
        final ConfigContext.Dto result = service.update(request.setId(id));
        return ResultContext.build(CopyUtil.run(result, ConfigContext.Vo.class));
    }

    @GetMapping("/{id}")
    public ResultContext get(@PathVariable Integer id) {
        final ConfigContext.Dto result = service.get(new ConfigContext.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, ConfigContext.Vo.class));
    }

    @DeleteMapping("/{id}")
    public ResultContext delete(@PathVariable Integer id) {
        final ConfigContext.Dto result = service.delete(new ConfigContext.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, ConfigContext.Vo.class));
    }

}
