package club.p6e.coat.console.domain.entity;

import club.p6e.coat.common.utils.CopyUtil;
import club.p6e.coat.common.utils.GeneratorUtil;
import club.p6e.coat.console.domain.Entity;
import club.p6e.coat.console.domain.identifier.UserId;
import club.p6e.coat.console.infrastructure.model.UserModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class UserEntity implements Entity<UserId>, Serializable {

    /**
     * ID
     */
    private final UserId id;

    /**
     * STATUS
     */
    private Integer status;

    /**
     * ENABLED
     */
    private Integer enabled;

    /**
     * INTERNAL
     */
    private Integer internal;

    /**
     * ADMINISTRATOR
     */
    private Integer administrator;

    /**
     * ACCOUNT
     */
    private String account;

    /**
     * PHONE
     */
    private String phone;

    /**
     * MAILBOX
     */
    private String mailbox;

    /**
     * NAME
     */
    private String name;

    /**
     * NICKNAME
     */
    private String nickname;

    /**
     * AVATAR
     */
    private String avatar;

    /**
     * DESCRIPTION
     */
    private String description;

    /**
     * OPERATOR
     */
    private String operator;

    /**
     * CREATE DATE
     */
    private LocalDateTime createDate;

    /**
     * UPDATE DATE
     */
    private LocalDateTime updateDate;

    public UserEntity(UserId id, UserModel model) {
        this.id = id;
        CopyUtil.run(model, this);
    }

    @Override
    public UserId id() {
        return id;
    }

    public UserModel convertToModel() {
        if (status == null) {
            status = 0;
        }
        if (enabled == null) {
            enabled = 1;
        }
        if (internal == null) {
            internal = 0;
        }
        if (administrator == null) {
            administrator = 0;
        }
        if (name == null) {
            name = GeneratorUtil.uuid();
        }
        if (nickname == null) {
            nickname = name;
        }
        if (avatar == null) {
            avatar = "default.jpg";
        }
        return CopyUtil.run(this, UserModel.class).setId(id == null ? null : id.getId());
    }

}
