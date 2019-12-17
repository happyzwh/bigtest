package com.zwh.domain;


@Atest(name="test")
public class Ado {

	public static void main(String[] args){
		
		Class<Ado> clazz = Ado.class;
		
		Atest table =  clazz.getAnnotation(Atest.class);
		System.out.println(table.name());
		
	}
	
}
