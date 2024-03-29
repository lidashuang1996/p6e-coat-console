package club.p6e.coat.console.infrastructure.model;

import club.p6e.coat.common.search.Searchable;
import club.p6e.coat.console.infrastructure.model.listener.BaseModelListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@IdClass(value = PermissionUrlGroupRelationUrlModel.Key.class)
@Entity
@Table(name = "p6e_permission_url_group_relation_url")
@EntityListeners(value = BaseModelListener.class)
public class PermissionUrlGroupRelationUrlModel implements Serializable {

    public static final String GID = "gid";
    public static final String UID = "uid";

    @Data
    @Accessors(chain = true)
    public static class Key implements Serializable {
        private Integer gid;
        private Integer uid;
    }


    @Id
    @Column(name = "gid")
    private Integer gid;
    @Id
    @Column(name = "uid")
    private Integer uid;
    @Searchable
    @Column(name = "config")
    private String config;
    @Searchable
    @Column(name = "attribute")
    private String attribute;

}
