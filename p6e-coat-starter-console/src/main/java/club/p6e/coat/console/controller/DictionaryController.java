package club.p6e.coat.console.controller;

import club.p6e.coat.common.context.ResultContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.DictionaryService;
import club.p6e.coat.console.infrastructure.context.DictionaryContext;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import club.p6e.coat.console.infrastructure.model.DictionaryModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 字典接口
 *
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    /**
     * 字典服务
     */
    private final DictionaryService service;

    /**
     * 构造方法初始化
     *
     * @param service 字典服务
     */
    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResultContext list(DictionaryContext.Request request) {
        return getList(request);
    }

    @GetMapping("/list")
    public ResultContext getList(DictionaryContext.Request request) {
        return postList(request);
    }

    @PostMapping("/list")
    public ResultContext postList(@RequestBody DictionaryContext.Request request) {
        if (request == null) {
            request = new DictionaryContext.Request();
        }
        if (request.getSort() != null && request.getSort().isValidationFailure(DictionaryModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(DictionaryContext.Request request)",
                    "Request sort validation exception."
            );
        }
        if (request.getSearch() != null && request.getSearch().isValidationFailure(DictionaryModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(DictionaryContext.Request request)",
                    "Request search validation exception."
            );
        }
        final DictionaryContext.ListDto result = service.list(request);
        return ResultContext.build(CopyUtil.run(result, DictionaryContext.ListVo.class));
    }

    @PostMapping()
    public ResultContext create(@RequestBody DictionaryContext.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(DictionaryContext.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getType() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(DictionaryContext.Request request)",
                    "Request parameter [type] exception."
            );
        }
        if (request.getKey() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(DictionaryContext.Request request)",
                    "Request parameter [key] exception."
            );
        }
        if (request.getValue() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(DictionaryContext.Request request)",
                    "Request parameter [value] exception."
            );
        }
        if (request.getLanguage() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(DictionaryContext.Request request)",
                    "Request parameter [language] exception."
            );
        }
        final DictionaryContext.Dto result = service.create(request);
        return ResultContext.build(CopyUtil.run(result, DictionaryContext.Vo.class));
    }

    @PutMapping("/{id}")
    public ResultContext update(@PathVariable Integer id, @RequestBody DictionaryContext.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, DictionaryContext.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getType() == null
                && request.getKey() == null
                && request.getValue() == null
                && request.getLanguage() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, DictionaryContext.Request request)",
                    "Request parameter exception."
            );
        }
        final DictionaryContext.Dto result = service.update(request.setId(id));
        return ResultContext.build(CopyUtil.run(result, DictionaryContext.Vo.class));
    }

    @GetMapping("/{id}")
    public ResultContext get(@PathVariable Integer id) {
        final DictionaryContext.Dto result = service.get(new DictionaryContext.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, DictionaryContext.Vo.class));
    }

    @DeleteMapping("/{id}")
    public ResultContext delete(@PathVariable Integer id) {
        final DictionaryContext.Dto result = service.delete(new DictionaryContext.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, DictionaryContext.Vo.class));
    }

    @GetMapping("/option")
    public ResultContext getOptionList(DictionaryContext.Option.Request request) {
        return postOptionList(request);
    }

    @PostMapping("/option")
    public ResultContext postOptionList(@RequestBody DictionaryContext.Option.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun postType(DictionaryContext.Type.Request request)",
                    "Request sort validation exception."
            );
        }
        if (request.getType() == null && (request.getTypes() == null || request.getTypes().isEmpty())) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun postType(DictionaryContext.Type.Request request)",
                    "Request sort validation exception."
            );
        }
        if (request.getTypes() == null) {
            request.setTypes(new ArrayList<>());
        }
        if (request.getType() != null) {
            request.getTypes().add(request.getType());
        }
        return ResultContext.build(service.option(request).getData());
    }

}
