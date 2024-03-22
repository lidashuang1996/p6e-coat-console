package club.p6e.coat.console.infrastructure.context;

import club.p6e.coat.common.context.BaseContext;
import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class FileUploadLogContext implements Serializable {

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Request extends BaseContext.PagingParam implements Serializable {
        private SortableContext sort;
        private SearchableContext search;
        private Integer id;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ListVo extends BaseContext.ListResult implements Serializable {
        private List<Item> list;
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
        private String name;
        private Long size;
        private String source;
        private String storageType;
        private String storageLocation;
        private String owner;
        private Integer lock;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operator;
    }


    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Vo extends Model implements Serializable {
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Dto extends Model implements Serializable {
    }

    @Data
    @Accessors(chain = true)
    public static class Model implements Serializable {
        private Integer id;
        private String name;
        private Long size;
        private String source;
        private String storageType;
        private String storageLocation;
        private String owner;
        private Integer lock;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operator;
        private List<ChunkModel> list = new ArrayList<>();
    }

    @Data
    @Accessors(chain = true)
    public static class ChunkModel implements Serializable {
        private Integer id;
        private Integer fid;
        private String name;
        private Integer size;
        private LocalDateTime date;
        private String operator;
    }
}
