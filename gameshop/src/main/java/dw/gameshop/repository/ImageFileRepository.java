package dw.gameshop.repository;

import dw.gameshop.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
    ImageFile findByFilename(String filename);
}