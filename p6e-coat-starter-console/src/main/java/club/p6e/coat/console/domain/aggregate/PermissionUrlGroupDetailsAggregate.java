package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.console.domain.Aggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlGroupEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlGroupId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class PermissionUrlGroupDetailsAggregate implements Aggregate<PermissionUrlGroupId>, Serializable {

    /**
     * 页码
     */
    private int page;

    /**
     * 页长
     */
    private int size;

    /**
     * 总数
     */
    private long total;

    /**
     * ID
     */
    private final PermissionUrlGroupId id;

    /**
     * URL
     */
    private PermissionUrlGroupEntity data;

    /**
     * 列表
     */
    private final List<PermissionAggregate> list;

    public PermissionUrlGroupDetailsAggregate(
            int page,
            int size,
            long total,
            PermissionUrlGroupId id,
            PermissionUrlGroupEntity data,
            List<PermissionAggregate> list) {
        this.id = id;
        this.data = data;
        this.list = list;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    @Override
    public PermissionUrlGroupId id() {
        return id;
    }

}
