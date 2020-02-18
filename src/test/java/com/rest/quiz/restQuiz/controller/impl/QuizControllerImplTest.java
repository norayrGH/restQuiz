package com.rest.quiz.restQuiz.controller.impl;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import com.rest.quiz.restQuiz.repository.QuizRepository;
import com.rest.quiz.restQuiz.service.QuizService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.google.common.net.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@PropertySource("classpath:api.calls.properties")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class QuizControllerImplTest {

    @Autowired
    private QuizService quizService;

    @Autowired
    private Environment environment;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void getByIdShouldSuccess() {
        System.out.println(this.webTestClient
                .get()
                .uri(String.format(Objects.requireNonNull(environment.getProperty("getQuizById")), 1))
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(6)
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.quizQuestionList[0]").isNotEmpty().returnResult());
    }

    @Test
    void updateByIdShouldSuccess() {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuizName("Поменял вопрос");
        this.webTestClient
                .put()
                .uri(String.format(Objects.requireNonNull(environment.getProperty("updateQuiz")), 1))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .body(Mono.just(quizDTO), QuizDTO.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.quizName").isEqualTo("Поменял вопрос").returnResult();
    }

    @Test
    void  deleteByIdShouldSuccess() {
        //Long lastId = quizService.findLastId();

        webTestClient
                .delete()
                .uri(String.format(Objects.requireNonNull(environment.getProperty("deleteQuiz")), 1))
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void createShouldSuccess() {
        QuizDTO quizDTO = getQuizDTO();
        Long lastId = quizService.findLastId();
        System.out.println(webTestClient.post().uri(Objects.requireNonNull(environment.getProperty("createQuiz")))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .body(Mono.just(quizDTO), QuizDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.id").isEqualTo(lastId+1).returnResult()
        );

        System.out.println(webTestClient
                .delete()
                .uri(String.format(Objects.requireNonNull(environment.getProperty("deleteQuiz")), lastId+1))
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk());

    }

    @Test
    void getAllShouldSuccess() {
        System.out.println(webTestClient
                .get()
                .uri(Objects.requireNonNull(environment.getProperty("getAllQuizes")))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(QuizDTO.class).hasSize(1).returnResult());
    }

    private QuizDTO getQuizDTO() {
        QuizDTO quizDTO = quizService.getQuizById(1L);
        quizDTO.setId(null);
        for(QuizQuestionDTO quizQuestionDto:quizDTO.getQuizQuestionList()){
            quizQuestionDto.setId(null);
        }
        return quizDTO;

    }

    /*private QuizDTO createQuiz() {
        QuizDTO quiz = new QuizDTO();
        quiz.setActivity(true);
        quiz.setEndDate(new Date());
        quiz.setQuizName("Some Question");
        quiz.setStartDate(new Date());
        quiz.setQuizQuestionList(createQuizQuestion(3));
        return quiz;
    }

    private List<QuizQuestionDTO> createQuizQuestion(int countOfQuestion) {
        List<QuizQuestionDTO> quizQuestions = new ArrayList<>();
        for (int i = 0; i < countOfQuestion; i++) {
            QuizQuestionDTO quizQuestion = new QuizQuestionDTO();
            quizQuestion.setQuestion("question Number: "+i);
            quizQuestion.setDisplayOrder(i);
            quizQuestions.add(quizQuestion);

        }
        return quizQuestions;
    }
*/
}
