package dw.gameshop.model;

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
    @Column(length = 100)
    private String title;
    @Column(length = 100)
    private String genre;
    @Column
    private int price;
    @Column(length = 65535)
    private String image;
    @Column(length = 65535)
    private String text;
}
