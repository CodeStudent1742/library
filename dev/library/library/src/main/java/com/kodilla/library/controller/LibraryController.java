package com.kodilla.library.controller;

import com.kodilla.library.domain.*;
import com.kodilla.library.mapper.LibraryMapper;
import com.kodilla.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@CrossOrigin("*")
@RestController
@RequestMapping("v1/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryMapper libraryMapper;

    private final DbService service;


    @PostMapping(value ="reader",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addReader(@RequestBody ReaderDto readerDto) {
        Reader reader = libraryMapper.mapToReader(readerDto);
        service.saveReader(reader);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="title",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTitle(@RequestBody TitleDto titleDto) {
        Title title = libraryMapper.mapToTitle(titleDto);
        service.saveTittle(title);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="copy")
    public ResponseEntity<Void> addCopy(@RequestParam int titleId) throws NotFoundException {
        Title title = service.findTitleByTitleId(titleId);
        Copy copy = new Copy(Status.AVAILABLE,title);
        service.saveCopy(copy);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="copy")
    public ResponseEntity<Void> changeBookStatus(@RequestParam int copyId, @RequestParam Status status) throws NotFoundException {
        Copy copy = service.findCopyById(copyId);
        copy.setStatus(status);
        service.saveCopy(copy);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value="copy")
    public int checkCopiesAvailableQuantity(@RequestParam int titleId) throws NotFoundException {
        int quantity = service.findCopiesAvailableByTitleId(titleId);
        return quantity;
    }

    @PostMapping(value="borrow")
    public ResponseEntity<Void> bookBorrow(@RequestParam int titleId, @RequestParam int readerId) throws NotFoundException {

        Copy copy = service.findCopyToBorrow(titleId);
        Reader reader = service.findReaderById(readerId);
        copy.setStatus(Status.IN_CIRCULATION);
        Borrow borrow = new Borrow(LocalDate.now(), null, reader, copy);
        service.saveBorrow(borrow);
        return ResponseEntity.ok().build();
    }

    @PutMapping("borrow")
    public ResponseEntity<Void> bookReturn(@RequestParam int borrowId) throws NotFoundException {
        Borrow borrow = service.findBorrowById(borrowId);
        borrow.setReturnDate(LocalDate.now());
        Copy copy = borrow.getCopy();
        copy.setStatus(Status.AVAILABLE);
        service.saveBorrow(borrow);
        return ResponseEntity.ok().build();
    }
}
