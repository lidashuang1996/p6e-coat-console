package club.p6e.coat.console.domain.identifier;

import club.p6e.coat.console.domain.Identifier;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限 ID
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class PermissionId implements Identifier, Serializable {

    /**
     * UID
     */
    private Integer uid;

    /**
     * GID
     */
    private Integer gid;

    /**
     * 构造方法初始化
     *
     * @param uid uid
     * @param gid gid
     */
    public PermissionId(Integer uid, Integer gid) {
        this.uid = uid;
        this.gid = gid;
    }

}
