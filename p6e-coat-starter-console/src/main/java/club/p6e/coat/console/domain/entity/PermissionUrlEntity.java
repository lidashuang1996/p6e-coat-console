package club.p6e.coat.console.domain.entity;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.Entity;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;
import club.p6e.coat.console.infrastructure.model.PermissionUrlModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class PermissionUrlEntity implements Entity<PermissionUrlId>, Serializable {

    /**
     * ID
     */
    private final PermissionUrlId id;

    /**
     * URL
     */
    private String url;

    /**
     * BASE URL
     */
    private String baseUrl;

    /**
     * BASE URL
     */
    private String method;

    /**
     * CONFIG
     */
    private String config;

    /**
     * NAME
     */
    private String name;

    /**
     * DESCRIPTION
     */
    private String description;

    /**
     * CREATE DATE
     */
    private LocalDateTime createDate;

    /**
     * UPDATE DATE
     */
    private LocalDateTime updateDate;

    /**
     * OPERATOR
     */
    private String operator;

    public PermissionUrlEntity(PermissionUrlId id, PermissionUrlModel model) {
        this.id = id;
        CopyUtil.run(model, this);
    }

    @Override
    public PermissionUrlId id() {
        return id;
    }

    public PermissionUrlModel convertToModel() {
        return CopyUtil.run(this, PermissionUrlModel.class).setId(id == null ? null : id.getId());
    }

}
