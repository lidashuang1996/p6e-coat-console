package club.p6e.coat.console.application.service.impl;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.ConfigService;
import club.p6e.coat.console.domain.aggregate.ConfigListAggregate;
import club.p6e.coat.console.domain.entity.ConfigEntity;
import club.p6e.coat.console.infrastructure.context.ConfigContext;
import club.p6e.coat.console.infrastructure.model.ConfigModel;
import org.springframework.stereotype.Service;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Override
    public ConfigContext.ListDto list(ConfigContext.Request request) {
        final ConfigListAggregate aggregate = ConfigListAggregate.search(
                request.getPage(), request.getSize(), request.getQuery(),
                request.getSort(), request.getSearch()
        );
        final ConfigContext.ListDto result = new ConfigContext.ListDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(CopyUtil.runList(aggregate.getList(), ConfigContext.Item.class));
        return result;
    }

    @Override
    public ConfigContext.Dto create(ConfigContext.Request request) {
        return CopyUtil.run(ConfigEntity.create(
                CopyUtil.run(request, ConfigModel.class)
        ).getModel(), ConfigContext.Dto.class);
    }

    @Override
    public ConfigContext.Dto get(ConfigContext.Request request) {
        return CopyUtil.run(ConfigEntity.findById(request.getId()).getModel(), ConfigContext.Dto.class);
    }

    @Override
    public ConfigContext.Dto delete(ConfigContext.Request request) {
        return CopyUtil.run(ConfigEntity.findById(request.getId()).delete().getModel(), ConfigContext.Dto.class);
    }

    @Override
    public ConfigContext.Dto update(ConfigContext.Request request) {
        return CopyUtil.run(ConfigEntity.findById(request.getId()).update(
                CopyUtil.run(request, ConfigModel.class)
        ).getModel(), ConfigContext.Dto.class);
    }

}
