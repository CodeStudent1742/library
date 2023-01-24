package com.kodilla.library.mapper;

import com.kodilla.library.controller.NotFoundException;
import com.kodilla.library.domain.*;
import com.kodilla.library.repository.BorrowDao;
import com.kodilla.library.repository.CopyDao;
import com.kodilla.library.repository.ReaderDao;
import com.kodilla.library.repository.TitleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryMapper {
    private final BorrowDao borrowDao;
    private final ReaderDao readerDao;
    private final CopyDao copyDao;
    private final TitleDao titleDao;


    public Borrow mapToBorrow(final BorrowDto borrowDto) throws NotFoundException {
        return new Borrow(
                borrowDto.getBorrowId(),
                borrowDto.getBorrowDate(),
                borrowDto.getReturnDate(),
                readerDao.findById(borrowDto.getReaderId()).orElseThrow(NotFoundException::new),
                copyDao.findById(borrowDto.getCopyId()).orElseThrow(NotFoundException::new)
        );
    }

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return new BorrowDto(
                borrow.getBorrowId(),
                borrow.getBorrowDate(),
                borrow.getReturnDate(),
                borrow.getReader().getReaderId(),
                borrow.getCopy().getCopyId()
        );
    }

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getReaderId(),
                readerDto.getReaderName(),
                readerDto.getReaderSurname(),
                readerDto.getCreated(),
                findAllBorrows(readerDto.getBorrowsId())
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getReaderId(),
                reader.getReaderName(),
                reader.getReaderSurname(),
                reader.getCreated(),
                mapToBorrowsIds(reader.getBorrows())
        );
    }

    public Copy mapToCopy(final CopyDto copyDto) throws NotFoundException {
        return new Copy(
                copyDto.getCopyId(),
                copyDto.getStatus(),
                titleDao.findById(copyDto.getTitleId()).orElseThrow(NotFoundException::new)
        );
    }

    public CopyDto mapToCopyDto(final Copy copy) {
        return new CopyDto(
                copy.getCopyId(),
                copy.getStatus(),
                copy.getThisTitle().getTitleId()
        );
    }

    public Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getTitleId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublicationDate(),
                findAllCopies(titleDto.getCopiesId())
        );
    }
    public Title mapToNewTitle(final NewTitleDto newTitleDto) {
        return new Title(
                newTitleDto.getTitle(),
                newTitleDto.getAuthor(),
                newTitleDto.getPublicationDate(),
                findAllCopies(newTitleDto.getCopiesId())
        );
    }

    public TitleDto mapToTitleDto(final Title title) {
        return new TitleDto(
                title.getTitleId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationDate(),
                mapToCopiesIds(title.getCopies())
        );
    }

    public Reader mapToNewReader(NewReaderDto newReaderDto) {
        return new Reader(
                newReaderDto.getReaderName(),
                newReaderDto.getReaderSurname(),
                newReaderDto.getCreated(),
                findAllBorrows(newReaderDto.getBorrowsId())
        );
    }

    public List<Borrow> findAllBorrows(List<Long> borrowsId) {
        List<Borrow> results = new ArrayList<>();
        if (Objects.nonNull(borrowsId)) {
            for (Long id : borrowsId) {
                borrowDao.findById(id).ifPresent(results::add);
            }
        }
        return results;
    }

    public List<Copy> findAllCopies(List<Long> copiesId) {
        List<Copy> results = new ArrayList<>();
        if (Objects.nonNull(copiesId)) {
            for (Long id : copiesId) {
                copyDao.findById(id).ifPresent(results::add);
            }
        }
        return results;
    }
    private List<Long> mapToCopiesIds(List<Copy> copies) {
        return copies.stream()
                .map(Copy::getCopyId)
                .collect(Collectors.toList());
    }
    private List<Long> mapToBorrowsIds(List<Borrow> borrows) {
        return borrows.stream()
                .map(Borrow::getBorrowId)
                .collect(Collectors.toList());
    }
}
