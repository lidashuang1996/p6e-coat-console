package club.p6e.coat.console.domain.entity;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.Entity;
import club.p6e.coat.console.domain.identifier.KvId;
import club.p6e.coat.console.infrastructure.model.KeyValueModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class KeyValueEntity implements Entity<KvId>, Serializable {

    /**
     * ID
     */
    private final KvId id;

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
    public KeyValueEntity(KvId id, KeyValueModel model) {
        this.id = id;
        CopyUtil.run(model, this);
    }

    @Override
    public KvId id() {
        return id;
    }

    public KeyValueModel convertToModel() {
        return CopyUtil.run(this, KeyValueModel.class).setId(id == null ? null : id.getId());
    }

}
