package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.console.domain.Aggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlEntity;
import club.p6e.coat.console.domain.entity.PermissionUrlGroupEntity;
import club.p6e.coat.console.domain.identifier.PermissionId;
import club.p6e.coat.console.infrastructure.model.PermissionUrlGroupRelationUrlModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限聚合根
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class PermissionAggregate implements Aggregate<PermissionId>, Serializable {

    /**
     * 权限 ID
     */
    private final PermissionId id;

    /**
     * 配置信息
     */
    private String config;

    /**
     * 属性信息
     */
    private String attribute;

    /**
     * 权限 URL 实体
     */
    private PermissionUrlEntity permissionUrlEntity;

    /**
     * 权限 URL GROUP 实体
     */
    private PermissionUrlGroupEntity permissionUrlGroupEntity;

    /**
     * 构造方法初始化
     *
     * @param id ID
     */
    public PermissionAggregate(PermissionId id) {
        this.id = id;
    }

    /**
     * 构造方法初始化
     *
     * @param id  ID
     * @param model 权限 URL 组关联 URL 模型
     */
    public PermissionAggregate(PermissionId id, PermissionUrlGroupRelationUrlModel model) {
        this.id = id;
        CopyUtil.run(model, this);
    }

    @Override
    public PermissionId id() {
        return id;
    }

}
