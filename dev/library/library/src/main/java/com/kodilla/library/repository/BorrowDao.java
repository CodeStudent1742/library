package com.kodilla.library.repository;


import com.kodilla.library.domain.Borrow;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface BorrowDao extends CrudRepository<Borrow,Integer> {
}
