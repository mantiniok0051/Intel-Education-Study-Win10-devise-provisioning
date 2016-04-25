package com.ieducation.study.deployment.agent;

import java.net.*;
import java.io.*;


@SuppressWarnings("unused")
public class Protocol {

	public Protocol (){}
	
	
	public String processInput(String command) {
       String response = null;
      
        if (command == CONNECTION_STABLISHED && terminal_status == WAITING_FOR_CONNECTION && server_status == READY_TO_CONNECT ) 
        				{ response =CONNECTION_STABLISHED;
				           terminal_status = CONNECTION_STABLISHED;
        				   server_status = CONNECTION_STABLISHED;
        				}
        else if (terminal_status == CONNECTION_STABLISHED && server_status == CONNECTION_STABLISHED)
        				{
				            if ( command == LOCAL_MAC_SENT) {
				                response = NO_ERRORS;
				                terminal_status = command;
				                server_status = READY;
                
				            } 
				            else { response = ERROR ;
				            }
        				} 
        else if (terminal_status == LOCAL_MAC_SENT && server_status == BUSY) 
        						{
	        						if (command == VALIDATE_CACHE) {terminal_status = command;
	            																	    server_status = READY;
	            																	    response = NO_ERRORS;}
        						}
            	

//                theOutput = answers[currentJoke] + " Want another? (y/n)";
//                state = ANOTHER;
//            } else {
//                theOutput = "You're supposed to say \"" + 
//			    clues[currentJoke] + 
//			    " who?\"" + 
//			    "! Try again. Knock! Knock!";
//                state = SENTKNOCKKNOCK;
//            }
//        } else if (state == ANOTHER) {
//            if (theInput.equalsIgnoreCase("y")) {
//                theOutput = "Knock! Knock!";
//                if (currentJoke == (NUMJOKES - 1))
//                    currentJoke = 0;
//                else
//                    currentJoke++;
//                state = SENTKNOCKKNOCK;
//            } else {
//                theOutput = "Bye.";
//                state = WAITING;
//            }
//        }
        return response;
    }
	
//=====================Common status codes=============================//

	      final String CONNECTION_STABLISHED = "CONNECTION_STABLISHED";
	      final String ERROR = "ERROR";
	      final String NO_ERRORS = "OK";
	      final String READY = "READY";
	      final String BUSY = "BUSY";
//====================Hub status codes=============================//
	   
		  final String READY_TO_CONNECT = "READY_TO_CONNECT"	;
    	  final String GETTING_CORRESPONDING_CREDENTIALS = "GETTING_CORRESPONDING_CREDENTIALS";
    	  final String CORRESPONDING_CREDENTIALS_SENT = "CORRESPONDING_CREDENTIALS_SENT";

//=====================Terminal status codes=============================//
    	  final String LOCAL_MAC_SENT = "LOCAL_MAC_SENT";
		  final String WAITING_FOR_CONNECTION = "WAITING_FOR_CONNECTION";
	      final String PROFILE_VALIDATED = "PROFILE_VALIDATED";
	      final String CACHE_VALIDATED = "CACHE_VALIDATED";
	      final String STUDY_DVR_ATTAINED = "STUDY_DVR_ATTAINED";
	      final String STUDY_APP_OPENED = "STUDY_APP_OPENED";
	      final String WAITING_FOR_SAMPLES = "WAITING_FOR_SAMPLES";
	      final String SAMPLES_DOWNLOADED = "SAMPLES_DOWNLOADED";
	      final String WAITING_FOR_SIGNIN = "WAITING_FOR_SIGNIN";
	      final String COVERS_LOADED = "COVERS_LOADED";
	      final String WAITING_FOR_BOOKS = "WAITING_FOR_BOOKS";
	      final String BOOKS_LOADED = "BOOKS_LOADED";

 //=====================Hub commands to terminal=============================//	    
	    
	      final String IDENTIFY_YOURSELF ="IDENTIFY_YOURSELF";
	      final String VALIDATE_PROFILE = "VALIDATE_PROFILE";
	      final String CLEAR_PROFILE = "CLEAR_PROFILE";
	      final String VALIDATE_CACHE = "VALIDATE_CACHE";
	      final String SET_CACHE = "SET_CACHE";
	      final String ATTAIN_DRIVER = "ATTAIN_DRIVER";
	      final String OPEN_STUDY_APP = "OPEN_STUDY_APP";
	      final String WAIT_FOR_SAPLES = "WAIT_FOR_SAPLES";
	      final String START_SIGN_IN = "START_SIGN_IN";
	      final String NAVIGATE_TO_LIBRARY = "NAVIGATE_TO_LIBRARY";
	      final String DOWNLOAD_ALL_BOOKS = "DOWNLOAD_ALL_BOOKS";
	    
 //=====================Terminal response codes=============================//	    
	    
	      final String PROFILE_PRESENT = "PROFILE_PRESENT";
	      final String PROFILE_NOT_PRESENT = "PROFILE_NOT_PRESENT";
	      final String PROFILE_CLEARED = "PROFILE_CLEARED";
	      final String CACHE_PRESENT = "CACHE_PRESENT";
	      final String CACHE_ABSENT = "CACHE_ABSENT";
	      final String SIGN_IN_SUCCESSFULL = "SIGN_IN_SUCCESSFULL";
	      final String SIGN_IN_UNSUCCESSFULL = "SIGN_IN_UNSUCCESSFULL";
	    
	      private String terminal_status = WAITING_FOR_CONNECTION;
	      private String server_status = READY_TO_CONNECT;




	    

}
