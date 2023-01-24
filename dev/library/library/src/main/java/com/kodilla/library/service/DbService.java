package com.kodilla.library.service;

import com.kodilla.library.controller.NotFoundException;
import com.kodilla.library.domain.*;
import com.kodilla.library.repository.BorrowDao;
import com.kodilla.library.repository.CopyDao;
import com.kodilla.library.repository.ReaderDao;
import com.kodilla.library.repository.TitleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DbService {

    private final BorrowDao borrowDao;
    private final CopyDao copyDao;
    private final ReaderDao readerDao;
    private final TitleDao titleDao;


    public void saveCopy(final Copy copy) {
        copyDao.save(copy);
    }

    public void saveReader(final Reader reader) {
        readerDao.save(reader);
    }

    public void saveTittle(final Title title) {
        titleDao.save(title);
    }

    public Copy findCopyById(Long copyId) throws NotFoundException {
        return copyDao.findById(copyId).orElseThrow(NotFoundException::new);
    }

    public int findCopiesAvailableByTitleId(Long titleId) throws NotFoundException {
        Title title = titleDao.findById(titleId).orElseThrow(NotFoundException::new);
        List<Copy> copies = copyDao.findCopiesByTitle(title);
        List<Copy> copiesFiltered = copies.stream()
                .filter(s -> s.getStatus() == Status.AVAILABLE)
                .collect(Collectors.toList());
        int copiesAvailableByTitleId = copiesFiltered.size();
        return copiesAvailableByTitleId;
    }

    public Borrow findBorrowById(Long borrowId) throws  NotFoundException {
        return borrowDao.findById(borrowId).orElseThrow(NotFoundException::new);
    }

    public void saveBorrow(Borrow borrow) {
        borrowDao.save(borrow);
    }

    public Copy findCopyToBorrow(Long titleId) throws NotFoundException {
        Title title = titleDao.findById(titleId).orElseThrow(NotFoundException::new);
        List<Copy> copies = copyDao.findCopiesByTitle(title);
        List<Copy> copiesFiltered = copies.stream()
                .filter(s -> s.getStatus() == Status.AVAILABLE)
                .collect(Collectors.toList());
        if (copiesFiltered.size()>0){
            return copiesFiltered.get(0);
        }else{
            throw new NotFoundException() ;
        }

    }

    public Reader findReaderById(Long readerId) throws NotFoundException {
         return readerDao.findById(readerId).orElseThrow(NotFoundException::new);
    }

    public Title findTitleByTitleId(Long titleId) throws NotFoundException {
        return titleDao.findById(titleId).orElseThrow(NotFoundException::new);
    }
}
