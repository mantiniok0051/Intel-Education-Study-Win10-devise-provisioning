package com.ieducation.study.deployment.agent;

import java.net.*;
import java.io.*;

//@SuppressWarnings("unused")
public class ControlAgent {
	public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println( "Error: No target Host was indicated");
            System.exit(1);
        }
 
        				hostName = args[0];
        
 
        try (	Socket hubSocket = new Socket(hostName, portNumber);
        			PrintWriter out_to_terminal = new PrintWriter(hubSocket.getOutputStream(), true);
        			BufferedReader in_from_terminal = new BufferedReader(new InputStreamReader(hubSocket.getInputStream()));)
        {
            BufferedReader system_console_input = new BufferedReader(new InputStreamReader(System.in));
            String response_from_terminal;
            String human_user_responses;
 
            while ((response_from_terminal = in_from_terminal.readLine()) != null) {
				                System.out.println("Terminal says " + response_from_terminal);
				                if (response_from_terminal.equals("Bye."))
				                    break;
				                 
				                human_user_responses = system_console_input.readLine();
				                if (human_user_responses != null) {
				                    System.out.println("Client: " + human_user_responses);
				                    out_to_terminal.println(human_user_responses);
				                }
            		}
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }

	private static String hostName = null;
	private static final int portNumber = 9991;
}
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockClient.java