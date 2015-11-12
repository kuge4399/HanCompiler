package edu.towson.cis.cosc455.han.project1.interfaces;
// COSC 455 Programming Languages: Implementation and Design
// Lab 2 - A Simple Syntax Analyzer
// Josh Dehlinger
//
// This syntax analyzer implements a top-down, left-to-right, recursive-descent parser based
// on the production rules for the simple lolspeak language described in Labs 1 & 2.

import java.util.*;

public class SyntaxAnalyzer {

	private String DOCB;
	private String DOCE;
	private String HEAD;
	private String TITLEB;
	private String TITLEE;
	private String PARAB;
	private String PARAE;
	private String DEFB;
	private String DEFUSEE;
	private String EQSIGN;
	private String USEB;
	private String BOLD;
	private String ITALICS;
	private String LISTITEMB;
	private String LISTITEME;
	private String NEWLINE;
	private String LINKB;
	private String LINKE;
	private String AUDIO;
	private String VIDEO;
	private String ADDRESSB;
	private String ADDRESSE;
	private String TEXT;
	

	protected boolean errorFound;

	
	public SyntaxAnalyzer(){
	
		DOCB = "#BEGIN";
	
		DOCE ="#END";

		HEAD ="^";	
		
		TITLEB ="<";	
		
		TITLEE =">";	
		
		PARAB ="{";	
		
		PARAE ="}";	
		
		DEFB ="$DEF";	
		
		DEFUSEE ="$END";	
		
		EQSIGN ="=";	
		
		USEB ="$USE";	
		
		BOLD ="**";	
		
		ITALICS ="*";	
		
		LISTITEMB ="+";	
		
		LISTITEME =";";	
		
		NEWLINE ="~";	
		
		LINKB ="[";	
		
		LINKE ="]";	
		
		AUDIO ="@";	
		
		VIDEO ="%";	
		
		ADDRESSB ="(";	
		
		ADDRESSE =")";	
	
	}

//===========================================================================
	public void markdown(){
		resetError();
		if(Compiler.currentToken.equalsIgnoreCase(DOCB)){
		 variable_define();
		 head();
		 body();
		 DOCE();
		}
	}
	protected void head(){
		if(Compiler.currentToken.equalsIgnoreCase(HEAD)){
			title();
			HEAD();

			}
		else{}
	}
	protected void variable_define(){
		if(Compiler.currentToken.equalsIgnoreCase(DEFB)){
			 TEXT();
			 EQSIGN();
			 TEXT();
			 DEFUSEE();
			 variable_define();
			}
		else{}
	}
	
	protected void title(){
		if(Compiler.currentToken.equalsIgnoreCase(TITLEB)){
			 TEXT();
			 TITLEE();
			}
		else{}
	}
	
	protected void body(){
		variable_use();
		
		
	}
	protected void variable_use(){
		if(Compiler.currentToken.equalsIgnoreCase(USEB)){
			 TEXT();
			 DEFUSEE();
			 
			}
		else{}
	}

	protected void link(){
		if(Compiler.currentToken.equalsIgnoreCase(LINKB)){
			 TEXT();
			 LINKE();
			 ADDRESSB();
			 TEXT();
			 ADDRESSE();
			}
		else{}
	}
	protected void audio(){
		if(Compiler.currentToken.equalsIgnoreCase(AUDIO)){
			 ADDRESSB(); 
			 TEXT();
			 ADDRESSE();
			}
		else{}
	}
	protected void video(){
		if(Compiler.currentToken.equalsIgnoreCase(VIDEO)){
			ADDRESSB(); 
			 TEXT();
			 ADDRESSE();
			}
		else{}
	}
	protected void newline(){
		if(Compiler.currentToken.equalsIgnoreCase(TITLEB)){

			}
		else{}
	}
	protected void italics(){
		if(Compiler.currentToken.equalsIgnoreCase(ITALICS)){
			 TEXT();
			 ITALICS();
			}
		else{}
	}
	protected void listitem(){
		if(Compiler.currentToken.equalsIgnoreCase(LISTITEMB)){
			 inner_item();
			 LISTITEME();
			 listitem();
			}
		else{}
	}
	protected void bold(){
		if(Compiler.currentToken.equalsIgnoreCase(BOLD)){
			 TEXT();
			 BOLD();
			}
		else{}
	}
	protected void paragraph(){
			PARAB();
			variable_define();
			inner_text();
			PARAE();

	}
	//===========================================================
	protected void inner_item(){
		if(Compiler.currentToken.equalsIgnoreCase(USEB)){
			 TEXT();
			 DEFUSEE();
			 inner_item();
			}
		else if(Compiler.currentToken.equalsIgnoreCase(BOLD)){
			TEXT();
			BOLD();
			inner_item();

		}
		else if(Compiler.currentToken.equalsIgnoreCase(ITALICS)){
			TEXT();
			ITALICS();
			inner_item();
		}
		else if(Compiler.currentToken.equalsIgnoreCase(LINKB)){
			ADDRESSB();
			TEXT();
			ADDRESSE();
			inner_item();
		}
		else if(Compiler.currentToken.equalsIgnoreCase(TEXT)){
			inner_item();
		}
		else{}
	}
	protected void inner_text(){
		if(Compiler.currentToken.equalsIgnoreCase(USEB)){
			 TEXT();
			 DEFUSEE();
			 inner_text();
			}
		else if(Compiler.currentToken.equalsIgnoreCase(BOLD)){
			TEXT();
			BOLD();
			inner_text();

		}
		else if(Compiler.currentToken.equalsIgnoreCase(ITALICS)){
			TEXT();
			ITALICS();
			inner_text();
		}
		else if(Compiler.currentToken.equalsIgnoreCase(LISTITEMB)){
			inner_item();
			LISTITEME();
			listitem();
		}
		else if(Compiler.currentToken.equalsIgnoreCase(TEXT)){
			inner_text();
		}
		else if(Compiler.currentToken.equalsIgnoreCase(LINKB)){
			ADDRESSB();
			TEXT();
			ADDRESSE();
			inner_text();
		}
		else if(Compiler.currentToken.equalsIgnoreCase(TEXT)){
			inner_text();
		}
		else{}
	}
	
//==============================================================================
	
	protected void DOCB(){
		if (!DOCB.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();

	}
	protected void DOCE(){
		if (!DOCE.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();

	}

	protected void HEAD(){
		if (!HEAD.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}


	protected void TITLEE(){
		if (!TITLEE.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void TITLEB(){
		if (!TITLEB.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void PARAB(){
		if (!PARAB.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void PARAE(){
		if (!PARAE.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void DEFB(){
		if (!DEFB.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void DEFUSEE(){
		if (!DEFUSEE.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void EQSIGN(){
		if (!EQSIGN.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void USEB(){
		if (!USEB.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void BOLD(){
		if (!BOLD.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void ITALICS(){
		if (!ITALICS.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void LISTITEMB(){
		if (!LISTITEMB.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void LISTITEME(){
		if (!LISTITEME.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void NEWLINE(){
		if (!NEWLINE.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void LINKB(){
		if (!LINKB.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void LINKE(){
		if (!LINKE.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void AUDIO(){
		if (!AUDIO.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void VIDEO(){
		if (!VIDEO.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void ADDRESSB(){
		if (!ADDRESSB.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}
	protected void ADDRESSE(){
		if (!ADDRESSE.contains(Compiler.currentToken)){
			System.out.println("SYNTAX ERROR");
			setError();
		}
		else
			Compiler.Lexer.getNextToken();
	}


	void setError(){errorFound = true;}
	void resetError(){errorFound = false;}
	boolean getError(){return errorFound;}
}
