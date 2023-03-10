package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDto {
    private Long borrowId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Long readerId;
    private Long copyId;
}
