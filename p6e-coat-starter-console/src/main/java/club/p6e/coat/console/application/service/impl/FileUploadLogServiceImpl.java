package club.p6e.coat.console.application.service.impl;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.FileUploadLogService;
import club.p6e.coat.console.domain.aggregate.FileUploadLogDetailsAggregate;
import club.p6e.coat.console.domain.aggregate.FileUploadLogListAggregate;
import club.p6e.coat.console.domain.identifier.FileUploadLogId;
import club.p6e.coat.console.domain.service.FileUploadLogDomainService;
import club.p6e.coat.console.infrastructure.context.FileUploadLogContext;
import org.springframework.stereotype.Service;

/**
 * 文件上传日志服务实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Service
public class FileUploadLogServiceImpl implements FileUploadLogService {

    /**
     * 文件上传日志领域服务
     */
    private final FileUploadLogDomainService service;

    /**
     * 构造方法初始化
     *
     * @param service 文件上传日志领域服务
     */
    public FileUploadLogServiceImpl(FileUploadLogDomainService service) {
        this.service = service;
    }

    @Override
    public FileUploadLogContext.Dto get(FileUploadLogContext.Request request) {
        final FileUploadLogDetailsAggregate aggregate = service.get(new FileUploadLogId(request.getId()));
        final FileUploadLogContext.Dto result = CopyUtil.run(aggregate.getData(), FileUploadLogContext.Dto.class);
        result.setList(CopyUtil.runList(aggregate.getList(), FileUploadLogContext.ChunkModel.class));
        return result;
    }

    @Override
    public FileUploadLogContext.ListDto list(FileUploadLogContext.Request request) {
        final FileUploadLogListAggregate aggregate = service.list(
                request.getSearch(), request.getSort(), request.getPaginationContext());
        final FileUploadLogContext.ListDto result = new FileUploadLogContext.ListDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(aggregate.getList().stream().map(m ->
                CopyUtil.run(m, FileUploadLogContext.Item.class).setId(m.id().getId())).toList());
        return result;
    }
}
