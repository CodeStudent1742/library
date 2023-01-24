package com.kodilla.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NamedQuery(
        name = "Copy.findCopiesByTitle",
        query = "FROM Copy WHERE thisTitle = :TITLE "
//                "AND status LIKE \"%AVAILABLE%\" "
)


@Entity
@Table(name = "COPIES")
@AllArgsConstructor
public class Copy {

    private Long copyId;
//    @Column(columnDefinition = "enum('AVAILABLE','IN_CIRCULATION','DAMAGED','LOST')")
    private Status status;
    private Title thisTitle;

    public Copy(Status status) {
        this.status = status;
    }

    public Copy(Status status, Title thisTitle) {
        this.status = status;
        this.thisTitle = thisTitle;
    }

    public Copy() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public Long getCopyId() {
        return copyId;
    }

//    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    @ManyToOne
    @JoinColumn(name = "TITLES_ID")
    public Title getThisTitle() {
        return thisTitle;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setThisTitle(Title thisTitle) {
        this.thisTitle = thisTitle;
    }
}
