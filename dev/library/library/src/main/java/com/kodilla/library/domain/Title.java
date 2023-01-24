package com.kodilla.library.domain;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TITLES")
@AllArgsConstructor
public class Title {
    private Long titleId;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private List<Copy> copies = new ArrayList<>();

    public Title(String title, String author, LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public Title(String title, String author, LocalDate publicationDate, List<Copy> copies) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.copies = copies;
    }

    public Title(){
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public Long getTitleId() {
        return titleId;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    @Column(name = "PUBLISHED")
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    @OneToMany(
            targetEntity = Copy.class,
            mappedBy = "thisTitle",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Copy> getCopies() {
        return copies;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }
}
