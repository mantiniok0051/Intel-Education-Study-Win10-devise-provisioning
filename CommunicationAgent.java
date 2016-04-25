package com.ieducation.study.deployment.agent;

//Terminal side code, it implements a reverse client server model

	
	import java.net.*;
	import java.io.*;
	import com.ieducation.study.deployment.agent.protocol.*;

@SuppressWarnings("unused")
public class CommunicationAgent {

	public static void main(String[] args) {
   
					if(createAssets())
						{
				        try ( 
					            ServerSocket terminalSocket = new ServerSocket(portNumber);
					            Socket hubSocket = terminalSocket.accept();
					            PrintWriter out_to_hub = new PrintWriter(hubSocket.getOutputStream(), true);
					            BufferedReader in_from_hub = new BufferedReader(new InputStreamReader(hubSocket.getInputStream()));
				        	 ) 
					        {
				        			my_status = Statuses.CONNECTION_STABLISHED;
						            String response_from_hub, response_to_hub, validate;
	
				            
						            while ((response_from_hub = in_from_hub.readLine()) != null) 
						            {
						            	
						            	if(response_from_hub == Statuses.CONNECTION_STABLISHED.toString() && (my_status == Statuses.CONNECTION_STABLISHED || my_status != Statuses.ERROR)){
												
						            			hub_status = Statuses.CONNECTION_STABLISHED;
						            		    response_to_hub = Statuses.CONNECTION_STABLISHED.toString();
												out_to_hub.println(response_to_hub);
						            		
						            		}
						            	else if(response_from_hub == Commands.IDENTIFY_YOURSELF.toString() && (my_status == Statuses.CONNECTION_STABLISHED || my_status != Statuses.ERROR)){
													
						            				my_status = Statuses.BUSY;
													response_to_hub = sysAgent.getMACAddress();
													   
													if(response_to_hub != null ){out_to_hub.println(response_to_hub); 
																							 my_status = Statuses.LOCAL_MAC_SENT;}
													else{System.out.println("Error while getting MAC Address");
															my_status = Statuses.ERROR;}
										
						            		}
						            	else if(response_from_hub == Commands.VALIDATE_PROFILE.toString() && (my_status == Statuses.READY || my_status == Statuses.LOCAL_MAC_SENT)){
											
						            				my_status = Statuses.BUSY;
						            				boolean profile_exists = sysAgent.validateProfile();
						            				if(profile_exists){response_to_hub = Responses.PROFILE_PRESENT.toString();
																   			my_status = Statuses.PROFILE_VALIDATED;
																   			out_to_hub.println(response_to_hub);}
						            				else{System.out.println("No user profile present in the default location, please refer to the Intel® Education Study documentation");
						            						my_status = Statuses.PROFILE_VALIDATED_BUT_NOT_PRESENT;}
						            		
						            		}
						            	else if(response_from_hub == Commands.CLEAR_PROFILE.toString()){
						            				my_status = Statuses.BUSY;
											 boolean profile_cleared = sysAgent.clearProfile();
											 	if(profile_cleared){ response_to_hub = Responses.PROFILE_CLEARED.toString();
											 								my_status = Statuses.READY;
											 								out_to_hub.println(response_to_hub);}
											 	else{response_to_hub = Responses.PROFILE_NOT_CLEARED.toString();
											 		   System.out.println("Something happened while trying to clear profile...");
											 		  }
						            		
						            		}
						            	else if(response_from_hub == Commands.VALIDATE_CACHE.toString()){
						            			my_status = Statuses.BUSY;
						            			boolean cache_present = sysAgent.validateCache();
						            			if(cache_present){response_to_hub = Responses.CACHE_PRESENT.toString();
						            									 my_status = Statuses.READY;}
					            			}
						            	else if(response_from_hub == Commands.SET_CACHE.toString()){
						            		
						            		}
						            	else if(response_from_hub ==  Commands.ATTAIN_DRIVER.toString()){
						            		
						            		}
						            	else if(response_from_hub == Commands.OPEN_STUDY_APP.toString()){
						            			
						            		}
						            	else if(response_from_hub == Commands.WAIT_FOR_SAPLES.toString()){
						            		
						            		}
						            	else if(response_from_hub == Commands.START_SIGN_IN.toString()){
						            		
						            		}
						            	else if(response_from_hub == Commands.NAVIGATE_TO_LIBRARY.toString()){
						            		
						            		}
						            	else if(response_from_hub == Commands.DOWNLOAD_ALL_BOOKS.toString()){
						            		
						            		}
						           }
				        } catch (IOException e) {
				            System.out.println("Exception caught when trying to listen on port "
				                + portNumber + " or listening for a connection");
				            System.out.println(e.getMessage());
				        }
				}



			}

	
	public static boolean createAssets(){
		
		System.out.println("Intel® Education Study Provisioning tool");
		System.out.println("Seting up depoyer environment...");
		System.out.println("Creating aplication agent");
		
			boolean appagent = false;
			boolean sysagent =  false;
		
				try {	appAgent = new StudyAppAgent();
						if(appAgent != null){System.out.println("Intel® Education Study provisioning agent successfully created XD");
													 appagent = true;}
						}
				catch (InterruptedException e) {    System.err.println("Exception occurred while creating Intel® Education Study provisioning agent X( ");
																	System.out.println("For mor detailed analysis plase review Stack Trace below");
																	e.printStackTrace();}
				
				sysAgent = new SystemAgent();
						if(sysAgent != null){System.out.println("Intel® Education Study system agent successfully created XD");
													sysagent = true;}
						else{System.err.println("Exception occurred while creating Intel® Education Study provisioning agent X( ");}
					
						try {Thread.sleep(60000);} 
						catch (InterruptedException e) {e.printStackTrace();}
					
					if(appagent && sysagent){return true;}
					return false;	
		}
	
	private static StudyAppAgent appAgent=null;
	private static SystemAgent sysAgent=null;
	private static final int portNumber = 9991;
	private static InetAddress hub_IP= null;
	private static Statuses my_status= Statuses.WAITING_FOR_CONNECTION;
	private static Statuses hub_status= Statuses.READY_TO_CONNECT; 
	private static Commands command = Commands.EMPTY_COMMAND;
	private static Responses response = Responses.EMPTY_RESPONSE;
}
//https://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockServer.java