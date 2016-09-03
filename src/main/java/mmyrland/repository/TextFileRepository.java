package mmyrland.repository;

import mmyrland.domain.TextFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TextFileRepository extends CrudRepository<TextFile,UUID>{

}
