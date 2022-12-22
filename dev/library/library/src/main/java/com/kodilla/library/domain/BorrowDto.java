package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDto {
    private int borrowId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Reader reader;
    private Copy copy;
}
