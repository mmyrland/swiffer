package mmyrland.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRecordRepository extends CrudRepository<FileRecord,UUID> {

    List<FileRecord> findByTextFileId(UUID textFileId);
}