package club.p6e.coat.console.domain.repository.impl;

import club.p6e.coat.common.pagination.JpaPaginationConverter;
import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.JpaSearchableConverter;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.JpaSortableConverter;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.aggregate.ConfigListAggregate;
import club.p6e.coat.console.domain.aggregate.ConfigTypeAggregate;
import club.p6e.coat.console.domain.entity.ConfigEntity;
import club.p6e.coat.console.domain.identifier.ConfigId;
import club.p6e.coat.console.domain.identifier.ConfigType;
import club.p6e.coat.console.domain.repository.ConfigDomainRepository;
import club.p6e.coat.console.infrastructure.model.ConfigModel;
import club.p6e.coat.console.infrastructure.repository.ConfigRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 配置领域存储库实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class ConfigDomainRepositoryImpl implements ConfigDomainRepository {

    /**
     * 配置存储库
     */
    private final ConfigRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 配置存储库
     */
    public ConfigDomainRepositoryImpl(ConfigRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConfigEntity save(ConfigEntity ce) {
        if (ce.id() == null) {
            final ConfigModel cm = repository.saveAndFlush(ce.convertToModel());
            return new ConfigEntity(new ConfigId(cm.getId()), cm);
        } else {
            final Optional<ConfigModel> optional = repository.findById(ce.id().getId());
            if (optional.isEmpty()) {
                return null;
            } else {
                final ConfigModel cm = optional.get();
                repository.saveAndFlush(CopyUtil.run(ce.convertToModel(), cm));
                return new ConfigEntity(new ConfigId(cm.getId()), cm);
            }
        }
    }

    @Override
    public ConfigEntity delete(ConfigId id) {
        final Optional<ConfigModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final ConfigModel cm = optional.get();
            repository.delete(cm);
            return new ConfigEntity(new ConfigId(cm.getId()), cm);
        }
    }

    @Override
    public ConfigEntity findById(ConfigId id) {
        final Optional<ConfigModel> optional = repository.findById(id.getId());
        if (optional.isEmpty()) {
            return null;
        } else {
            final ConfigModel cm = optional.get();
            return new ConfigEntity(new ConfigId(cm.getId()), cm);
        }
    }

    @Override
    public ConfigTypeAggregate findByType(ConfigType type) {
        final List<ConfigModel> list = repository.findAll(
                (Specification<ConfigModel>) (rt, qy, cb) -> cb.equal(rt.get(ConfigModel.TYPE), type));
        return new ConfigTypeAggregate(type, list.stream().map(m ->
                new ConfigEntity(new ConfigId(m.getId()), m)).toList());
    }

    @Override
    public ConfigListAggregate list(SearchableContext searchable, SortableContext sortable, PaginationContext pagination) {
        final PageRequest pr = JpaPaginationConverter.execute(pagination);
        final Page<ConfigModel> data = repository.findAll((Specification<ConfigModel>) (rt, qy, cb) -> {
            JpaSortableConverter.injectSort(rt, qy, cb, sortable, Sort.by(Sort.Order.asc(ConfigModel.ID)));
            JpaSearchableConverter.injectSearch(rt, qy, cb, searchable);
            return qy.getRestriction();
        }, pr);
        return new ConfigListAggregate(
                (pr.getPageNumber() + 1), pr.getPageSize(), data.getTotalElements(),
                data.getContent().stream().map(m -> new ConfigEntity(new ConfigId(m.getId()), m)).collect(Collectors.toList())
        );
    }

}
