package club.p6e.coat.console.domain.entity;

import club.p6e.coat.console.domain.Entity;
import club.p6e.coat.console.domain.identifier.PasswordId;

import java.io.Serializable;

/**
 * 密码实体
 *
 * @author lidashuang
 * @version 1.0
 */
public class PasswordEntity implements Entity<PasswordId>, Serializable {

    /**
     * 密码 ID 对象
     */
    private final PasswordId id;

    /**
     * 构造方法初始化
     *
     * @param password 密码内容
     */
    public PasswordEntity(String password) {
        this.id = new PasswordId(password);
    }

    @Override
    public PasswordId id() {
        return id;
    }

    public String content() {
        return id().getPassword();
    }

    public boolean isMatch(String pwd) {
        return this.id().getPassword().equals(pwd);
    }

}
