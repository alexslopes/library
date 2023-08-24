package br.com.cabidiomas.library.module.level.model;

import br.com.cabidiomas.library.module.language.model.Language;
import br.com.cabidiomas.library.module.book.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Language language;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private List<Book> bookList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private List<StudentLevel> studentLevels;
}
