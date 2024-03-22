package club.p6e.coat.console.domain.entity;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.Entity;
import club.p6e.coat.console.domain.identifier.FileUploadLogId;
import club.p6e.coat.console.infrastructure.model.FileUploadModel;
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
public class FileUploadLogEntity implements Entity<FileUploadLogId>, Serializable {

    /**
     * ID
     */
    private final FileUploadLogId id;

    /**
     * NAME
     */
    private String name;

    /**
     * SIZE
     */
    private Long size;

    /**
     * SOURCE
     */
    private String source;

    /**
     * STORAGE TYPE
     */
    private String storageType;

    /**
     * STORAGE LOCATION
     */
    private String storageLocation;

    /**
     * OWNER
     */
    private String owner;

    /**
     * LOCK
     */
    private Integer lock;

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
    public FileUploadLogEntity(FileUploadLogId id, FileUploadModel model) {
        this.id = id;
        CopyUtil.run(model, this);
    }

    @Override
    public FileUploadLogId id() {
        return id;
    }

    public FileUploadModel convertToModel() {
        return CopyUtil.run(this, FileUploadModel.class).setId(id == null ? null : id.getId());
    }
}
