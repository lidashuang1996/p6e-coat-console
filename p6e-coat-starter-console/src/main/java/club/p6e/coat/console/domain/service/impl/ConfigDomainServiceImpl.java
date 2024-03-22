package club.p6e.coat.console.domain.service.impl;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.ConfigTypeAggregate;
import club.p6e.coat.console.domain.aggregate.ConfigListAggregate;
import club.p6e.coat.console.domain.entity.ConfigEntity;
import club.p6e.coat.console.domain.identifier.ConfigId;
import club.p6e.coat.console.domain.identifier.ConfigType;
import club.p6e.coat.console.domain.repository.ConfigDomainRepository;
import club.p6e.coat.console.domain.service.ConfigDomainService;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import org.springframework.stereotype.Component;

/**
 * 配置领域服务实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class ConfigDomainServiceImpl implements ConfigDomainService {

    /**
     * 配置领域存储库
     */
    private final ConfigDomainRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 配置领域存储库
     */
    public ConfigDomainServiceImpl(ConfigDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConfigEntity get(ConfigId id) {
        final ConfigEntity entity = repository.findById(id);
        if (entity == null) {
            throw GlobalExceptionContext.executeConfigDataNotExistException(
                    this.getClass(),
                    "fun get(ConfigId id).",
                    "Request get data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public ConfigEntity delete(ConfigId id) {
        final ConfigEntity entity = repository.delete(id);
        if (entity == null) {
            throw GlobalExceptionContext.executeConfigDataNotExistException(
                    this.getClass(),
                    "fun delete(ConfigId id).",
                    "Request delete data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public ConfigEntity create(ConfigEntity ce) {
        if (ce.getId() == null) {
            return repository.save(ce);
        } else {
            throw GlobalExceptionContext.executeConfigDataExistException(
                    this.getClass(),
                    "fun create(ConfigEntity ce).",
                    "Request create data already exist."
            );
        }
    }

    @Override
    public ConfigEntity update(ConfigEntity ce) {
        final ConfigEntity entity = repository.save(ce);
        if (entity == null) {
            throw GlobalExceptionContext.executeConfigDataNotExistException(
                    this.getClass(),
                    "fun update(ConfigEntity ce).",
                    "Request update data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public ConfigListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination) {
        return repository.list(search, sort, pagination);
    }

    @Override
    public ConfigTypeAggregate getTypeData(ConfigType type) {
        return repository.findByType(type);
    }

}
