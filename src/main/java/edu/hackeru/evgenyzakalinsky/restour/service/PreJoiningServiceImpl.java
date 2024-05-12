package edu.hackeru.evgenyzakalinsky.restour.service;

import edu.hackeru.evgenyzakalinsky.restour.dto.PreJoiningRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.PreJoiningResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.entity.PreJoining;
import edu.hackeru.evgenyzakalinsky.restour.repository.PreJoiningRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreJoiningServiceImpl implements PreJoiningService {

    private final ModelMapper modelMapper;
    private final PreJoiningRepository preJoiningRepository;

    @Override
    public PreJoiningResponseDto createPreJoining(PreJoiningRequestDto preJoiningRequestDto) {

        var entity = modelMapper.map(preJoiningRequestDto, PreJoining.class);
        var saved = preJoiningRepository.save(entity);
        return modelMapper.map(saved, PreJoiningResponseDto.class);
    }

    @Override
    public List<PreJoiningResponseDto> getAllPreJoining() {

        return preJoiningRepository
                .findAll()
                .stream()
                .map(j -> modelMapper.map(j, PreJoiningResponseDto.class))
                .toList();
    }
}
