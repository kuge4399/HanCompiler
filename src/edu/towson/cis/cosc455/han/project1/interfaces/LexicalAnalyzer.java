package edu.towson.cis.cosc455.han.project1.interfaces;


public class LexicalAnalyzer {

	private String sourceLine;
	private char[] lexeme = new char [100];
	private char nextChar;
	private int lexLength;
	private int position;
		
	// The main driver of this class. This method takes a "program", in this case a single line of text in
	// the form of a sentence, and gets the first lexeme/token. 
	public void start(String line){
		sourceLine = line;
		position = 0;
		
		getChar();		
		getNextToken();		
	}
	
	// This method does a character-by-character analysis to get the next token and set it in the Compiler
	// class's currentToken global String variable. This simple lexical analyzer does not differentiate 
	// between letters, digits and other special characters - it simply looks for characters, spaces and
	// end of line characters to determine relevant tokens. 
	public void getNextToken() {
		lexLength = 0;
		
		// Ignore spaces and add the first character to the token
		getNonBlank();
		addChar();
		getChar();
		
		// Continue gathering characters for the token
		while((nextChar != '\n') && (nextChar != ' ')){
			addChar();
			getChar();
		}			
		
		// Convert the gathered character array token into a String
		String newToken = new String(lexeme);
		// Set the new token 
		Compiler.currentToken = newToken.substring(0, lexLength);		
	}
	
	// This method gets the next character from the "program" string.
	private void getChar(){
		if (position < sourceLine.length())
			nextChar = sourceLine.charAt(position++);
		else nextChar = '\n';
	}
	
	// A helper method to determine if the current character is a space.
	private boolean isSpace(char c){
		if (c == ' ') return true;
		else return false;
	}
	
	// A helper method to get the next non-blank character.
	private void getNonBlank(){
		while(isSpace(nextChar))getChar();
	}
	
	// This method adds the current character the the token after checking to make
	// sure that the length of the token isn't too long, a lexical error in this
	// case. 
	private void addChar(){
		if(lexLength <= 98){
			lexeme[lexLength++] = nextChar;
			lexeme[lexLength] = 0;
		}
		else{
			System.out.println("LEXICAL ERROR - The found lexeme is too long!");
									
			if(!isSpace(nextChar)){
				while(!isSpace(nextChar)){					
					getChar();						
				}
			}
			lexLength = 0;					
			getNonBlank();
			addChar();
			
		}
	}
}
