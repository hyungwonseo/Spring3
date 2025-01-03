package dw.gameshop.model;

import dw.gameshop.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false, columnDefinition = "TEXT") // 65535 byte
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    private User author;
    @Column(name="created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name="modified_date", nullable = false)
    private LocalDateTime modifiedDate = LocalDateTime.now();
    @Column(name="is_active")
    private Boolean isActive = true;

    public BoardDTO toDto() {
        return new BoardDTO(
                this.id,
                this.title,
                this.content,
                this.author.getUserName(),
                this.modifiedDate
        );
    }
}