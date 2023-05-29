package com.jb.polling_station.record;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseDTO<T>
{
    private T item;
    
    public ResponseDTO(T item) {
        this.item = item;
    }
}
