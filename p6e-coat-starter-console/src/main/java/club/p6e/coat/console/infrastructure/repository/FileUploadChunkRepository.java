package club.p6e.coat.console.infrastructure.repository;

import club.p6e.coat.console.infrastructure.model.FileUploadChunkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface FileUploadChunkRepository extends
        JpaRepository<FileUploadChunkModel, Integer>,
        JpaSpecificationExecutor<FileUploadChunkModel> {
}
