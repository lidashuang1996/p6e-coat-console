package club.p6e.coat.console.domain.repository.impl;

import club.p6e.coat.common.pagination.JpaPaginationConverter;
import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.JpaSearchableConverter;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.JpaSortableConverter;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.FileUploadLogDetailsAggregate;
import club.p6e.coat.console.domain.aggregate.FileUploadLogListAggregate;
import club.p6e.coat.console.domain.entity.FileUploadLogEntity;
import club.p6e.coat.console.domain.identifier.FileUploadLogId;
import club.p6e.coat.console.domain.repository.FileUploadLogDomainRepository;
import club.p6e.coat.console.infrastructure.model.FileUploadChunkModel;
import club.p6e.coat.console.infrastructure.model.FileUploadModel;
import club.p6e.coat.console.infrastructure.repository.FileUploadChunkRepository;
import club.p6e.coat.console.infrastructure.repository.FileUploadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 文件上传日志领域存储库实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class FileUploadLogDomainRepositoryImpl implements FileUploadLogDomainRepository {

    /**
     * 文件上传存储库
     */
    private final FileUploadRepository fileUploadRepository;

    /**
     * 文件上传块存储库
     */
    private final FileUploadChunkRepository fileUploadChunkRepository;

    /**
     * 构造方法初始化
     *
     * @param fileUploadRepository      文件上传存储库
     * @param fileUploadChunkRepository 文件上传块存储库
     */
    public FileUploadLogDomainRepositoryImpl(
            FileUploadRepository fileUploadRepository,
            FileUploadChunkRepository fileUploadChunkRepository
    ) {
        this.fileUploadRepository = fileUploadRepository;
        this.fileUploadChunkRepository = fileUploadChunkRepository;
    }

    @Override
    public FileUploadLogDetailsAggregate getDetails(FileUploadLogId id) {
        final Optional<FileUploadModel> optional = fileUploadRepository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final FileUploadModel fum = optional.get();
            final List<FileUploadChunkModel> list = fileUploadChunkRepository.findAll(
                    (Specification<FileUploadChunkModel>) (rt, qy, cb) ->
                            cb.equal(rt.get(FileUploadChunkModel.FID), fum.getId())
            );
            list.sort(Comparator.comparing(FileUploadChunkModel::getName));
            return new FileUploadLogDetailsAggregate(new FileUploadLogId(fum.getId()), fum, new ArrayList<>(list));
        }
    }

    @Override
    public FileUploadLogListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination) {
        final PageRequest pr = JpaPaginationConverter.execute(pagination);
        final Page<FileUploadModel> data = fileUploadRepository.findAll((Specification<FileUploadModel>) (rt, qy, cb) -> {
            JpaSortableConverter.injectSort(rt, qy, cb, sortable, Sort.by(Sort.Order.desc(FileUploadModel.ID)));
            JpaSearchableConverter.injectSearch(rt, qy, cb, searchable);
            return qy.getRestriction();
        }, pr);
        return new FileUploadLogListAggregate(
                (pr.getPageNumber() + 1), pr.getPageSize(), data.getTotalElements(),
                data.getContent().stream().map(m -> new FileUploadLogEntity(new FileUploadLogId(m.getId()), m)).collect(Collectors.toList())
        );
    }

}
