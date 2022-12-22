package com.kodilla.library.repository;

import com.kodilla.library.domain.Reader;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface ReaderDao extends CrudRepository<Reader,Integer> {


    Optional<Reader> findById(int readerId);
}
