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
    public ResponseEntity<Void> addReader(@RequestBody NewReaderDto newReaderDto) {
        Reader reader = libraryMapper.mapToNewReader(newReaderDto);
        service.saveReader(reader);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="title",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTitle(@RequestBody NewTitleDto newTitleDto) {
        Title title = libraryMapper.mapToNewTitle(newTitleDto);
        service.saveTittle(title);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="copy")
    public ResponseEntity<Void> addCopy(@RequestParam Long titleId) throws NotFoundException {
        Title title = service.findTitleByTitleId(titleId);
        Copy copy = new Copy(Status.AVAILABLE,title);
        service.saveCopy(copy);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="copy")
    public ResponseEntity<CopyDto> changeBookStatus(@RequestParam Long copyId, @RequestParam Status status) throws NotFoundException {
        Copy copy = service.findCopyById(copyId);
        copy.setStatus(status);
        service.saveCopy(copy);
        return ResponseEntity.ok(libraryMapper.mapToCopyDto(copy));
    }

    @GetMapping(value="copy")
    public int checkCopiesAvailableQuantity(@RequestParam Long titleId) throws NotFoundException {
        return service.findCopiesAvailableByTitleId(titleId);
    }

    @PostMapping(value="borrow")
    public ResponseEntity<Void> bookBorrow(@RequestParam Long titleId, @RequestParam Long readerId) throws NotFoundException {

        Copy copy = service.findCopyToBorrow(titleId);
        Reader reader = service.findReaderById(readerId);
        copy.setStatus(Status.IN_CIRCULATION);
        Borrow borrow = new Borrow(LocalDate.now(), null, reader, copy);
        service.saveBorrow(borrow);
        return ResponseEntity.ok().build();
    }

    @PutMapping("borrow")
    public ResponseEntity<BorrowDto> bookReturn(@RequestParam Long borrowId) throws NotFoundException {
        Borrow borrow = service.findBorrowById(borrowId);
        borrow.setReturnDate(LocalDate.now());
        Copy copy = borrow.getCopy();
        copy.setStatus(Status.AVAILABLE);
        service.saveBorrow(borrow);
        return ResponseEntity.ok(libraryMapper.mapToBorrowDto(borrow));
    }
}
