package club.p6e.coat.console.infrastructure.model;

import club.p6e.coat.console.infrastructure.model.listener.BaseModelListener;
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
@Table(name = "p6e_oauth2_client")
@EntityListeners(value = BaseModelListener.class)
public class Oauth2ClientModel implements Serializable {
    public static final String ID = "id";
    public static final String ENABLED = "enabled";
    public static final String TYPE = "type";
    public static final String SCOPE = "scope";
    public static final String REDIRECT_URI = "redirectUri";
    public static final String RECONFIRM = "reconfirm";
    public static final String CLIENT_ID = "clientId";
    public static final String CLIENT_SECRET = "clientSecret";
    public static final String CLIENT_NAME = "clientName";
    public static final String CLIENT_DESCRIPTION = "clientDescription";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateDate";
    public static final String OPERATOR = "operator";
    public static final String VERSION = "version";
    public static final String IS_DELETE = "isDelete";

    @Id
    @Column(name = "[id]")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "[enabled]")
    private Integer enabled;
    @Column(name = "[type]")
    private String type;
    @Column(name = "[scope]")
    private String scope;
    @Column(name = "[redirect_uri]")
    private String redirectUri;
    @Column(name = "[reconfirm]")
    private Integer reconfirm;
    @Column(name = "[client_id]")
    private String clientId;
    @Column(name = "[client_secret]")
    private String clientSecret;
    @Column(name = "[client_name]")
    private String clientName;
    @Column(name = "[client_description]")
    private String clientDescription;
    @Column(name = "[client_avatar]")
    private String clientAvatar;
    @Column(name = "[create_date]")
    private LocalDateTime createDate;
    @Column(name = "[update_date]")
    private LocalDateTime updateDate;
    @Column(name = "[operator]")
    private String operator;
    @Column(name = "[version]")
    private Integer version;
    @Column(name = "[is_delete]")
    private Integer isDelete;

}
