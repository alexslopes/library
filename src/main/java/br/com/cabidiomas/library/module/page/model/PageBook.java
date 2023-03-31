package br.com.cabidiomas.library.module.page.model;

import br.com.cabidiomas.library.module.level.model.Level;
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
@Table(name = "pagebook")
public class PageBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;


    @Column(name="chapter")
    private Integer chapter;

    @Column(name="page_index")
    private Long pageIndex;

    @Column(name="content")
    private String content;
}
