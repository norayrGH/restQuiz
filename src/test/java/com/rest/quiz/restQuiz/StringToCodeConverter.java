package com.rest.quiz.restQuiz;

import org.dozer.DozerConverter;

public class StringToCodeConverter extends DozerConverter {

	public StringToCodeConverter(){
		super(String.class,String.class);
	}








	@Override
	public Object convertTo(Object source, Object destination) {
		if(getParameter().equals("STATE")){
			//In real world application, Fetch state code and display value map from cache and convert
			if(source!=null && source.equals("Florida")){
				return "FL";
			}
		}
		return null;
	}

	@Override
	public Object convertFrom(Object source, Object destination) {

		// TODO Auto-generated method stub
		return convertTo(source,destination);
	}
}