package club.p6e.coat.console.infrastructure.model;

import club.p6e.coat.common.search.Searchable;
import club.p6e.coat.common.sortable.Sortable;
import club.p6e.coat.console.infrastructure.model.listener.BaseModelListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "p6e_permission_url")
@EntityListeners(value = BaseModelListener.class)
public class PermissionUrlModel implements Serializable {

    public static final String ID = "id";
    public static final String URL = "url";
    public static final String BASE_URL = "baseUrl";
    public static final String METHOD = "method";
    public static final String CONFIG = "config";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateDate";
    public static final String OPERATOR = "operator";
    public static final String VERSION = "version";

    @Id
    @Sortable
    @Searchable
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Searchable
    @Column(name = "url")
    private String url;
    @NotNull
    @Searchable
    @Column(name = "base_url")
    private String baseUrl;
    @Searchable
    @Column(name = "method")
    private String method;
    @Searchable
    @Column(name = "config")
    private String config;
    @Searchable
    @Column(name = "name")
    private String name;
    @Searchable
    @Column(name = "description")
    private String description;
    @Sortable
    @Searchable
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Sortable
    @Searchable
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Column(name = "operator")
    private String operator;
    @Column(name = "version")
    private Integer version;
}
