package club.p6e.coat.console.domain.repository.impl;

import club.p6e.coat.common.pagination.JpaPaginationConverter;
import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.JpaSearchableConverter;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.JpaSortableConverter;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.aggregate.DictionaryListAggregate;
import club.p6e.coat.console.domain.aggregate.DictionaryTypeAggregate;
import club.p6e.coat.console.domain.entity.DictionaryEntity;
import club.p6e.coat.console.domain.identifier.DictionaryId;
import club.p6e.coat.console.domain.identifier.DictionaryType;
import club.p6e.coat.console.domain.repository.DictionaryDomainRepository;
import club.p6e.coat.console.infrastructure.model.DictionaryModel;
import club.p6e.coat.console.infrastructure.repository.DictionaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 字典领域存储库实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class DictionaryDomainRepositoryImpl implements DictionaryDomainRepository {

    /**
     * 字典存储库
     */
    private final DictionaryRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 字典存储库
     */
    public DictionaryDomainRepositoryImpl(DictionaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public DictionaryEntity save(DictionaryEntity de) {
        if (de.id() == null) {
            final DictionaryModel dm = repository.saveAndFlush(de.convertToModel());
            return new DictionaryEntity(new DictionaryId(dm.getId()), dm);
        } else {
            final Optional<DictionaryModel> optional = repository.findById(de.id().getId());
            if (optional.isEmpty()) {
                return null;
            } else {
                final DictionaryModel dm = optional.get();
                repository.saveAndFlush(CopyUtil.run(de.convertToModel(), dm));
                return new DictionaryEntity(new DictionaryId(dm.getId()), dm);
            }
        }
    }

    @Override
    public DictionaryEntity delete(DictionaryId id) {
        final Optional<DictionaryModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final DictionaryModel dm = optional.get();
            repository.delete(dm);
            return new DictionaryEntity(new DictionaryId(dm.getId()), dm);
        }
    }

    @Override
    public DictionaryEntity findById(DictionaryId id) {
        final Optional<DictionaryModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final DictionaryModel dm = optional.get();
            return new DictionaryEntity(new DictionaryId(dm.getId()), dm);
        }
    }

    @Override
    public DictionaryTypeAggregate findByType(DictionaryType type) {
        final List<DictionaryModel> list = repository.findAll(
                (Specification<DictionaryModel>) (rt, qy, cb) -> cb.equal(rt.get(DictionaryModel.TYPE), type));
        return new DictionaryTypeAggregate(type, list.stream().map(m ->
                new DictionaryEntity(new DictionaryId(m.getId()), m)).toList());
    }

    @Override
    public DictionaryListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination) {
        final PageRequest pr = JpaPaginationConverter.execute(pagination);
        final Page<DictionaryModel> data = repository.findAll((Specification<DictionaryModel>) (rt, qy, cb) -> {
            JpaSortableConverter.injectSort(rt, qy, cb, sortable, Sort.by(Sort.Order.asc(DictionaryModel.ID)));
            JpaSearchableConverter.injectSearch(rt, qy, cb, searchable);
            return qy.getRestriction();
        }, pr);
        return new DictionaryListAggregate(
                (pr.getPageNumber() + 1), pr.getPageSize(), data.getTotalElements(),
                data.getContent().stream().map(m -> new DictionaryEntity(new DictionaryId(m.getId()), m)).collect(Collectors.toList())
        );
    }

}
