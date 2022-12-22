package com.kodilla.library.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "READERS")
@AllArgsConstructor
public class Reader {

    private int readerId;
    private String readerName;
    private String readerSurname;
    private LocalDate created;
    private List<Borrow> borrows = new ArrayList<>();
    public Reader(String readerName,String readerSurname, LocalDate created) {
        this.readerName = readerName;
        this.readerSurname = readerSurname;
        this.created = created;
    }
    public Reader(){

    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public int getReaderId() {
        return readerId;
    }
    @Column(name = "READER_NAME")
    public String getReaderName() {
        return readerName;
    }
    @Column(name = "READER_SURNAME")
    public String getReaderSurname() {
        return readerSurname;
    }

    @Column(name = "CREATED")
    public LocalDate getCreated() {
        return created;
    }
    @OneToMany(targetEntity = Borrow.class,
            cascade = CascadeType.ALL,
            mappedBy = "reader",
            fetch = FetchType.EAGER
    )
    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    public void setReaderSurname(String readerSurname) {
        this.readerSurname = readerSurname;
    }
}
