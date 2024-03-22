package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.console.domain.Aggregate;
import club.p6e.coat.console.domain.entity.ConfigEntity;
import club.p6e.coat.console.domain.identifier.ConfigType;
import club.p6e.coat.console.infrastructure.model.ConfigModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置类型的聚合根
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class ConfigTypeAggregate implements Aggregate<ConfigType>, Serializable {

    /**
     * ID
     */
    private final ConfigType id;

    /**
     * 列表
     */
    private final List<ConfigEntity> list;

    /**
     * 构造方法初始化
     *
     * @param id   ID
     * @param list 列表
     */
    public ConfigTypeAggregate(ConfigType id, List<ConfigEntity> list) {
        this.id = id;
        this.list = list;
    }

    @Override
    public ConfigType id() {
        return id;
    }

    public String type() {
        return id.getType();
    }

    public Map<String, String> data() {
        return new HashMap<>() {{
            for (final ConfigEntity item : list) {
                put(item.getKey(), item.getValue());
            }
        }};
    }

    public Map<String, Map<String, String>> result() {
        return new HashMap<>() {{
            put(type(), data());
        }};
    }

}
