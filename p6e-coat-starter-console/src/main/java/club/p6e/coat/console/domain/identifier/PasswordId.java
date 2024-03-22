package club.p6e.coat.console.domain.identifier;

import club.p6e.coat.console.domain.Identifier;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 密码 ID
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class PasswordId implements Identifier, Serializable {

    /**
     * PASSWORD
     */
    private String password;

    /**
     * 密码
     *
     * @param password 密码
     */
    public PasswordId(String password) {
        this.password = password;
    }

}