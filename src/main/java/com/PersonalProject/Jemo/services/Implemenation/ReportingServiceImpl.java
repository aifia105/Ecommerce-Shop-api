package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.ReportDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.model.User;
import com.PersonalProject.Jemo.repository.ReportingRepository;
import com.PersonalProject.Jemo.repository.UserRepository;
import com.PersonalProject.Jemo.services.ReportingService;
import com.PersonalProject.Jemo.validator.ReportingValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportingServiceImpl implements ReportingService {

    private final ReportingRepository reportingRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportingServiceImpl(ReportingRepository reportingRepository, UserRepository userRepository) {
        super();
        this.reportingRepository = reportingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReportDto save(ReportDto reportDto) {
        List<String> errors = ReportingValidator.validate(reportDto);
        if (!errors.isEmpty()) {
            log.error("Report invalid ");
            throw new EntityNotValidException("Report not valid");
        }
        Optional<User> user = userRepository.findById(reportDto.getRapporteur().getId());
        if (user.isEmpty()){
            log.warn("user not found in the database");
            throw new EntityNotFoundException("No user in the database with this Id" + reportDto.getRapporteur().getId() , ErrorCodes.USER_NOT_FOUND);
        }
        return ReportDto.fromEntity(reportingRepository.save(ReportDto.toEntity(reportDto)));
    }

    @Override
    public List<ReportDto> findAllByUserId(Long id) {
        if (id == null){
            log.error("Id user is null");
            return null;
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            log.warn("user not found in the database");
            throw new EntityNotFoundException("No user in the database with this Id" + id, ErrorCodes.USER_NOT_FOUND);
        }
        return reportingRepository.findAllByRapporteurId(id)
                .stream().map(ReportDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ReportDto findById(Long id) {
        if (id == null){
            log.error("Id Report is null");
            return null;
        }
        return reportingRepository.findById(id).map(ReportDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Report with this ID is not found" + id));
    }

    @Override
    public List<ReportDto> findAll() {
        return reportingRepository.findAll().stream()
                .map(ReportDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null){
            log.error("Id Report is null");
            return;
        }
        reportingRepository.deleteById(id);

    }
}
