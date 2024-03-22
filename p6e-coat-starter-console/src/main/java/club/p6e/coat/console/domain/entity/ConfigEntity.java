package club.p6e.coat.console.domain.entity;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.Entity;
import club.p6e.coat.console.domain.identifier.ConfigId;
import club.p6e.coat.console.infrastructure.model.ConfigModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 配置实体
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class ConfigEntity implements Entity<ConfigId>, Serializable {

    /**
     * ID
     */
    private final ConfigId id;

    /**
     * TYPE
     */
    private String type;

    /**
     * KEY
     */
    private String key;

    /**
     * VALUE
     */
    private String value;

    /**
     * OPERATOR
     */
    private String operator;

    /**
     * CREATE DATE
     */
    private LocalDateTime createDate;

    /**
     * UPDATE DATE
     */
    private LocalDateTime updateDate;

    /**
     * 构造方法初始化
     *
     * @param id    ID
     * @param model 模型对象
     */
    public ConfigEntity(ConfigId id, ConfigModel model) {
        this.id = id;
        CopyUtil.run(model, this);
    }

    @Override
    public ConfigId id() {
        return id;
    }

    public ConfigModel convertToModel() {
        return CopyUtil.run(this, ConfigModel.class).setId(id == null ? null : id.getId());
    }

}
