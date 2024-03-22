package club.p6e.coat.console.domain.service;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.FileUploadLogDetailsAggregate;
import club.p6e.coat.console.domain.aggregate.FileUploadLogListAggregate;
import club.p6e.coat.console.domain.identifier.FileUploadLogId;

/**
 * 文件上传日志领域服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface FileUploadLogDomainService {

    /**
     * 读取
     *
     * @param id ID 对象
     * @return 实体对象
     */
    FileUploadLogDetailsAggregate get(FileUploadLogId id);

    /**
     * 列表
     *
     * @param search     搜索上下文
     * @param sort       排序上下文
     * @param pagination 分页上下文
     * @return 列表聚合对象
     */
    FileUploadLogListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination);

}
