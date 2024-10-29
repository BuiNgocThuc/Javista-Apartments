package com.example.javista.service.Implements;

import com.example.javista.dto.request.otherAnswer.OtherAnswerCreationRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerPatchRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerQueryRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.otherAnswer.OtherAnswerResponse;
import com.example.javista.entity.OtherAnswer;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.OtherAnswerMapper;
import com.example.javista.repository.OtherAnswerRepository;
import com.example.javista.repository.QuestionRepository;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.OtherAnswerService;
import com.example.javista.utils.QueryUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class OtherAnswerServiceImpl implements OtherAnswerService {
        OtherAnswerMapper otherAnswerMapper;
        OtherAnswerRepository otherAnswerRepository;

        QuestionRepository questionRepository;
        UserRepository userRepository;
        
        FilterSpecification<OtherAnswer> filterSpecification;

        @Override
        public PageResponse<OtherAnswerResponse> getOtherAnswers(OtherAnswerQueryRequest query) {
                // Pagination and Sorting
                Pageable pageable = QueryUtils.getPagination(query);

                //Filtering and searching by specification
                Specification<OtherAnswer> spec = filterSpecification.filteringBySpecification(
                                QueryUtils.getFilterCriterion(query)
                );

                var pageData = otherAnswerRepository.findAll(spec, pageable);

                return QueryUtils.buildPageResponse(pageData, pageable, otherAnswerMapper::entityToResponse);
        }

        @Override
        public OtherAnswerResponse getOtherAnswerById(Integer id) {
                return otherAnswerMapper.entityToResponse(otherAnswerRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("OtherAnswer Not Found")));
        }

        @Override
        public OtherAnswerResponse createOtherAnswer(OtherAnswerCreationRequest request) {
                OtherAnswer otherAnswer = otherAnswerMapper.creationRequestToEntity(request);

                otherAnswer.setQuestion(questionRepository.findById(request.getQuestionId())
                                .orElseThrow(() -> new RuntimeException("Question Not Found")));

                otherAnswer.setUser(userRepository.findById(request.getUserId())
                                .orElseThrow(() -> new RuntimeException("User Not Found")));

                return otherAnswerMapper.entityToResponse(otherAnswerRepository.save(otherAnswer));
        }

        @Override
        public OtherAnswerResponse updateOtherAnswer(Integer id, OtherAnswerUpdateRequest request) {
                OtherAnswer otherAnswer = otherAnswerRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("OtherAnswer Not Found"));

                otherAnswerMapper.updateRequestToEntity(otherAnswer, request);
                return otherAnswerMapper.entityToResponse(otherAnswerRepository.save(otherAnswer));
        }

        @Override
        public OtherAnswerResponse patchOtherAnswer(Integer id, OtherAnswerPatchRequest request) {
                OtherAnswer otherAnswer = otherAnswerRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("OtherAnswer Not Found"));

                otherAnswerMapper.patchRequestToEntity(otherAnswer, request);
                return otherAnswerMapper.entityToResponse(otherAnswerRepository.save(otherAnswer));
        }

        @Override
        public void deleteOtherAnswer(Integer id) {
                OtherAnswer otherAnswer = otherAnswerRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("OtherAnswer Not Found"));

                otherAnswer.setDeletedAt(LocalDateTime.now());
                otherAnswerRepository.save(otherAnswer);
        }
}
