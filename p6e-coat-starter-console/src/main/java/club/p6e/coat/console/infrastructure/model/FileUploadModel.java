package club.p6e.coat.console.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "p6e_file_upload")
public class FileUploadModel implements Serializable {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SIZE = "size";
    public static final String SOURCE = "source";
    public static final String STORAGE_TYPE = "storageType";
    public static final String STORAGE_LOCATION = "storageLocation";
    public static final String OWNER = "owner";
    public static final String LOCK = "lock";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateDate";
    public static final String OPERATOR = "operator";
    public static final String VERSION = "version";

    @Id
    @Column(name = "[id]")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "[name]")
    private String name;
    @Column(name = "[size]")
    private Long size;
    @Column(name = "[source]")
    private String source;
    @Column(name = "[storage_type]")
    private String storageType;
    @Column(name = "[storage_location]")
    private String storageLocation;
    @Column(name = "[owner]")
    private String owner;
    @Column(name = "[lock]")
    private Integer lock;
    @Column(name = "[create_date]")
    private LocalDateTime createDate;
    @Column(name = "[update_date]")
    private LocalDateTime updateDate;
    @Column(name = "[operator]")
    private String operator;
    @Column(name = "[version]")
    private Integer version;
}
