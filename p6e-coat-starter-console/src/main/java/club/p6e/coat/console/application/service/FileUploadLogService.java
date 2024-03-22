package club.p6e.coat.console.application.service;

import club.p6e.coat.console.infrastructure.context.FileUploadLogContext;

/**
 * 文件上传日志服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface FileUploadLogService {

    /**
     * 读取
     *
     * @param request 请求对象
     * @return 结果对象
     */
    FileUploadLogContext.Dto get(FileUploadLogContext.Request request);

    /**
     * 列表
     *
     * @param request 请求对象
     * @return 结果对象
     */
    FileUploadLogContext.ListDto list(FileUploadLogContext.Request request);

}
