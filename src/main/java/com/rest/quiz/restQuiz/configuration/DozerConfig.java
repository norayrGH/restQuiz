package com.rest.quiz.restQuiz.configuration;

import com.rest.quiz.restQuiz.dto.QuizDTO;
import com.rest.quiz.restQuiz.dto.QuizQuestionDTO;
import com.rest.quiz.restQuiz.model.Quiz;
import com.rest.quiz.restQuiz.model.QuizQuestion;
import com.rest.quiz.restQuiz.service.mapper.QuizQuestionDTOConvert;
import org.dozer.DozerBeanMapper;
import org.dozer.classmap.RelationshipType;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.dozer.loader.api.FieldsMappingOptions.*;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;

@Configuration
public class DozerConfig {

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(QuizDTO.class, Quiz.class,mapNull(false),
                        TypeMappingOptions.oneWay())
                        .fields("quizQuestionList", "quizQuestionList",
                                FieldsMappingOptions.oneWay(), customConverter(QuizQuestionDTOConvert.class));
            }
        };
    }
    @Bean
    public DozerBeanMapper beanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }

}
