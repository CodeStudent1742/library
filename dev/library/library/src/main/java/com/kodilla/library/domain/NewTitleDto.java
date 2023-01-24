package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewTitleDto {
    private String title;
    private String author;
    private LocalDate publicationDate;
    private List<Long> copiesId ;
}
