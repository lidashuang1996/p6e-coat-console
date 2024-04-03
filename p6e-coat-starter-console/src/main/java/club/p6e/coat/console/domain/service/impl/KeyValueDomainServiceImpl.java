package club.p6e.coat.console.domain.service.impl;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.KeyValueListAggregate;
import club.p6e.coat.console.domain.entity.ConfigEntity;
import club.p6e.coat.console.domain.entity.KeyValueEntity;
import club.p6e.coat.console.domain.identifier.KvId;
import club.p6e.coat.console.domain.identifier.KvKey;
import club.p6e.coat.console.domain.repository.KeyValueDomainRepository;
import club.p6e.coat.console.domain.service.KeyValueDomainService;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import org.springframework.stereotype.Component;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class KeyValueDomainServiceImpl implements KeyValueDomainService {

    private final KeyValueDomainRepository repository;

    public KeyValueDomainServiceImpl(KeyValueDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public KeyValueEntity get(KvId id) {
        final KeyValueEntity entity = repository.findById(id);
        if (entity == null) {
            throw GlobalExceptionContext.executeKeyValueDataNotExistException(
                    this.getClass(),
                    "fun get(ConfigId id).",
                    "Request get data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public KeyValueEntity delete(KvId id) {
        final KeyValueEntity entity = repository.delete(id);
        if (entity == null) {
            throw GlobalExceptionContext.executeKeyValueDataNotExistException(
                    this.getClass(),
                    "fun delete(ConfigId id).",
                    "Request delete data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public KeyValueEntity create(KeyValueEntity ce) {
        if (ce.getId() == null) {
            return repository.save(ce);
        } else {
            throw GlobalExceptionContext.executeKeyValueDataExistException(
                    this.getClass(),
                    "fun create(ConfigEntity ce).",
                    "Request create data already exist."
            );
        }
    }

    @Override
    public KeyValueEntity update(KeyValueEntity ce) {
        final KeyValueEntity entity = repository.save(ce);
        if (entity == null) {
            throw GlobalExceptionContext.executeKeyValueDataNotExistException(
                    this.getClass(),
                    "fun update(ConfigEntity ce).",
                    "Request update data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public KeyValueListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination) {
        return repository.list(search, sort, pagination);
    }

    @Override
    public KeyValueEntity get(KvKey key) {
        final KeyValueEntity entity = repository.findByKey(key);
        if (entity == null) {
            throw GlobalExceptionContext.executeKeyValueDataNotExistException(
                    this.getClass(),
                    "fun get(ConfigId id).",
                    "Request get data does not exist."
            );
        } else {
            return entity;
        }
    }

}
