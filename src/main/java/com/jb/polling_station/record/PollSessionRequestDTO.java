package com.jb.polling_station.record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record PollSessionRequestDTO(LocalDateTime starDate, LocalDateTime endDate, Long agendaId) {
}
