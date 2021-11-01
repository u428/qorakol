package com.qorakol.ilm.ziyo.repository;

import com.qorakol.ilm.ziyo.model.entity.MainImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainImagesRepository extends JpaRepository<MainImage, Long> {

    Page<MainImage> findAllByDeleteIsFalse(Pageable pageable);
}
