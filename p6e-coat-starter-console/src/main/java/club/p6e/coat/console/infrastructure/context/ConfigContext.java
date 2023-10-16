package club.p6e.coat.console.infrastructure.context;

import club.p6e.coat.common.context.BaseContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class ConfigContext implements Serializable {

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Request extends BaseContext.PagingParam implements Serializable {
        private String query;
        private SortableContext<SortableContext.Option> sort;
        private SearchableContext<SearchableContext.Option> search;

        private Integer id;
        private String key;
        private String value;
    }

    @Data
    @Accessors(chain = true)
    public static class Vo implements Serializable {
        private Integer id;
        private String key;
        private String value;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operator;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ListVo extends BaseContext.ListResult implements Serializable {
        private List<Item> list;
    }


    @Data
    @Accessors(chain = true)
    public static class Dto implements Serializable {
        private Integer id;
        private String key;
        private String value;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operator;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ListDto extends BaseContext.ListResult implements Serializable {
        private List<Item> list;
    }

    @Data
    @Accessors(chain = true)
    public static class Item implements Serializable {
        private Integer id;
        private String key;
        private String value;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operator;
    }

}
