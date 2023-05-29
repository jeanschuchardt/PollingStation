package com.jb.polling_station.record;

import java.time.LocalDateTime;

public record PollSessionRequestDTO(LocalDateTime starDate, LocalDateTime endDate, Long agendaId) {
}
