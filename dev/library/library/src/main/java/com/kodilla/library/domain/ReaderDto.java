package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDto {
    private int readerId;
    private String readerName;
    private String readerSurname;
    private LocalDate created;
    private List<Borrow> borrows;
}
