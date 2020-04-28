package com.isi.repositories;

import com.isi.entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Page<Tag> findAll(Pageable pageable);

}
