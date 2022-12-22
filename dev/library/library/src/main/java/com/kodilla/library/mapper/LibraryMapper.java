package com.kodilla.library.mapper;

import com.kodilla.library.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LibraryMapper {

    public Borrow mapToBorrow(final BorrowDto borrowDto){
        return new Borrow(
                borrowDto.getBorrowId(),
                borrowDto.getBorrowDate(),
                borrowDto.getReturnDate(),
                borrowDto.getReader(),
                borrowDto.getCopy()
        );
    }
    public BorrowDto mapToBorrowDto(final Borrow borrow){
        return new BorrowDto(
                borrow.getBorrowId(),
                borrow.getBorrowDate(),
                borrow.getReturnDate(),
                borrow.getReader(),
                borrow.getCopy()
        );
    }
    public Reader mapToReader (final ReaderDto readerDto){
        return new Reader(
                readerDto.getReaderId(),
                readerDto.getReaderName(),
                readerDto.getReaderSurname(),
                readerDto.getCreated(),
                readerDto.getBorrows() //list<Borrow>
        );
    }
    public ReaderDto mapToReaderDto (final Reader reader){
        return new ReaderDto(
                reader.getReaderId(),
                reader.getReaderName(),
                reader.getReaderSurname(),
                reader.getCreated(),
                reader.getBorrows() //list<Borrow>
        );
    }
    public Copy mapToCopy(final CopyDto copyDto){
        return new Copy(
                copyDto.getCopyId(),
                copyDto.getStatus(),
                copyDto.getThisTitle()
        );
    }
    public CopyDto mapToCopyDto(final Copy copy){
        return new CopyDto(
                copy.getCopyId(),
                copy.getStatus(),
                copy.getThisTitle()
        );
    }
    public Title mapToTitle(final TitleDto titleDto){
        return new Title(
           titleDto.getTitleId(),
           titleDto.getTitle(),
           titleDto.getAuthor(),
           titleDto.getPublicationDate(),
                titleDto.getCopies() //List<Copy>
        );
    }
    public TitleDto mapToTitleDto(final Title title){
        return new TitleDto(
                title.getTitleId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationDate(),
                title.getCopies() //List<Copy>
        );
    }

}
