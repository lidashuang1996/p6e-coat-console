package club.p6e.coat.console.application.service.impl;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.DictionaryService;
import club.p6e.coat.console.domain.aggregate.DictionaryListAggregate;
import club.p6e.coat.console.domain.aggregate.DictionaryTypeAggregate;
import club.p6e.coat.console.domain.entity.DictionaryEntity;
import club.p6e.coat.console.domain.identifier.DictionaryId;
import club.p6e.coat.console.domain.identifier.DictionaryType;
import club.p6e.coat.console.domain.service.DictionaryDomainService;
import club.p6e.coat.console.infrastructure.context.DictionaryContext;
import club.p6e.coat.console.infrastructure.model.DictionaryModel;
import org.springframework.stereotype.Service;

/**
 * 字典服务实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    /**
     * 字典领域服务
     */
    private final DictionaryDomainService service;

    /**
     * 构造方法初始化
     *
     * @param service 字典领域服务
     */
    public DictionaryServiceImpl(DictionaryDomainService service) {
        this.service = service;
    }

    @Override
    public DictionaryContext.Dto get(DictionaryContext.Request request) {
        return CopyUtil.run(service.get(new DictionaryId(request.getId())).convertToModel(), DictionaryContext.Dto.class);
    }

    @Override
    public DictionaryContext.Dto create(DictionaryContext.Request request) {
        return CopyUtil.run(service.create(
                new DictionaryEntity(null, CopyUtil.run(request, DictionaryModel.class))
        ).convertToModel(), DictionaryContext.Dto.class);
    }

    @Override
    public DictionaryContext.Dto update(DictionaryContext.Request request) {
        return CopyUtil.run(service.update(
                new DictionaryEntity(new DictionaryId(request.getId()), CopyUtil.run(request, DictionaryModel.class))
        ).convertToModel(), DictionaryContext.Dto.class);
    }

    @Override
    public DictionaryContext.Dto delete(DictionaryContext.Request request) {
        return CopyUtil.run(service.delete(new DictionaryId(request.getId())).convertToModel(), DictionaryContext.Dto.class);
    }

    @Override
    public DictionaryContext.ListDto list(DictionaryContext.Request request) {
        final DictionaryListAggregate aggregate = service.list(
                request.getSearch(), request.getSort(), request.getPaginationContext());
        final DictionaryContext.ListDto result = new DictionaryContext.ListDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(aggregate.getList().stream().map(m ->
                CopyUtil.run(m, DictionaryContext.Item.class).setId(m.id().getId())).toList());
        return result;
    }

    @Override
    public DictionaryContext.Option.Dto option(DictionaryContext.Option.Request request) {
        final DictionaryContext.Option.Dto result = new DictionaryContext.Option.Dto();
        for (final String type : request.getTypes()) {
            final DictionaryTypeAggregate aggregate = service.getTypeData(new DictionaryType(type));
            result.getData().put(aggregate.type(), aggregate.data());
        }
        return result;
    }
}
