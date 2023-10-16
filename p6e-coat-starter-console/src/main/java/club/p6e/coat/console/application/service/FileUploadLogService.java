package club.p6e.coat.console.application.service;

import club.p6e.coat.console.infrastructure.context.FileUploadLogContext;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface FileUploadLogService {

    FileUploadLogContext.ListDto list(FileUploadLogContext.Request request);

    FileUploadLogContext.Details.Dto details(FileUploadLogContext.Details.Request request);

    FileUploadLogContext.Storage.Dto storage(FileUploadLogContext.Storage.Request request);

}
