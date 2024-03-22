package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.console.domain.Aggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlEntity;
import club.p6e.coat.console.domain.identifier.PermissionUrlId;
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
public class PermissionUrlDetailsAggregate implements Aggregate<PermissionUrlId>, Serializable {

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
    private final PermissionUrlId id;

    /**
     * URL
     */
    private PermissionUrlEntity data;

    /**
     * 列表
     */
    private final List<PermissionAggregate> list;

    /**
     * 构造方法初始化
     *
     * @param id   ID
     * @param list 列表
     */
    public PermissionUrlDetailsAggregate(
            int page,
            int size,
            long total,
            PermissionUrlId id,
            PermissionUrlEntity data,
            List<PermissionAggregate> list
    ) {
        this.id = id;
        this.data = data;
        this.list = list;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    @Override
    public PermissionUrlId id() {
        return id;
    }

}
