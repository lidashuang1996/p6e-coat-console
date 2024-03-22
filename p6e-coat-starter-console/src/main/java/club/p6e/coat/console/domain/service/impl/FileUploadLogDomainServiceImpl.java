package club.p6e.coat.console.domain.service.impl;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.FileUploadLogDetailsAggregate;
import club.p6e.coat.console.domain.aggregate.FileUploadLogListAggregate;
import club.p6e.coat.console.domain.identifier.FileUploadLogId;
import club.p6e.coat.console.domain.repository.FileUploadLogDomainRepository;
import club.p6e.coat.console.domain.service.FileUploadLogDomainService;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import org.springframework.stereotype.Component;

/**
 * 文件上传日志领域服务实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class FileUploadLogDomainServiceImpl implements FileUploadLogDomainService {

    /**
     * 文件上传日志领域存储库
     */
    private final FileUploadLogDomainRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 文件上传日志领域存储库
     */
    public FileUploadLogDomainServiceImpl(FileUploadLogDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public FileUploadLogDetailsAggregate get(FileUploadLogId id) {
        final FileUploadLogDetailsAggregate aggregate = repository.getDetails(id);
        if (aggregate == null) {
            throw GlobalExceptionContext.executeFileUploadLogDataNotExistException(
                    this.getClass(),
                    "fun get(FileUploadLogId id).",
                    "Request get data does not exist."
            );
        } else {
            return aggregate;
        }
    }

    @Override
    public FileUploadLogListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination) {
        return repository.list(search, sort, pagination);
    }

}
