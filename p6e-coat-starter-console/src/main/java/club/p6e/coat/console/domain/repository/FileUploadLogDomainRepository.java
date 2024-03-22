package club.p6e.coat.console.domain.repository;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.EmptyRepository;
import club.p6e.coat.console.domain.aggregate.FileUploadLogDetailsAggregate;
import club.p6e.coat.console.domain.aggregate.FileUploadLogListAggregate;
import club.p6e.coat.console.domain.entity.FileUploadLogEntity;
import club.p6e.coat.console.domain.identifier.FileUploadLogId;

/**
 * 文件上传日志领域存储库
 *
 * @author lidashuang
 * @version 1.0
 */
public interface FileUploadLogDomainRepository extends EmptyRepository<FileUploadLogEntity, FileUploadLogId> {

    /**
     * 详情
     *
     * @param id ID 对象
     * @return 详情聚合根
     */
    FileUploadLogDetailsAggregate getDetails(FileUploadLogId id);

    /**
     * 列表
     *
     * @param searchable 搜索对象
     * @param sortable   排序对象
     * @param pagination 分页对象
     * @return 列表聚合根
     */
    FileUploadLogListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination);

}
