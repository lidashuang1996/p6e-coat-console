package club.p6e.coat.console.controller;

import club.p6e.coat.common.context.ResultContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.FileUploadLogService;
import club.p6e.coat.console.infrastructure.context.FileUploadLogContext;
import org.springframework.web.bind.annotation.*;

/**
 * 文件上传日志接口
 *
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/file/upload/log")
public class FileUploadLogController {

    /**
     * 文件上传日志服务
     */
    private final FileUploadLogService service;

    /**
     * 构造方法初始化
     *
     * @param service 文件上传日志服务
     */
    public FileUploadLogController(FileUploadLogService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResultContext list(FileUploadLogContext.Request request) {
        return getList(request);
    }

    @GetMapping("/list")
    public ResultContext getList(FileUploadLogContext.Request request) {
        return postList(request);
    }

    @PostMapping("/list")
    public ResultContext postList(@RequestBody FileUploadLogContext.Request request) {
        final FileUploadLogContext.ListDto result = service.list(request);
        return ResultContext.build(CopyUtil.run(result, FileUploadLogContext.ListVo.class));
    }

    @GetMapping("/{id}")
    public ResultContext get(@PathVariable Integer id) {
        final FileUploadLogContext.Dto result =
                service.get(new FileUploadLogContext.Request().setId(id));
        return ResultContext.build(CopyUtil.run(result, FileUploadLogContext.Vo.class));
    }

}
