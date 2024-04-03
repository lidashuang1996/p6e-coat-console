package club.p6e.coat.console.domain.repository.impl;

import club.p6e.coat.common.pagination.JpaPaginationConverter;
import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.JpaSearchableConverter;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.JpaSortableConverter;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.aggregate.KeyValueListAggregate;
import club.p6e.coat.console.domain.entity.KeyValueEntity;
import club.p6e.coat.console.domain.identifier.KvId;
import club.p6e.coat.console.domain.identifier.KvKey;
import club.p6e.coat.console.domain.repository.KeyValueDomainRepository;
import club.p6e.coat.console.infrastructure.model.KeyValueModel;
import club.p6e.coat.console.infrastructure.repository.KeyValueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class KeyValueDomainRepositoryImpl implements KeyValueDomainRepository {

    /**
     * KEY/VALUE 存储库
     */
    private final KeyValueRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository KEY/VALUE 存储库
     */
    public KeyValueDomainRepositoryImpl(KeyValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public KeyValueEntity save(KeyValueEntity kve) {
        if (kve.id() == null) {
            boolean bool = true;
            if (kve.getKey() != null) {
                bool = repository.findOne((Specification<KeyValueModel>)
                        (rt, qy, cb) -> cb.equal(rt.get(KeyValueModel.KEY), kve.getKey())).isEmpty();
            }
            if (bool) {
                final KeyValueModel kvm = repository.saveAndFlush(kve.convertToModel());
                return new KeyValueEntity(new KvId(kvm.getId()), kvm);
            } else {
                return null;
            }
        } else {
            boolean bool = true;
            final Optional<KeyValueModel> optional = repository.findById(kve.id().getId());
            if (kve.getKey() != null) {
                bool = repository.findOne(
                        (Specification<KeyValueModel>) (rt, qy, cb) -> cb.and(
                                cb.equal(rt.get(KeyValueModel.KEY), kve.getKey()),
                                cb.notEqual(rt.get(KeyValueModel.ID), kve.getId())
                        )
                ).isEmpty();
            }
            if (bool && optional.isPresent()) {
                final KeyValueModel kvm = optional.get();
                repository.saveAndFlush(CopyUtil.run(kve.convertToModel(), kvm));
                return new KeyValueEntity(new KvId(kvm.getId()), kvm);
            } else {
                return null;
            }
        }
    }

    @Override
    public KeyValueEntity delete(KvId id) {
        final Optional<KeyValueModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final KeyValueModel kvm = optional.get();
            repository.delete(kvm);
            return new KeyValueEntity(new KvId(kvm.getId()), kvm);
        }
    }

    @Override
    public KeyValueEntity findById(KvId id) {
        final Optional<KeyValueModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final KeyValueModel kvm = optional.get();
            return new KeyValueEntity(new KvId(kvm.getId()), kvm);
        }
    }

    @Override
    public KeyValueListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination) {
        final PageRequest pr = JpaPaginationConverter.execute(pagination);
        final Page<KeyValueModel> data = repository.findAll((Specification<KeyValueModel>) (rt, qy, cb) -> {
            JpaSortableConverter.injectSort(rt, qy, cb, sortable, Sort.by(Sort.Order.asc(KeyValueModel.ID)));
            JpaSearchableConverter.injectSearch(rt, qy, cb, searchable);
            return qy.getRestriction();
        }, pr);
        return new KeyValueListAggregate(
                (pr.getPageNumber() + 1), pr.getPageSize(), data.getTotalElements(),
                data.getContent().stream().map(m -> new KeyValueEntity(new KvId(m.getId()), m)).collect(Collectors.toList())
        );
    }

    @Override
    public KeyValueEntity findByKey(KvKey key) {
        final Optional<KeyValueModel> optional = repository.findOne(
                (Specification<KeyValueModel>) (rt, qy, cb) -> cb.equal(rt.get(KeyValueModel.KEY), key.getId()));
        if (optional.isEmpty()) {
            return null;
        } else {
            final KeyValueModel kvm = optional.get();
            return new KeyValueEntity(new KvId(kvm.getId()), kvm);
        }
    }
}
