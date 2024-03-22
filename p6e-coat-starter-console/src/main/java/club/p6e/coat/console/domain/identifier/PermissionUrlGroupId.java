package club.p6e.coat.console.domain.identifier;

import club.p6e.coat.console.domain.Identifier;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限 URL GROUP ID
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class PermissionUrlGroupId implements Identifier, Serializable {

    /**
     * ID
     */
    private Integer id;

    /**
     * 构造方法初始化
     *
     * @param id ID
     */
    public PermissionUrlGroupId(Integer id) {
        this.id = id;
    }

}
