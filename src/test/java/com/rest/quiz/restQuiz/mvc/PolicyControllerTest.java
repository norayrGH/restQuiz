package com.rest.quiz.restQuiz.mvc;

import com.google.gson.Gson;
import com.rest.quiz.restQuiz.controller.impl.QuizControllerImpl;
import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.repository.QuizRepository;
import com.rest.quiz.restQuiz.service.QuizService;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@WebMvcTest(QuizControllerImpl.class)
@PropertySource("classpath:api.calls.properties")
public class PolicyControllerTest {
    @MockBean
    QuizService quizService;
    @MockBean
    QuizRepository quizRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    Environment environment;

    @Test
    public void getById() throws Exception{
        QuizDTO quizDTO = quizService.getQuizById(1L);
        Gson gson = new Gson();

        given(quizService.getQuizById(1L)).willReturn(quizDTO);
        mockMvc.perform(
                get(String.format(Objects.requireNonNull(environment.getProperty("quiz.getQuizById")), 1)).
                        accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(gson.toJson(quizDTO)));
    }
}
