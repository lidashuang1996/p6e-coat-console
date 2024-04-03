package club.p6e.coat.console.domain.identifier;

import club.p6e.coat.console.domain.Identifier;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class KvKey implements Identifier, Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 构造方法初始化
     *
     * @param id ID
     */
    public KvKey(String id) {
        this.id = id;
    }

}