package club.p6e.coat.console.application.service.impl;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.application.service.ConfigService;
import club.p6e.coat.console.domain.aggregate.ConfigListAggregate;
import club.p6e.coat.console.domain.entity.ConfigEntity;
import club.p6e.coat.console.domain.identifier.ConfigId;
import club.p6e.coat.console.domain.service.ConfigDomainService;
import club.p6e.coat.console.infrastructure.context.ConfigContext;
import club.p6e.coat.console.infrastructure.model.ConfigModel;
import org.springframework.stereotype.Service;

/**
 * 配置服务实现
 *
 * @author lidashuang
 * @version 1.0
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    /**
     * 配置领域服务
     */
    private final ConfigDomainService service;

    /**
     * 构造方法初始化
     *
     * @param service 配置领域服务
     */
    public ConfigServiceImpl(ConfigDomainService service) {
        this.service = service;
    }

    @Override
    public ConfigContext.Dto get(ConfigContext.Request request) {
        return CopyUtil.run(service.get(new ConfigId(request.getId())).convertToModel(), ConfigContext.Dto.class);
    }

    public ConfigContext.Dto create(ConfigContext.Request request) {
        return CopyUtil.run(service.create(
                new ConfigEntity(null, CopyUtil.run(request, ConfigModel.class))
        ).convertToModel(), ConfigContext.Dto.class);
    }

    @Override
    public ConfigContext.Dto update(ConfigContext.Request request) {
        return CopyUtil.run(service.update(new ConfigEntity(
                new ConfigId(request.getId()), CopyUtil.run(request, ConfigModel.class))
        ).convertToModel(), ConfigContext.Dto.class);
    }

    @Override
    public ConfigContext.Dto delete(ConfigContext.Request request) {
        return CopyUtil.run(service.delete(new ConfigId(request.getId())).convertToModel(), ConfigContext.Dto.class);
    }

    @Override
    public ConfigContext.ListDto list(ConfigContext.Request request) {
        final ConfigListAggregate aggregate = service.list(
                request.getSearch(), request.getSort(), request.getPaginationContext());
        final ConfigContext.ListDto result = new ConfigContext.ListDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(aggregate.getList().stream().map(m ->
                CopyUtil.run(m, ConfigContext.Item.class).setId(m.id().getId())).toList());
        return result;
    }

}
