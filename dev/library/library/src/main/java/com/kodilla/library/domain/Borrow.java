package com.kodilla.library.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;


@Entity
@Table(name = "BORROWS")
@AllArgsConstructor
public class Borrow {

    private Long borrowId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Reader reader;
    private Copy copy;

    public Borrow(LocalDate borrowTime, LocalDate returnDate) {
        this.borrowDate = borrowTime;
        this.returnDate = returnDate;
    }
    public Borrow (LocalDate borrowTime, LocalDate returnDate, Reader reader, Copy copy){
        this.borrowDate = borrowTime;
        this.returnDate = returnDate;
        this.reader = reader;
        this.copy = copy;
    }

    public Borrow() {
    }


    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public Long getBorrowId() {
        return borrowId;
    }
    @Column(name = "BORROWED")
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    @Column(name = "RETURNED")
    public LocalDate getReturnDate() {
        return returnDate;
    }
    @ManyToOne()
    @JoinColumn(name = "READERS_ID")
    public Reader getReader() {
        return reader;
    }
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "COPIES_ID")
    public Copy getCopy() {
        return copy;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }
}

