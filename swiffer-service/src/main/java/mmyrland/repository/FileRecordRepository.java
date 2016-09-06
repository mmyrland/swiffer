package mmyrland.repository;

import mmyrland.domain.FileRecord;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRecordRepository extends PagingAndSortingRepository<FileRecord,UUID> {

    List<FileRecord> findByTextFileId(UUID textFileId);
}
