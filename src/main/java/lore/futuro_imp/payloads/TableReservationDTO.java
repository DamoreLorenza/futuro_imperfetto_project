package lore.futuro_imp.payloads;

import jakarta.validation.constraints.NotNull;
import lore.futuro_imp.entities.Desk;
import lore.futuro_imp.entities.Event;
import lore.futuro_imp.entities.Game;
import lore.futuro_imp.entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record TableReservationDTO(
        UUID id,
        @NotNull(message = "Data obbligatoria!")
        LocalDate date,
        @NotNull(message = "Orario obbligatorio!")
        LocalTime time,
        @NotNull(message = "User obbligatorio!")
        UUID idUser,
      //  UUID idEvent,
        UUID idGame,
        @NotNull(message = "Tavolo obbligatorio!")
        UUID idDesk
) {
}
