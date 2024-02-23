package club.p6e.coat.console.controller;

import club.p6e.coat.common.context.ResultContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.PermissionServer;
import club.p6e.coat.console.error.GlobalExceptionContext;
import club.p6e.coat.console.infrastructure.context.PermissionContext;
import club.p6e.coat.console.infrastructure.model.PermissionUrlGroupModel;
import club.p6e.coat.console.infrastructure.model.PermissionUrlModel;
import org.springframework.web.bind.annotation.*;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionServer server;

    public PermissionController(PermissionServer server) {
        this.server = server;
    }

    @GetMapping("/url")
    public ResultContext urlList(PermissionContext.Url.Request request) {
        return getUrlList(request);
    }

    @GetMapping("/url/list")
    public ResultContext getUrlList(PermissionContext.Url.Request request) {
        return postUrlList(request);
    }

    @PostMapping("/url/list")
    public ResultContext postUrlList(@RequestBody PermissionContext.Url.Request request) {
        if (request != null
                && request.getSort() != null
                && !request.getSort().validation(PermissionUrlModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(PermissionContext.Url.Request request)",
                    "Request sort validation exception."
            );
        }
        if (request != null
                && request.getSearch() != null
                && !request.getSearch().validation(PermissionUrlModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(PermissionContext.Url.Request request)",
                    "Request search validation exception."
            );
        }
        final PermissionContext.Url.ListDto result = server.urlList(request);
        return ResultContext.build(CopyUtil.run(result, PermissionContext.Url.ListVo.class));
    }

    @PostMapping("/url")
    public ResultContext urlCreate(@RequestBody PermissionContext.Url.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.Url.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getUrl() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.Url.Request request)",
                    "Request parameter [url] exception."
            );
        }
        if (request.getBaseUrl() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.Url.Request request)",
                    "Request parameter [baseUrl] exception."
            );
        }
        if (request.getMethod() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.Url.Request request)",
                    "Request parameter [method] exception."
            );
        }
        if (request.getConfig() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.Url.Request request)",
                    "Request parameter [config] exception."
            );
        }
        if (request.getName() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.Url.Request request)",
                    "Request parameter [name] exception."
            );
        }
        final PermissionContext.Url.Dto result = server.urlCreate(request);
        return ResultContext.build(CopyUtil.run(result, PermissionContext.Url.Vo.class));
    }

    @PutMapping("/url/{id}")
    public ResultContext urlUpdate(@PathVariable Integer id, @RequestBody PermissionContext.Url.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, PermissionContext.Url.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getUrl() == null
                && request.getName() == null
                && request.getDescription() == null
                && request.getMethod() == null
                && request.getConfig() == null
                && request.getBaseUrl() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, PermissionContext.Url.Request request)",
                    "Request parameter exception."
            );
        }
        final PermissionContext.Url.Dto result = server.urlUpdate(request.setId(id));
        return ResultContext.build(CopyUtil.run(result, PermissionContext.Url.Vo.class));
    }

    @GetMapping("/url/{id}")
    public ResultContext urlGet(@PathVariable Integer id) {
        final PermissionContext.Url.Dto result = server.urlGet(new PermissionContext.Url.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, PermissionContext.Url.Vo.class));
    }

    @DeleteMapping("/url/{id}")
    public ResultContext urlDelete(@PathVariable Integer id) {
        final PermissionContext.Url.Dto result = server.urlDelete(new PermissionContext.Url.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, PermissionContext.Url.Vo.class));
    }

    @GetMapping("/url/details/{id}")
    public ResultContext urlDetails(@PathVariable Integer id) {
        final PermissionContext.Url.Dto result = server.urlDetails(new PermissionContext.Url.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, PermissionContext.Url.Vo.class));
    }


    // -------------------------

    @GetMapping("/url/group")
    public ResultContext urlGroupList(PermissionContext.UrlGroup.Request request) {
        return getUrlGroupList(request);
    }

    @GetMapping("/url/group/list")
    public ResultContext getUrlGroupList(PermissionContext.UrlGroup.Request request) {
        return postUrlGroupList(request);
    }

    @PostMapping("/url/group/list")
    public ResultContext postUrlGroupList(@RequestBody PermissionContext.UrlGroup.Request request) {
        if (request != null
                && request.getSort() != null
                && !request.getSort().validation(PermissionUrlGroupModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(PermissionContext.UrlGroup.Request request)",
                    "Request sort validation exception."
            );
        }
        if (request != null
                && request.getSearch() != null
                && !request.getSearch().validation(PermissionUrlGroupModel.class)) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun list(PermissionContext.UrlGroup.Request request)",
                    "Request search validation exception."
            );
        }
        final PermissionContext.UrlGroup.ListDto result = server.urlGroupList(request);
        return ResultContext.build(CopyUtil.run(result, PermissionContext.UrlGroup.ListVo.class));
    }

    @PostMapping("/url/group")
    public ResultContext urlGroupCreate(@RequestBody PermissionContext.UrlGroup.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.UrlGroup.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getMark() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.UrlGroup.Request request)",
                    "Request parameter [mark] exception."
            );
        }
        if (request.getWeight() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.UrlGroup.Request request)",
                    "Request parameter [weight] exception."
            );
        }
        if (request.getName() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun create(PermissionContext.UrlGroup.Request request)",
                    "Request parameter [name] exception."
            );
        }
        final PermissionContext.UrlGroup.Dto result = server.urlGroupCreate(request);
        return ResultContext.build(CopyUtil.run(result, PermissionContext.UrlGroup.Vo.class));
    }

    @PutMapping("/url/group/{id}")
    public ResultContext urlGroupUpdate(@PathVariable Integer id, @RequestBody PermissionContext.UrlGroup.Request request) {
        if (request == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, PermissionContext.UrlGroup.Request request)",
                    "Request parameter exception."
            );
        }
        if (request.getName() == null
                && request.getDescription() == null
                && request.getMark() == null
                && request.getWeight() == null) {
            throw GlobalExceptionContext.executeParameterException(
                    this.getClass(),
                    "fun update(Integer id, PermissionContext.UrlGroup.Request request)",
                    "Request parameter exception."
            );
        }
        final PermissionContext.UrlGroup.Dto result = server.urlGroupUpdate(request.setId(id));
        return ResultContext.build(CopyUtil.run(result, PermissionContext.UrlGroup.Vo.class));
    }

    @GetMapping("/url/group/{id}")
    public ResultContext urlGroupGet(@PathVariable Integer id) {
        final PermissionContext.UrlGroup.Dto result = server.urlGroupGet(new PermissionContext.UrlGroup.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, PermissionContext.UrlGroup.Vo.class));
    }

    @DeleteMapping("/url/group/{id}")
    public ResultContext urlGroupDelete(@PathVariable Integer id) {
        final PermissionContext.UrlGroup.Dto result = server.urlGroupDelete(new PermissionContext.UrlGroup.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, PermissionContext.UrlGroup.Vo.class));
    }

    @GetMapping("/url/group/details/{id}")
    public ResultContext urlGroupDetails(@PathVariable Integer id, PermissionContext.UrlGroup.Details.Request request) {
        if (request == null) {
            request = new PermissionContext.UrlGroup.Details.Request();
        }
        final PermissionContext.UrlGroup.Details.Dto result = server.urlGroupDetails(request.setId(id));
        return ResultContext.build(CopyUtil.run(result, PermissionContext.UrlGroup.Details.Vo.class));
    }

}
