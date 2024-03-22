package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.console.domain.Aggregate;
import club.p6e.coat.console.domain.ListAggregate;
import club.p6e.coat.console.domain.entity.PermissionUrlGroupEntity;
import club.p6e.coat.console.domain.identifier.ListId;
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
public class PermissionUrlGroupTreeAggregate implements Aggregate<ListId>, Serializable {

    /**
     * ID
     */
    protected final ListId id;

    /**
     * 列表
     */
    private List<PermissionUrlGroupEntity> list;

    @Override
    public ListId id() {
        return id;
    }

    public PermissionUrlGroupTreeAggregate(List<PermissionUrlGroupEntity> list) {
        this.list = list;
        this.id = new ListId();
    }
}