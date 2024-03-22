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
public class PermissionContext implements Serializable {

    public static class Url implements Serializable {
        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class Request extends BaseContext.PagingParam implements Serializable {
            private SortableContext sort;
            private SearchableContext search;

            private Integer id;
            private String url;
            private String baseUrl;
            private String method;
            private String config;
            private String name;
            private String description;
        }

        @Data
        @Accessors(chain = true)
        public static class Vo implements Serializable {
            private Integer id;
            private String url;
            private String baseUrl;
            private String method;
            private String config;
            private String name;
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
            private String url;
            private String baseUrl;
            private String method;
            private String config;
            private String name;
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
            private String url;
            private String baseUrl;
            private String method;
            private String config;
            private String name;
            private String description;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operator;
        }

        public static class Details implements Serializable {
            @Data
            @Accessors(chain = true)
            @EqualsAndHashCode(callSuper = true)
            public static class Request extends BaseContext.PagingParam implements Serializable {
                private Integer id;
            }

            @Data
            @Accessors(chain = true)
            @EqualsAndHashCode(callSuper = true)
            public static class Vo extends BaseContext.ListResult implements Serializable {
                private Integer id;
                private String url;
                private String baseUrl;
                private String method;
                private String config;
                private String name;
                private String description;
                private String operator;
                private LocalDateTime createDate;
                private LocalDateTime updateDate;
                private List<GroupItem> groups;
            }

            @Data
            @Accessors(chain = true)
            @EqualsAndHashCode(callSuper = true)
            public static class Dto extends BaseContext.ListResult implements Serializable {
                private Integer id;
                private String url;
                private String baseUrl;
                private String method;
                private String config;
                private String name;
                private String description;
                private String operator;
                private LocalDateTime createDate;
                private LocalDateTime updateDate;
                private List<GroupItem> groups;
            }

            @Data
            @Accessors(chain = true)
            public static class GroupItem implements Serializable {
                private Integer id;
                private Integer parent;
                private String mark;
                private Integer weight;
                private String name;
                private String description;
                private LocalDateTime createDate;
                private LocalDateTime updateDate;
                private String operator;

                private String relationConfig;
                private String relationAttribute;
            }
        }
    }

    public static class UrlGroup implements Serializable {
        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class Request extends BaseContext.PagingParam implements Serializable {
            private SortableContext sort;
            private SearchableContext search;

            private Integer id;
            private Integer parent;
            private String mark;
            private Integer weight;
            private String name;
            private String description;
        }

        @Data
        @Accessors(chain = true)
        public static class Vo implements Serializable {
            private Integer id;
            private Integer parent;
            private String mark;
            private Integer weight;
            private String name;
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
            private Integer parent;
            private String mark;
            private Integer weight;
            private String name;
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
            private Integer parent;
            private String mark;
            private Integer weight;
            private String name;
            private String description;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operator;
        }

        public static class Details implements Serializable {
            @Data
            @Accessors(chain = true)
            @EqualsAndHashCode(callSuper = true)
            public static class Request extends BaseContext.PagingParam implements Serializable {
                private Integer id;
            }

            @Data
            @Accessors(chain = true)
            @EqualsAndHashCode(callSuper = true)
            public static class Vo extends BaseContext.ListResult implements Serializable {
                private Integer id;
                private Integer parent;
                private String mark;
                private Integer weight;
                private String name;
                private String description;
                private LocalDateTime createDate;
                private LocalDateTime updateDate;
                private String operator;
                private List<UrlItem> urls;
            }


            @Data
            @Accessors(chain = true)
            @EqualsAndHashCode(callSuper = true)
            public static class Dto extends BaseContext.ListResult implements Serializable {
                private Integer id;
                private Integer parent;
                private String mark;
                private Integer weight;
                private String name;
                private String description;
                private LocalDateTime createDate;
                private LocalDateTime updateDate;
                private String operator;
                private List<UrlItem> urls;
            }

            @Data
            @Accessors(chain = true)
            public static class UrlItem implements Serializable {
                private Integer id;
                private String url;
                private String baseUrl;
                private String method;
                private String config;
                private String name;
                private String description;
                private LocalDateTime createDate;
                private LocalDateTime updateDate;
                private String operator;
                private String relationConfig;
                private String relationAttribute;
            }
        }


    }

    public static class Tree implements Serializable {
        @Data
        @Accessors(chain = true)
        public static class Request implements Serializable {
            private Integer id;
        }

        @Data
        @Accessors(chain = true)
        public static class Dto implements Serializable {
            private List<Item> list;
        }

        @Data
        @Accessors(chain = true)
        public static class Item implements Serializable {
            private Integer id;
            private Integer parent;
            private String mark;
            private Integer weight;
            private String name;
            private String description;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operator;
        }
    }

    public static class Association implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class Request implements Serializable {
            private Integer uid;
            private Integer gid;
            private String config;
            private String attribute;
        }

        @Data
        @Accessors(chain = true)
        public static class Vo implements Serializable {
            private Integer uid;
            private Integer gid;
            private String config;
            private String attribute;
        }

        @Data
        @Accessors(chain = true)
        public static class Dto implements Serializable {
            private Integer uid;
            private Integer gid;
            private String config;
            private String attribute;
        }

    }

    public static class Disassociate implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class Request implements Serializable {
            private Integer uid;
            private Integer gid;
        }

        @Data
        @Accessors(chain = true)
        public static class Vo implements Serializable {
            private Integer uid;
            private Integer gid;
        }

        @Data
        @Accessors(chain = true)
        public static class Dto implements Serializable {
            private Integer uid;
            private Integer gid;
        }

    }
}
