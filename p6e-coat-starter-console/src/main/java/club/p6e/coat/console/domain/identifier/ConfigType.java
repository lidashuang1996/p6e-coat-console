package club.p6e.coat.console.domain.identifier;

import club.p6e.coat.console.domain.Identifier;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 配置 TYPE
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class ConfigType implements Identifier, Serializable {

    /**
     * 类型
     */
    private String type;

    /**
     * 构造方法初始化
     *
     * @param type TYPE
     */
    public ConfigType(String type) {
        this.type = type;
    }

}