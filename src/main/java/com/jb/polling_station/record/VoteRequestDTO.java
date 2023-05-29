package com.jb.polling_station.record;

import java.time.LocalDateTime;

public record VoteRequestDTO(Long userId, Boolean inFavor) {
}
