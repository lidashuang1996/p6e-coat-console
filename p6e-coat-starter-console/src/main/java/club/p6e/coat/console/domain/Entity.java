package club.p6e.coat.console.domain;

import java.io.Serializable;

/**
 * 实体对象
 *
 * @author lidashuang
 * @version 1.0
 */
public interface Entity<ID extends Identifier> extends Serializable {

    /**
     * 获取标识符对象
     *
     * @return 标识符对象
     */
    ID id();

}
