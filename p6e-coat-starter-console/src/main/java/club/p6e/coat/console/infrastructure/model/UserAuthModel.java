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
@Table(name = "p6e_user_auth")
public class UserAuthModel implements Serializable {

    public static final String ID = "id";
    public static final String STATUS = "account";
    public static final String PHONE = "phone";
    public static final String MAILBOX = "mailbox";
    public static final String PASSWORD = "password";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateDate";
    public static final String OPERATOR = "operator";
    public static final String VERSION = "version";
    public static final String IS_DELETE = "isDelete";

    @Id
    private Integer id;
    @Column(name = "account")
    private String account;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mailbox")
    private String mailbox;
    @Column(name = "password")
    private String password;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Column(name = "version")
    private Integer version;
    @Column(name = "operator")
    private String operator;

}
