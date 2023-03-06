package br.com.cabidiomas.library.book.page.model;

import br.com.cabidiomas.library.book.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Column(name="chapter")
    private Integer chapter;

    @Column(name="page_index")
    private Long pageIndex;

    @Column(name="content")
    private String content;
}
