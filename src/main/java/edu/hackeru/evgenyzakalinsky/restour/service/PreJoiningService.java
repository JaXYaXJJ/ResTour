package edu.hackeru.evgenyzakalinsky.restour.service;

import edu.hackeru.evgenyzakalinsky.restour.dto.PreJoiningRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.PreJoiningResponseDto;

import java.util.List;

public interface PreJoiningService {
    PreJoiningResponseDto createPreJoining(PreJoiningRequestDto preJoiningRequestDto);
    List<PreJoiningResponseDto> getAllPreJoining();
}
