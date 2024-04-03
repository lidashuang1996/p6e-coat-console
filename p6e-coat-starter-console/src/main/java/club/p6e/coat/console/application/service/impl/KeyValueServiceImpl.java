package club.p6e.coat.console.application.service.impl;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.KeyValueService;
import club.p6e.coat.console.domain.aggregate.KeyValueListAggregate;
import club.p6e.coat.console.domain.entity.KeyValueEntity;
import club.p6e.coat.console.domain.identifier.KvId;
import club.p6e.coat.console.domain.identifier.KvKey;
import club.p6e.coat.console.domain.service.KeyValueDomainService;
import club.p6e.coat.console.infrastructure.context.KeyValueContext;
import club.p6e.coat.console.infrastructure.model.KeyValueModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class KeyValueServiceImpl implements KeyValueService {

    private final KeyValueDomainService service;

    public KeyValueServiceImpl(KeyValueDomainService service) {
        this.service = service;
    }

    @Override
    public KeyValueContext.Dto get(KeyValueContext.Request request) {
        return CopyUtil.run(service.get(new KvId(request.getId())), KeyValueContext.Dto.class);
    }

    @Override
    public KeyValueContext.KeyValueMap keys(KeyValueContext.Request request) {
        final List<String> list = new ArrayList<>();
        final Map<String, String> result = new HashMap<>();
        if (request.getKey() != null) {
            list.add(request.getKey());
        }
        if (request.getKeys() != null) {
            list.addAll(request.getKeys());
        }
        for (final String item : list) {
            final KeyValueEntity kve = service.get(new KvKey(item));
            if (kve.getKey() != null && kve.getValue() != null) {
                result.put(kve.getKey(), kve.getValue());
            }
        }
        return new KeyValueContext.KeyValueMap().setKv(result);
    }

    @Override
    public KeyValueContext.Dto create(KeyValueContext.Request request) {
        return CopyUtil.run(service.create(new KeyValueEntity(
                null, CopyUtil.run(request, KeyValueModel.class))
        ).convertToModel(), KeyValueContext.Dto.class);
    }

    @Override
    public KeyValueContext.Dto update(KeyValueContext.Request request) {
        return CopyUtil.run(service.update(new KeyValueEntity(
                new KvId(request.getId()), CopyUtil.run(request, KeyValueModel.class))
        ).convertToModel(), KeyValueContext.Dto.class);
    }

    @Override
    public KeyValueContext.Dto delete(KeyValueContext.Request request) {
        return CopyUtil.run(service.delete(new KvId(request.getId())).convertToModel(), KeyValueContext.Dto.class);
    }

    @Override
    public KeyValueContext.ListDto list(KeyValueContext.Request request) {
        final KeyValueListAggregate aggregate = service.list(
                request.getSearch(), request.getSort(), request.getPaginationContext());
        final KeyValueContext.ListDto result = new KeyValueContext.ListDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(aggregate.getList().stream().map(m ->
                CopyUtil.run(m, KeyValueContext.Item.class).setId(m.id().getId())).toList());
        return result;
    }

}
