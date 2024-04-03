package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.console.domain.ListAggregate;
import club.p6e.coat.console.domain.entity.KeyValueEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 字典列表的聚合根
 *
 * @author lidashuang
 * @version 1.0
 */
public class KeyValueListAggregate extends ListAggregate<KeyValueEntity> implements Serializable {

    /**
     * 构造方法初始化
     *
     * @param page  页码
     * @param size  页长
     * @param total 总数
     * @param list  列表
     */
    public KeyValueListAggregate(int page, int size, long total, List<KeyValueEntity> list) {
        super(page, size, total, list);
    }

}
