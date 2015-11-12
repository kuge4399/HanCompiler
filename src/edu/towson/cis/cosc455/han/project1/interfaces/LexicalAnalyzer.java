package edu.towson.cis.cosc455.han.project1.interfaces;

public class LexicalAnalyzer {

	private String sourceLine;
	private char[] lexeme = new char [100];
	private char nextChar;
	private int lexLength;
	private int position;
		

	public void start(String line){
		sourceLine = line;
		position = 0;
		
		getChar();		
		getNextToken();		
	}
	

	public void getNextToken() {
		lexLength = 0;
		
		getNonBlank();
		addChar();
		getChar();
		

		while((nextChar != '\n') && (nextChar != ' ')){
			addChar();
			getChar();
		}			
		
		String newToken = new String(lexeme);
		Compiler.currentToken = newToken.substring(0, lexLength);		
	}
	
	private void getChar(){
		if (position < sourceLine.length())
			nextChar = sourceLine.charAt(position++);
		else nextChar = '\n';
	}
	
	private boolean isSpace(char c){
		if (c == ' ') return true;
		else return false;
	}
	
	private void getNonBlank(){
		while(isSpace(nextChar))getChar();
	}

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
	private boolean lookupToken(){
		
		
	}
	
}
