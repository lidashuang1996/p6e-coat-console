package club.p6e.coat.console.domain.repository.impl;

import club.p6e.coat.common.pagination.JpaPaginationConverter;
import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.JpaSearchableConverter;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.JpaSortableConverter;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.aggregate.PermissionUrlListAggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;
import club.p6e.coat.console.domain.repository.PermissionUrlDomainRepository;
import club.p6e.coat.console.infrastructure.model.PermissionUrlModel;
import club.p6e.coat.console.infrastructure.repository.PermissionUrlRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 权限 URL 领域存储库实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PermissionUrlDomainRepositoryImpl implements PermissionUrlDomainRepository {

    /**
     * 权限 URL 存储库
     */
    private final PermissionUrlRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 权限 URL 存储库
     */
    public PermissionUrlDomainRepositoryImpl(PermissionUrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public PermissionUrlEntity save(PermissionUrlEntity pue) {
        if (pue.id() == null) {
            final PermissionUrlModel pum = repository.saveAndFlush(pue.convertToModel());
            return new PermissionUrlEntity(new PermissionUrlId(pum.getId()), pum);
        } else {
            final Optional<PermissionUrlModel> optional = repository.findById(pue.id().getId());
            if (optional.isEmpty()) {
                return null;
            } else {
                final PermissionUrlModel pum = optional.get();
                repository.saveAndFlush(CopyUtil.run(pue.convertToModel(), pum));
                return new PermissionUrlEntity(new PermissionUrlId(pum.getId()), pum);
            }
        }
    }

    @Override
    public PermissionUrlEntity delete(PermissionUrlId id) {
        final Optional<PermissionUrlModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final PermissionUrlModel dm = optional.get();
            repository.delete(dm);
            return new PermissionUrlEntity(new PermissionUrlId(dm.getId()), dm);
        }
    }

    @Override
    public PermissionUrlEntity findById(PermissionUrlId id) {
        final Optional<PermissionUrlModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final PermissionUrlModel dm = optional.get();
            return new PermissionUrlEntity(new PermissionUrlId(dm.getId()), dm);
        }
    }

    @Override
    public PermissionUrlListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination) {
        final PageRequest pr = JpaPaginationConverter.execute(pagination);
        final Page<PermissionUrlModel> data = repository.findAll((Specification<PermissionUrlModel>) (rt, qy, cb) -> {
            JpaSortableConverter.injectSort(rt, qy, cb, sortable, Sort.by(Sort.Order.asc(PermissionUrlModel.ID)));
            JpaSearchableConverter.injectSearch(rt, qy, cb, searchable);
            return qy.getRestriction();
        }, pr);
        return new PermissionUrlListAggregate(
                (pr.getPageNumber() + 1), pr.getPageSize(), data.getTotalElements(),
                data.getContent().stream().map(m -> new PermissionUrlEntity(new PermissionUrlId(m.getId()), m)).collect(Collectors.toList())
        );
    }

}
