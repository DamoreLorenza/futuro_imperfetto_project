package lore.futuro_imp.entities;

import lore.futuro_imp.enums.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "game")
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "id_game")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "players")
    private Double players;

    private String avatar;

    @Enumerated(EnumType.STRING)
    private Reservation reservation;

  //  @OneToMany(mappedBy = "game")
  //  private List<Desk> desk;

    @OneToMany(mappedBy = "game")
    private List<TableReservation> tableReservation;

}
