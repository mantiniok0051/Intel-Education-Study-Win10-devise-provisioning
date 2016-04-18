package com.ieducation.study.deployment.agent;

		import javax.net.ssl.*;
		import javax.net.*;
		import java.io.*;
		import java.net.*;

@SuppressWarnings("unused")
public class CommunicationsAgent {

	public CommunicationsAgent() {
	    	ServerSocketFactory serverSocketFactory = ServerSocketFactory.getDefault();
	    	ServerSocket serverSocket = null;
				    try {serverSocket = serverSocketFactory.createServerSocket(port_num);} 
				    catch (IOException ignored) {System.err.println("Unable to create server");
				      												System.exit(-1); }
		   System.out.printf("LogServer running on port: %s%n", port_num);
				    while (true) { Socket socket = null;
									      try {
											        socket = serverSocket.accept();
											        InputStream is = socket.getInputStream();
											        BufferedReader br = new BufferedReader( new InputStreamReader(is, "US-ASCII"));
											        String line = null; 
												        while ((line = br.readLine()) != null) {System.out.println(line);}
									           } catch (IOException exception) {
									        // Just handle next request.
									      } finally {
													        if (socket != null) {
																					          try {socket.close();} 
																					          catch (IOException connection_fail) {connection_fail.printStackTrace(); }
													        							}
									      			   }
				    				}
	  }
	
	private static final int port_num = 9991;
	
}
