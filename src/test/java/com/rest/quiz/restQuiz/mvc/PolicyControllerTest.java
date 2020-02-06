package com.rest.quiz.restQuiz.mvc;

import com.rest.quiz.restQuiz.RestQuizApplication;
import com.rest.quiz.restQuiz.configuration.H2TestProfileJPAConfig;
import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.service.QuizService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestQuizApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
@PropertySource("classpath:api.calls.properties")
public class PolicyControllerTest {

    @Autowired
    private QuizService quizService;

    @Autowired
    Environment environment;

    private static ClientAndServer clientAndServer;

    @BeforeClass
    public static void setupMockServer() {
        clientAndServer = ClientAndServer.startClientAndServer(8000);

    }
    @AfterClass
    public static void after(){
        clientAndServer.stop();
    }


    @Test
    public void savedQuiz() {
        QuizDTO quiz = quizService.getQuizById(1L);
        //quiz.setId(null);
        //Quiz savedQuiz = quizRepository.save(quiz);
        //assertThat(savedUser.getRegistrationDate()).isNotNull();
    }

    public Quiz createQuiz(){
        return new Quiz(1L,"test",null,new Date(),new Date(),true);
    }
}
