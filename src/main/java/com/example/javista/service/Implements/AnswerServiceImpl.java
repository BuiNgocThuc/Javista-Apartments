package com.example.javista.service.Implements;

import com.example.javista.dto.request.answer.AnswerCreationRequest;
import com.example.javista.dto.request.answer.AnswerQueryRequest;
import com.example.javista.dto.request.answer.AnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.answer.AnswerResponse;
import com.example.javista.entity.Answer;
import com.example.javista.entity.Question;
import com.example.javista.mapper.AnswerMapper;
import com.example.javista.repository.AnswerRepository;
import com.example.javista.repository.QuestionRepository;
import com.example.javista.service.AnswerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerServiceImpl implements AnswerService {

        AnswerRepository answerRepository;
        AnswerMapper answerMapper;

        // virtual field
        QuestionRepository questionRepository;

        @Override
        public PageResponse<AnswerResponse> getAnswers(AnswerQueryRequest query) {
                return null;
        }

        @Override
        public AnswerResponse getAnswerById(String id) {
                return null;
        }

        @Override
        public AnswerResponse createAnswer(AnswerCreationRequest request) {
                Answer answer = answerMapper.creationRequestToEntity(request);

                Question question = questionRepository.findById(request.getQuestionId())
                                .orElseThrow(() -> new RuntimeException("Question Not Found"));

                answer.setQuestion(question);

                return answerMapper.entityToResponse(answerRepository.save(answer));
        }

        @Override
        public AnswerResponse updateAnswer(String id, AnswerUpdateRequest request) {
                return null;
        }

        @Override
        public void deleteAnswer(String id) {

        }
}
