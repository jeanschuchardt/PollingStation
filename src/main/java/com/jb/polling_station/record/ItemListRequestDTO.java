package com.jb.polling_station.record;


public record ItemListRequestDTO(String title, String description, Long userId, Long parentTaskId) {
}
