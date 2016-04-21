package com.ieducation.study.deployment.agent;

//Terminal side code, it implements a reverse client server model

	
	import java.net.*;
	import java.io.*;
	import com.ieducation.study.deployment.agent.protocol.*;

@SuppressWarnings("unused")
public class CommunicationAgent {

	public static void main(String[] args) {
   
					createAssets();

        try ( 
		            ServerSocket terminalSocket = new ServerSocket(portNumber);
		            Socket hubSocket = terminalSocket.accept();
		            PrintWriter out_to_hub =
		                new PrintWriter(hubSocket.getOutputStream(), true);
		            BufferedReader in_from_hub = new BufferedReader(
		                new InputStreamReader(hubSocket.getInputStream()));
	        ) 
	        {
        
		            String response_from_hub, response_to_hub, validate;
		            commprotocol = new Protocol();
            
            
		            while ((response_from_hub = in_from_hub.readLine()) != null) {
		            	
		            	switch (response_from_hub) {
						case Statuses.CONNECTION_STABLISHED.toString():
										response_to_hub = response_from_hub;
										out_to_hub.println(response_to_hub);
							break;
						case Commands.IDENTIFY_YOURSELF:
								
									response_to_hub = sysAgent.getMACAddress();
									   
									if(response_to_hub != null ){out_to_hub.println(response_to_hub);}
									else{System.out.println("Error while getting MAC Address");}
							
							break;
						case Commands.VALIDATE_PROFILE:
								boolean profile_exists = sysAgent.validateProfile();
									if(profile_exists){validate = commprotocol.processInput(response_from_hub);
														     if(validate == "OK"){
														    	 response_to_hub = "PROFILE_NOT_PRESENT";
														    	 out_to_hub.println(response_to_hub);
														     	}
														   }
							break;
						case Commands.CLEAR_PROFILE:
								 boolean profile_cleared = sysAgent.clearProfile();
								 	if(profile_cleared){
								 		response_to_hub = commprotocol.processInput(response_from_hub);
								 	}
							break;
						case  Commands.VALIDATE_CACHE:
							
							break;
						case Commands.SET_CACHE:
						
							break;
						case Commands.ATTAIN_DRIVER:
						
							break;
						case Commands.OPEN_STUDY_APP:
							
							break;
						case Commands.WAIT_FOR_SAPLES:
							
							break;
						case Commands.START_SIGN_IN:
							
							break;
						case Commands.NAVIGATE_TO_LIBRARY:
							
							break;
						case Commands.DOWNLOAD_ALL_BOOKS:
							
							break;
						default:
							break;
						}
		            	
		//                response_to_hub = commprotocol.processInput(response_from_hub);
		//                out_to_hub.println(response_to_hub);
		//                if (response_from_hub.equals(commprotocol.IDENTIFY_YOURSELF)){}
		//                else if (response_to_hub.equals("get_local_mac")){break;}   
		            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }

	}

	
	public static void createAssets(){
		
		System.out.println("Intel® Education Study Provisioning tool");
		System.out.println("Seting up depoyer environment...");
		System.out.println("Creating aplication agent");
		
				try {	appAgent = new StudyAppAgent();
						if(appAgent != null)System.out.println("Intel® Education Study provisioning agent successfully created XD");} 
				catch (InterruptedException e) {System.err.println("Exception occurred while creating Intel® Education Study provisioning agent X( ");
																	System.out.println("For mor detailed analysis plase review Stack Trace below");
																	e.printStackTrace();}
				
				sysAgent = new SystemAgent();
						if(sysAgent != null){System.out.println("Intel® Education Study system agent successfully created XD");}
					
				
					try {Thread.sleep(60000);} 
					catch (InterruptedException e) {e.printStackTrace();}
	}
	
//	private static String status 
	
	private static StudyAppAgent appAgent=null;
	private static SystemAgent sysAgent=null;
	private static Protocol commprotocol = null;
	private static final int portNumber = 9991;
	private static InetAddress hub_IP= null;
	private static Statuses actual_status= null;
	private static Commands command = null;
	private static Responses response = null;
}
//https://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockServer.java