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
public class UserContext implements Serializable {

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Request extends BaseContext.PagingParam implements Serializable {
        private SortableContext sort;
        private SearchableContext search;

        private Integer id;
        private Integer status;
        private Integer enabled;
        private Integer internal;
        private Integer administrator;
        private String account;
        private String phone;
        private String mailbox;
        private String name;
        private String nickname;
        private String avatar;
        private String description;

        private String password;
    }

    @Data
    @Accessors(chain = true)
    public static class Vo implements Serializable {
        private Integer id;
        private Integer status;
        private Integer enabled;
        private Integer internal;
        private Integer administrator;
        private String account;
        private String phone;
        private String mailbox;
        private String name;
        private String nickname;
        private String avatar;
        private String description;
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
        private Integer status;
        private Integer enabled;
        private Integer internal;
        private Integer administrator;
        private String account;
        private String phone;
        private String mailbox;
        private String name;
        private String nickname;
        private String avatar;
        private String description;
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
        private Integer status;
        private Integer enabled;
        private Integer internal;
        private Integer administrator;
        private String account;
        private String phone;
        private String mailbox;
        private String name;
        private String nickname;
        private String avatar;
        private String description;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operator;
    }

}
