package com.kodilla.library.repository;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.Title;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CopyDao extends CrudRepository<Copy,Integer> {


    Optional<Copy> findById(int id);

    @Query
    List<Copy> findCopiesByTitle(@Param("TITLE") Title title);
}
