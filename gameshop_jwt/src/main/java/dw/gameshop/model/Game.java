package dw.gameshop.model;

import dw.gameshop.enums.GameGenre;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private GameGenre genre;
    @Column(name = "price")
    private int price;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "text", length = 3000)
    private String text;
}
