package edu.towson.cis.cosc455.han.project1.implementation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Compiler {
	
	public static String currentToken;
	public static MyLexicalAnalyzer Lexer;
	

	public static void main(String[] args) throws IOException {
		String fname="Test2.mkd";
		
		if(fname.endsWith("mkd")){
		}
		else{
			System.out.println("Usage error");
			fname="aa";
		}
		Lexer = new MyLexicalAnalyzer();
				
		MySyntaxAnalyzer Parser = new MySyntaxAnalyzer();
		
		FileReader fr = new FileReader(fname);
		BufferedReader br = new BufferedReader(fr); 
		
		String sourceLine = null; 
		
 
		while((sourceLine = br.readLine()) != null){ 
			Lexer.start(sourceLine);

			Parser.markdown();
			

			System.out.println(sourceLine);
			
			System.out.println();
		} 
		fr.close(); 
	}
}
