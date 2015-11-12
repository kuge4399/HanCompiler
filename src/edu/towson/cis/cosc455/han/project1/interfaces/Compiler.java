package edu.towson.cis.cosc455.han.project1.interfaces;
// COSC 455 Programming Languages: Implementation and Design
// Lab 2 - A Simple Compiler
// Josh Dehlinger
//
// A simple compiler used for the simple lolspeak grammar described in Lab 1 & Lab 2.
//
// This compiler assumes that the source file containing the sentences to parse are provided as the first
// runtime argument. Within the source file, the compiler assumes that each sentence to parse is provided
// on its own line. 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Compiler {
	
	// Global variable for the current token
	public static String currentToken;
	// Instance of the lexical analyzer
	public static LexicalAnalyzer Lexer;
	
	// It is assumed that the first argument provided is the name of the source file
	// that is to be "compiled". 
	public static void main(String[] args) throws IOException {
		// Initializations
		Lexer = new LexicalAnalyzer();
				
		// TODO: For the lab, you will replace the #1 declaration for the
		// 		 #2 declaration so that the compiler uses your extended parser. 
		/* 1 */  SyntaxAnalyzer Parser = new SyntaxAnalyzer();
		/* 2 */ //ExtendedSyntaxAnalyzer Parser = new ExtendedSyntaxAnalyzer();
		
		FileReader fr = new FileReader(args[0]);
		BufferedReader br = new BufferedReader(fr); 
		
		String sourceLine = null; 
		
		// Read each line in the source file to be compiled as a unique sentence to 
		// check against the grammar. 
		while((sourceLine = br.readLine()) != null){ 
			// Get the first token
			Lexer.start(sourceLine);
			
		
			Parser.markdown();
			
			// If no syntax error was discovered, print that the sentence follows the grammar.
			if(!Parser.getError())
				System.out.println("The sentence '" + sourceLine + "' follows the BNF grammar.");
			// If a syntax error was found, print that the sentence does not follow the grammar. 
			else
				System.out.println("The sentence '" + sourceLine + "' does not follow the BNF grammar.");
			
			System.out.println();
		} 
		// Close the file and clean up
		fr.close(); 
	}
}
