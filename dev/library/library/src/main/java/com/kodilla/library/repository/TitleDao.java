package com.kodilla.library.repository;

import com.kodilla.library.domain.Title;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface TitleDao extends CrudRepository<Title,Long> {

}
