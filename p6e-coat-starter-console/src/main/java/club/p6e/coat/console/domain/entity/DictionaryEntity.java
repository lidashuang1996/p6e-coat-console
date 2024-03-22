package club.p6e.coat.console.domain.entity;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.Entity;
import club.p6e.coat.console.domain.identifier.DictionaryId;
import club.p6e.coat.console.infrastructure.model.DictionaryModel;
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
public class DictionaryEntity implements Entity<DictionaryId>, Serializable {

    /**
     * ID
     */
    private final DictionaryId id;

    /**
     * KEY
     */
    private String key;

    /**
     * VALUE
     */
    private String value;

    /**
     * TYPE
     */
    private String type;

    /**
     * LANGUAGE
     */
    private String language;

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
    public DictionaryEntity(DictionaryId id, DictionaryModel model) {
        this.id = id;
        CopyUtil.run(model, this);
    }

    @Override
    public DictionaryId id() {
        return id;
    }

    public DictionaryModel convertToModel() {
        return CopyUtil.run(this, DictionaryModel.class).setId(id == null ? null : id.getId());
    }

}
