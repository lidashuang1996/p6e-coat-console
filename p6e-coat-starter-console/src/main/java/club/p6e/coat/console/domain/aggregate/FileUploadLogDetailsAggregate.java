package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.console.domain.Aggregate;
import club.p6e.coat.console.domain.identifier.FileUploadLogId;
import club.p6e.coat.console.infrastructure.model.FileUploadChunkModel;
import club.p6e.coat.console.infrastructure.model.FileUploadModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 文件上传日志的聚合根
 *
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class FileUploadLogDetailsAggregate implements Aggregate<FileUploadLogId>, Serializable {

    /**
     * ID
     */
    private final FileUploadLogId id;

    /**
     * DATA
     */
    private final FileUploadModel data;

    /**
     * LIST
     */
    private final List<FileUploadChunkModel> list;

    /**
     * 构造方法初始化
     *
     * @param id   ID 对象
     * @param data 数据对象
     * @param list 列表对象
     */
    public FileUploadLogDetailsAggregate(FileUploadLogId id, FileUploadModel data, List<FileUploadChunkModel> list) {
        this.id = id;
        this.data = data;
        this.list = list;
    }

    @Override
    public FileUploadLogId id() {
        return id;
    }

}
