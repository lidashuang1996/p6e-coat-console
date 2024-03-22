package club.p6e.coat.console.domain;

import club.p6e.coat.console.domain.identifier.ListId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 列表的聚合根
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public abstract class ListAggregate<T extends Entity<?>> implements Aggregate<ListId>, Serializable {

    /**
     * 页码
     */
    protected int page;

    /**
     * 页长
     */
    protected int size;

    /**
     * 总数
     */
    protected long total;

    /**
     * 列表
     */
    protected List<T> list;

    /**
     * ID
     */
    protected final ListId id;

    /**
     * 构造方法初始化
     *
     * @param page  页码
     * @param size  页长
     * @param total 总数
     * @param list  列表
     */
    public ListAggregate(int page, int size, long total, List<T> list) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.list = list;
        this.id = new ListId();
    }

    @Override
    public ListId id() {
        return id;
    }

}
