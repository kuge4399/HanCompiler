package edu.towson.cis.cosc455.han.project1.interfaces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Compiler {
	
	public static String currentToken;
	public static LexicalAnalyzer Lexer;
	

	public static void main(String[] args) throws IOException {
		Lexer = new LexicalAnalyzer();
				
		SyntaxAnalyzer Parser = new SyntaxAnalyzer();
		
		FileReader fr = new FileReader(args[0]);
		BufferedReader br = new BufferedReader(fr); 
		
		String sourceLine = null; 
		
 
		while((sourceLine = br.readLine()) != null){ 
			Lexer.start(sourceLine);

			Parser.markdown();
			
			if(!Parser.getError())
				System.out.println("The sentence '" + sourceLine + "' follows the BNF grammar.");
			else
				System.out.println("The sentence '" + sourceLine + "' does not follow the BNF grammar.");
			
			System.out.println();
		} 
		fr.close(); 
	}
}
