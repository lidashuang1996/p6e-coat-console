package club.p6e.coat.console.domain.entity;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.Entity;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;
import club.p6e.coat.console.infrastructure.model.PermissionUrlGroupModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class PermissionUrlGroupEntity implements Entity<PermissionUrlGroupId>, Serializable {
    private final PermissionUrlGroupId id;
    private Integer parent;
    private String mark;
    private Integer weight;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String operator;

    public PermissionUrlGroupEntity(PermissionUrlGroupId id, PermissionUrlGroupModel model) {
        this.id = id;
        CopyUtil.run(model, this);
    }

    @Override
    public PermissionUrlGroupId id() {
        return id;
    }

    public PermissionUrlGroupModel convertToModel() {
        if (parent == null) {
            parent = 0;
        }
        return CopyUtil.run(this, PermissionUrlGroupModel.class).setId(id == null ? null : id.getId());
    }

}
