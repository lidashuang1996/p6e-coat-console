package club.p6e.coat.console.controller;

import club.p6e.coat.common.context.ResultContext;
import club.p6e.coat.common.controller.BaseWebController;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.KeyValueService;
import club.p6e.coat.console.infrastructure.context.KeyValueContext;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import club.p6e.coat.console.infrastructure.model.KeyValueModel;
import org.springframework.web.bind.annotation.*;

/**
 * 配置接口
 *
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/config")
public class KeyValueController extends BaseWebController {

    /**
     * KEY/VALUE 服务
     */
    private final KeyValueService service;

    /**
     * 构造方法初始化
     *
     * @param service KEY/VALUE 服务
     */
    public KeyValueController(KeyValueService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResultContext list(KeyValueContext.Request request) {
        return getList(request);
    }

    @GetMapping("/list")
    public ResultContext getList(KeyValueContext.Request request) {
        return postList(request);
    }

    @PostMapping("/list")
    public ResultContext postList(@RequestBody KeyValueContext.Request request) {
        if (request == null) {
            request = new KeyValueContext.Request();
        }
        if (request.getSort() != null && request.getSort().isValidationFailure(KeyValueModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(KeyValueContext.Request request)",
                    "Request sort validation exception."
            );
        }
        if (request.getSearch() != null && request.getSearch().isValidationFailure(KeyValueModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(KeyValueContext.Request request)",
                    "Request search validation exception."
            );
        }
        final KeyValueContext.ListDto result = service.list(request);
        return ResultContext.build(CopyUtil.run(result, KeyValueContext.ListVo.class));
    }

    @PostMapping()
    public ResultContext create(@RequestBody KeyValueContext.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(KeyValueContext.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getKey() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(KeyValueContext.Request request)",
                    "Request parameter [key] exception."
            );
        }
        if (request.getValue() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(KeyValueContext.Request request)",
                    "Request parameter [value] exception."
            );
        }
        final KeyValueContext.Dto result = service.create(request);
        return ResultContext.build(CopyUtil.run(result, KeyValueContext.Vo.class));
    }

    @PutMapping("/{id}")
    public ResultContext update(@PathVariable Integer id, @RequestBody KeyValueContext.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, KeyValueContext.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getKey() == null && request.getValue() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, KeyValueContext.Request request)",
                    "Request parameter exception."
            );
        }
        final KeyValueContext.Dto result = service.update(request.setId(id));
        return ResultContext.build(CopyUtil.run(result, KeyValueContext.Vo.class));
    }

    @GetMapping("/{id}")
    public ResultContext get(@PathVariable Integer id) {
        final KeyValueContext.Dto result = service.get(new KeyValueContext.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, KeyValueContext.Vo.class));
    }

    @DeleteMapping("/{id}")
    public ResultContext delete(@PathVariable Integer id) {
        final KeyValueContext.Dto result = service.delete(new KeyValueContext.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, KeyValueContext.Vo.class));
    }

    @GetMapping("/key/{key}")
    public ResultContext key(@PathVariable String key) {
        return getKeys(new KeyValueContext.Request().setKey(key));
    }

    @GetMapping("/keys")
    public ResultContext getKeys(KeyValueContext.Request request) {
        return postKeys(request);
    }

    @PostMapping("/keys")
    public ResultContext postKeys(@RequestBody KeyValueContext.Request request) {
        return ResultContext.build(service.keys(request).getKv());
    }
}
