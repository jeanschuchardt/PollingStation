package com.jb.polling_station.record;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ResponseListDTO<T>
{
    private List<T> items;
    
    public ResponseListDTO(List<T> items) {
        this.items = items;
    }
}
