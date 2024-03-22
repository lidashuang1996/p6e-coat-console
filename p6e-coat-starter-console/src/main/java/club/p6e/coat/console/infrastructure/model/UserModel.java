package club.p6e.coat.console.infrastructure.model;

import club.p6e.coat.console.infrastructure.model.listener.BaseModelListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "p6e_user")
@EntityListeners(value = BaseModelListener.class)
public class UserModel implements Serializable {

    public static final String ID = "id";
    public static final String STATUS = "status";
    public static final String ENABLED = "enabled";
    public static final String ACCOUNT = "account";
    public static final String PHONE = "phone";
    public static final String MAILBOX = "mailbox";
    public static final String NAME = "name";
    public static final String NICKNAME = "nickname";
    public static final String AVATAR = "avatar";
    public static final String DESCRIPTION = "description";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateDate";
    public static final String OPERATOR = "operator";
    public static final String VERSION = "version";
    public static final String IS_DELETE = "isDelete";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status")
    private Integer status;
    @Column(name = "enabled")
    private Integer enabled;
    @Column(name = "internal")
    private Integer internal;
    @Column(name = "administrator")
    private Integer administrator;
    @Column(name = "account")
    private String account;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mailbox")
    private String mailbox;
    @Column(name = "name")
    private String name;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "description")
    private String description;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Column(name = "version")
    private Integer version;
    @Column(name = "operator")
    private String operator;
}
