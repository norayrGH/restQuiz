package com.rest.quiz.restQuiz.configuration;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class ApiConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rest.quiz.restQuiz.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                    new Contact("Norayr Gharibyan", "restquize", "norayr.gharibyan.java@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Quiz.class, QuizDTO.class).addMappings(mapper -> {
            mapper.map( Quiz::getId,QuizDTO::setId );
            mapper.map( Quiz::getQuizName,QuizDTO::setQuizName );
            mapper.map( Quiz::getQuizQuestionList,QuizDTO::setQuizQuestionList);
            mapper.map( Quiz::getStartDate,QuizDTO::setStartDate);
            mapper.map( Quiz::getEndDate,QuizDTO::setEndDate);
            mapper.map( Quiz::getQuizState,QuizDTO::setQuizState);
        });
        modelMapper.typeMap(QuizDTO.class, Quiz.class).addMappings(mapper -> {
            mapper.map( QuizDTO::getId,Quiz::setId );
            mapper.map( QuizDTO::getQuizName,Quiz::setQuizName );
            mapper.map( QuizDTO::getQuizQuestionList,Quiz::setQuizQuestionList);
            mapper.map( QuizDTO::getStartDate,Quiz::setStartDate);
            mapper.map( QuizDTO::getEndDate,Quiz::setEndDate);
            mapper.map( QuizDTO::getQuizState,Quiz::setQuizState);
        });

        modelMapper.typeMap(QuizQuestion.class, QuizQuestionDTO.class).addMappings(mapper -> {
            mapper.map( QuizQuestion::getId,QuizQuestionDTO::setId );
            mapper.map( src->src.getQuestion().getQuestion(),QuizQuestionDTO::setQuestion );
            mapper.map( QuizQuestion::getDisplayOrder,QuizQuestionDTO::setDisplayOrder );
        });
        return modelMapper;
    }
}
