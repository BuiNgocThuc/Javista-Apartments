package com.example.javista.service.impl;

import com.example.javista.dto.request.answer.AnswerCreationRequest;
import com.example.javista.dto.request.answer.AnswerPatchRequest;
import com.example.javista.dto.request.answer.AnswerQueryRequest;
import com.example.javista.dto.request.answer.AnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.answer.AnswerResponse;
import com.example.javista.entity.Answer;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.AnswerMapper;
import com.example.javista.repository.AnswerRepository;
import com.example.javista.repository.QuestionRepository;
import com.example.javista.service.AnswerService;
import com.example.javista.utils.QueryUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerServiceImpl implements AnswerService {

        AnswerRepository answerRepository;
        AnswerMapper answerMapper;

        QuestionRepository questionRepository;

        FilterSpecification<Answer> filterSpecification;

        @Override
        public PageResponse<AnswerResponse> getAnswers(AnswerQueryRequest query) {
                // Pagination
                Pageable pageable = QueryUtils.getPagination(query);

                // Filtering by specification
                Specification<Answer> spec = filterSpecification.filteringBySpecification(
                                QueryUtils.getFilterCriterion(query)
                );

                // execute the query
                Page<Answer> pageData = answerRepository.findAll(spec, pageable);

//                return  PageResponse.<AnswerResponse>builder()
//                                .currentPage(pageable.getPageNumber() + 1)
//                                .pageSize(pageData.getSize())
//                                .totalPages(pageData.getTotalPages())
//                                .totalElements(pageData.getTotalElements())
//                                .data(pageData.getContent().stream()
//                                                .map(answerMapper::entityToResponse)
//                                                .toList())
//                                .build();

                return QueryUtils.buildPageResponse(pageData, pageable, answerMapper::entityToResponse);
        }

        @Override
        public AnswerResponse getAnswerById(Integer id) {
                return answerMapper.entityToResponse(answerRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Answer Not Found")));
        }

        @Override
        public AnswerResponse createAnswer(AnswerCreationRequest request) {
                Answer answer = answerMapper.creationRequestToEntity(request);

                answer.setQuestion(questionRepository.findById(request.getQuestionId())
                                .orElseThrow(() -> new RuntimeException("Question Not Found")));

                return answerMapper.entityToResponse(answerRepository.save(answer));
        }

        @Override
        public AnswerResponse updateAnswer(Integer id, AnswerUpdateRequest request) {
                Answer answer = answerRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Answer Not Found"));

                answer.setQuestion(questionRepository.findById(request.getQuestionId())
                                .orElseThrow(() -> new RuntimeException("Question Not Found")));

                answerMapper.updateRequestToEntity(answer, request);
                return answerMapper.entityToResponse(answerRepository.save(answer));
        }

        @Override
        public AnswerResponse patchAnswer(Integer id, AnswerPatchRequest request) {
                Answer answer = answerRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Answer Not Found"));

                answerMapper.patchRequestToEntity(answer, request);
                return answerMapper.entityToResponse(answerRepository.save(answer));
        }

        @Override
        public void deleteAnswer(Integer id) {
                Answer answer = answerRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Answer Not Found"));

                answer.setDeletedAt(LocalDateTime.now());

                answerRepository.save(answer);
        }
}
