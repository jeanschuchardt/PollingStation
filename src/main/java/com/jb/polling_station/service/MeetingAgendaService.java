package com.jb.polling_station.service;

import com.jb.polling_station.entity.MeetingAgenda;
import com.jb.polling_station.exception.ApiRequestException;
import com.jb.polling_station.record.MeetingAgendaRequestDTO;
import com.jb.polling_station.repository.MeetingAgendaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MeetingAgendaService {
    
    public final MeetingAgendaRepository meetingAgendaRepository;
    
    public MeetingAgendaService(MeetingAgendaRepository meetingAgendaRepository) {
        this.meetingAgendaRepository = meetingAgendaRepository;
    }
    
    public void delete(int id) {
        MeetingAgenda agenda = getListById(id);
        agenda.setIsArchived(true);
        try {
            save(agenda);
        } catch (Exception e) {
            throw new ApiRequestException("ERROR ON DELETE", HttpStatus.BAD_REQUEST);
        }
    }
    
    public List<MeetingAgenda> getAll() {
        List<MeetingAgenda> meetingAgenda = meetingAgendaRepository.findAllByIsArchived(false);
        if (meetingAgenda.isEmpty()) {
            throw new ApiRequestException("NO CONTENT", HttpStatus.NO_CONTENT);
        }
        if (Objects.isNull(meetingAgenda)) {
            throw new ApiRequestException("NO CONTENT", HttpStatus.NO_CONTENT);
        }
        
        return meetingAgenda;
    }
    
    public MeetingAgenda getListById(int id) {
        return meetingAgendaRepository.findByIdAndIsArchived(id, false)
                                      .orElseThrow(
                                              () -> new ApiRequestException("NOT FOUND", HttpStatus.NOT_FOUND));
    }
    
    
    public MeetingAgenda createAgenda(MeetingAgendaRequestDTO meetingAgendaRequestDTO) {
        MeetingAgenda meetingAgenda = new MeetingAgenda()
                .setName(meetingAgendaRequestDTO.name())
                .setDescription(meetingAgendaRequestDTO.description())
                .setUserId(meetingAgendaRequestDTO.userId());
        
        return save(meetingAgenda);
        
    }
    
    public MeetingAgenda save(MeetingAgenda meetingAgenda) {
        try {
            return meetingAgendaRepository.save(meetingAgenda);
        } catch (Exception e) {
            throw new ApiRequestException("not able to save", HttpStatus.BAD_REQUEST);
        }
        
    }
}
