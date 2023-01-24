package com.kodilla.library.test;

import com.kodilla.library.controller.NotFoundException;
import com.kodilla.library.domain.*;
import com.kodilla.library.repository.CopyDao;
import com.kodilla.library.repository.ReaderDao;
import com.kodilla.library.repository.TitleDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class TestsSuite {
    @Autowired
    private ReaderDao readerDao;
    @Autowired
    private TitleDao titleDao;
    @Autowired
    private CopyDao copyDao;

    @Test
    void testTableCreation() {
        //GIVEN
        Reader reader1 = new Reader("Name1", "Surname1", LocalDate.of(120, 11, 12));
        Title title1 = new Title("Title1", "Author1", LocalDate.of(121, 11, 12));
        Copy copy1 = new Copy(Status.IN_CIRCULATION);
        Borrow borrow1 = new Borrow(LocalDate.of(122, 11, 18), null);

        titleDao.save(title1);
        title1.getCopies().add(copy1);
        copy1.setThisTitle(title1);
        borrow1.setCopy(copy1);
        reader1.getBorrows().add(borrow1);
        borrow1.setReader(reader1);
        readerDao.save(reader1);

        //WHEN
        Long id = reader1.getReaderId();

        //THEN
        assertNotEquals(0, id);
        //CleanUp
        readerDao.delete(reader1);
        titleDao.delete(title1);
    }

    @Test
    void testCopyUpdate() throws NotFoundException {
        //GIVEN
        Title title1 = new Title("Title1", "Author1", LocalDate.of(121, 11, 12));
        titleDao.save(title1);
        Copy copy1 = new Copy(Status.IN_CIRCULATION, title1);
        Copy copy2 = new Copy(Status.AVAILABLE, title1);
        Copy copy3 = new Copy(Status.AVAILABLE, title1);
        copyDao.save(copy1);
        copyDao.save(copy2);
        copyDao.save(copy3);

        //WHEN
        Long titleId = title1.getTitleId();
        Title title = titleDao.findById(titleId).orElseThrow(NotFoundException::new);
        List<Copy> copies = copyDao.findCopiesByTitle(title);
        List<Copy> copiesFiltered = copies.stream()
                .filter(s -> s.getStatus() == Status.AVAILABLE)
                .collect(Collectors.toList());
        int copiesAvailableByTitleId = copiesFiltered.size();

        //THEN
        assertEquals(2,copiesAvailableByTitleId);
    }
}
