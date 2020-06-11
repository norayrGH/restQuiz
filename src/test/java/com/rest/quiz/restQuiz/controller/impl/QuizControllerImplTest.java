package com.rest.quiz.restQuiz.controller.impl;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.service.QuizService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

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
                .jsonPath("$.length()").isEqualTo(7)
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.quizQuestionList").isNotEmpty().returnResult());
    }

    @Test
    void updateByIdShouldSuccess() {
        QuizDTO quizDTO = getQuizDTO();
        quizDTO.setQuizName("Поменял вопрос");
        System.out.println(this.webTestClient
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
                .jsonPath("$.quizName").isEqualTo("Поменял вопрос").returnResult());
    }


    @Test
    void createAndDeleteShouldSuccess() {
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
                .expectBodyList(QuizDTO.class).hasSize(1).returnResult()) ;
    }



    @Test
    void getByIdQuizQuestionShouldSuccess() {
        System.out.println(this.webTestClient
                .get()
                .uri(String.format(Objects.requireNonNull(environment.getProperty("getQuizQuestionById")), 1))
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.question").isNotEmpty().returnResult());
    }

    @Test
    void updateQuizQuestionByIdShouldSuccess() {
        QuizQuestionDTO quizQuestionDTO = getQuizQuestionDTO();
        quizQuestionDTO.setQuestion("Поменял вопрос");
        System.out.println(this.webTestClient
                .put()
                .uri(String.format(Objects.requireNonNull(environment.getProperty("updateQuiz")), 1))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .body(Mono.just(quizQuestionDTO), QuizDTO.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.question").isEqualTo("Поменял вопрос").returnResult());
    }

    private QuizQuestionDTO getQuizQuestionDTO() {
        QuizQuestionDTO quizQuestionDTO = quizService.getQuizQuestionById(1L);
        quizQuestionDTO.setId(null);
        return quizQuestionDTO;
    }

    private QuizDTO getQuizDTO() {
        QuizDTO quizDTO = quizService.getQuizById(1L);
        quizDTO.setId(null);
        for(QuizQuestionDTO quizQuestionDto :quizDTO.getQuizQuestionList()){
            quizQuestionDto.setId(null);
        }
        return quizDTO;

    }
}
