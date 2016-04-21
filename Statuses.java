package com.ieducation.study.deployment.agent.protocol;

public enum Statuses {
		
	//=====================Common_status_codes=============================//

    CONNECTION_STABLISHED,  
    ERROR, 
    OK, 
    READY, 
    BUSY, 
//====================Hub_status_codes=============================//
 
	   READY_TO_CONNECT	, 
	   GETTING_CORRESPONDING_CREDENTIALS, 
	   CORRESPONDING_CREDENTIALS_SENT, 

//=====================Terminal_status_codes=============================//
	 LOCAL_MAC_SENT, 
	 WAITING_FOR_CONNECTION, 
     PROFILE_VALIDATED, 
     CACHE_VALIDATED, 
     STUDY_DVR_ATTAINED, 
     STUDY_APP_OPENED, 
     WAITING_FOR_SAMPLES, 
     SAMPLES_DOWNLOADED, 
     WAITING_FOR_SIGNIN, 
     COVERS_LOADED, 
     WAITING_FOR_BOOKS, 
     BOOKS_LOADED, 
     
  //====================Class-members==================================//


}
