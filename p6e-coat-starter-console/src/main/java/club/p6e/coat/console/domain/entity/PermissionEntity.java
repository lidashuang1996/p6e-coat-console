package club.p6e.coat.console.domain.entity;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.Entity;
import club.p6e.coat.console.domain.identifier.PermissionId;
import club.p6e.coat.console.infrastructure.model.PermissionUrlGroupRelationUrlModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class PermissionEntity implements Entity<PermissionId>, Serializable {

    /**
     * ID
     */
    private final PermissionId id;

    /**
     * CONFIG
     */
    private String config;

    /**
     * ATTRIBUTE
     */
    private String attribute;

    public PermissionEntity(PermissionId id) {
        this.id = id;
    }

    public PermissionEntity(PermissionId id, PermissionUrlGroupRelationUrlModel model) {
        this.id = id;
        CopyUtil.run(model, this);
    }

    @Override
    public PermissionId id() {
        return id;
    }

    public PermissionUrlGroupRelationUrlModel convertToModel() {
        return CopyUtil.run(this, PermissionUrlGroupRelationUrlModel.class)
                .setUid(id == null ? null : id.getUid()).setGid(id == null ? null : id.getGid());
    }

}
