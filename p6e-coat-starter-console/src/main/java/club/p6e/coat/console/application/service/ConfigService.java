package club.p6e.coat.console.application.service;

import club.p6e.coat.console.infrastructure.context.ConfigContext;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface ConfigService {

    ConfigContext.ListDto list(ConfigContext.Request request);

    ConfigContext.Dto create(ConfigContext.Request request);

    ConfigContext.Dto get(ConfigContext.Request request);

    ConfigContext.Dto delete(ConfigContext.Request request);

    ConfigContext.Dto update(ConfigContext.Request request);

}
