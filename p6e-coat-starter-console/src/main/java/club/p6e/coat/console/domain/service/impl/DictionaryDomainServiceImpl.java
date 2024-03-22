package club.p6e.coat.console.domain.service.impl;

import club.p6e.coat.common.pagination.PaginationContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.console.domain.aggregate.DictionaryListAggregate;
import club.p6e.coat.console.domain.aggregate.DictionaryTypeAggregate;
import club.p6e.coat.console.domain.entity.DictionaryEntity;
import club.p6e.coat.console.domain.identifier.DictionaryId;
import club.p6e.coat.console.domain.identifier.DictionaryType;
import club.p6e.coat.console.domain.repository.DictionaryDomainRepository;
import club.p6e.coat.console.domain.service.DictionaryDomainService;
import club.p6e.coat.console.infrastructure.error.GlobalExceptionContext;
import org.springframework.stereotype.Component;

/**
 * 字典领域服务实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Component
public class DictionaryDomainServiceImpl implements DictionaryDomainService {

    /**
     * 字典领域存储库
     */
    private final DictionaryDomainRepository repository;

    /**
     * 构造方法初始化
     *
     * @param repository 字典领域存储库
     */
    public DictionaryDomainServiceImpl(DictionaryDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public DictionaryEntity get(DictionaryId id) {
        final DictionaryEntity entity = repository.findById(id);
        if (entity == null) {
            throw GlobalExceptionContext.executeDictionaryDataNotExistException(
                    this.getClass(),
                    "fun get(DictionaryId id).",
                    "Request get data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public DictionaryEntity delete(DictionaryId id) {
        final DictionaryEntity entity = repository.delete(id);
        if (entity == null) {
            throw GlobalExceptionContext.executeDictionaryDataNotExistException(
                    this.getClass(),
                    "fun delete(DictionaryId id).",
                    "Request delete data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public DictionaryEntity create(DictionaryEntity de) {
        if (de.getId() == null) {
            return repository.save(de);
        } else {
            throw GlobalExceptionContext.executeDictionaryDataExistException(
                    this.getClass(),
                    "fun create(DictionaryEntity de).",
                    "Request create data already exist."
            );
        }
    }

    @Override
    public DictionaryEntity update(DictionaryEntity de) {
        final DictionaryEntity entity = repository.save(de);
        if (entity == null) {
            throw GlobalExceptionContext.executeDictionaryDataNotExistException(
                    this.getClass(),
                    "fun update(DictionaryEntity de).",
                    "Request update data does not exist."
            );
        } else {
            return entity;
        }
    }

    @Override
    public DictionaryListAggregate list(SearchableContext search, SortableContext sort, PaginationContext pagination) {
        return repository.list(search, sort, pagination);
    }

    @Override
    public DictionaryTypeAggregate getTypeData(DictionaryType dt) {
        return repository.findByType(dt);
    }
}
