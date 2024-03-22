package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.console.domain.Aggregate;
import club.p6e.coat.console.domain.entity.DictionaryEntity;
import club.p6e.coat.console.domain.identifier.DictionaryType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典类型的聚合根
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class DictionaryTypeAggregate implements Aggregate<DictionaryType>, Serializable {

    /**
     * ID
     */
    private final DictionaryType id;

    /**
     * 列表
     */
    private final List<DictionaryEntity> list;

    /**
     * 构造方法初始化
     *
     * @param id   ID
     * @param list 列表
     */
    public DictionaryTypeAggregate(DictionaryType id, List<DictionaryEntity> list) {
        this.id = id;
        this.list = list;
    }

    @Override
    public DictionaryType id() {
        return id;
    }

    public String type() {
        return id.getType();
    }

    public Map<String, String> data() {
        return new HashMap<>() {{
            for (final DictionaryEntity item : list) {
                put(item.getKey(), item.getValue());
            }
        }};
    }

    public Map<String, Map<String, String>> result() {
        return new HashMap<>() {{
            put(type(), data());
        }};
    }

}
