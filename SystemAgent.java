package com.ieducation.study.deployment.agent;

	import java.io.File;
	import java.net.InetAddress;
	import java.net.NetworkInterface;
	import java.net.SocketException;
	import java.net.UnknownHostException;

	import org.apache.commons.io.FileUtils;

	


//@SuppressWarnings("unused")
			public class SystemAgent {
			
				public SystemAgent() {
						setProfileURL();
							System.out.println("Profile path: "+profileURL.getPath());
						setCacheURL();
							System.out.println("Cache path: "+cacheURL.getPath());
						setBooksFolder();
							System.out.println("Books path: "+booksFolder.getPath());

				}
			
		
				
				
				public static boolean setProfileURL(){
						String baseURL = System.getenv("APPDATA");
						String profileFolder = baseURL + "\\Kno.TextBooks";
						File target = new File(profileFolder);
							if(target.exists() && target.isDirectory()){profileURL = target;
																			        return true; }
					return false;
				}
				
				public static boolean setCacheURL(){
						String cacheurl = new String("C:\\KnoBookCache");
						File target = new File(cacheurl);
							if(target.exists() && target.isDirectory()){cacheURL = target;
																							return true;}
					return false;
				}
				
				public static boolean setBooksFolder(){
					String baseURL = System.getenv("APPDATA");
					String cacheurl = baseURL + "\\Kno.TextBooks\\BOOKS";
					File target = new File(cacheurl);
						if(target.exists() && target.isDirectory()){booksFolder = target;
																						return true;}
				return false;
			}
				
				public boolean monitorBookCoverSideload(long expectedFolderSize_bytes){
						long actual_folder_size = getFolderSize(booksFolder);
							
							if (actual_folder_size >= expectedFolderSize_bytes) {return true;}
						return false;
				}
				
				public boolean monitorBookCoverSideload(){
						long folder_size = getFolderSize(booksFolder);
								
							if (folder_size >= 218000) {return true;}
								
						return false; 
				}
				
				public boolean monitorPackageSideload(){ 
					long folder_size = getFolderSize(booksFolder);	
					
						if (folder_size >= 117000000) { return true;}
					
					return false;
				}

				public static boolean monitorPackageSideload(long cache_package_size_bytes ){
					long folder_size = getFolderSize(booksFolder);
					
						if (folder_size >= cache_package_size_bytes) {return true;}
					
					return false;
				}
				
				public boolean monitorBooksDownload( ){
					long folder_size = getFolderSize(booksFolder);
					
					
						do{
								if (folder_size >= 10400000) {return true;}
								else{
										try {Thread.sleep(30000);
											   folder_size = getFolderSize(booksFolder);} 
										catch (InterruptedException e) {e.printStackTrace();}
									  }	
							}
						while(folder_size >= 10400000 );
					
					return false;
				}
				
				public static long getFolderSize(File directory) {
				    long length = 0;
				    for (File file : directory.listFiles()) {
				        if (file.isFile()){
					            length += file.length();
					        	//System.out.println(file.getName()+" FolderSize= "+length);
				    			}
					    else{
					            length += getFolderSize(file);
					        	//System.out.println(file.getName()+" FolderSize= "+length);
				              }
				    }
				    System.out.println(directory.getName()+"_Folder Size= "+length+" Bytes.");
				    return length;
				}
				
				public String getMACAddress(){
					String mac_addresss = null;
					try {
						    InetAddress IP = InetAddress.getLocalHost();
							NetworkInterface network = NetworkInterface.getByInetAddress(IP);
							byte[] mac = network.getHardwareAddress();
							StringBuilder formater = new StringBuilder();
							
								for(int i = 0; i < mac.length; i++){
										formater.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
								}
								mac_addresss = formater.toString();
						 } 
					catch (UnknownHostException e) {e.printStackTrace();}
					catch (SocketException e) {e.printStackTrace();}
					
					System.out.println("My Physical address: "+mac_addresss);
					
					return mac_addresss;
				}
				
				public boolean validateProfile(){
					if(profileURL != null ){return true;}
					return false;
				}

				public boolean clearProfile(){
					if(profileURL != null){	System.out.println("Ready to clear user profile and content cache");
												   	System.out.println("Folder to be erased: "+profileURL.toString());
												   	FileUtils.deleteQuietly(profileURL);
												   	return  true;
				   }
					else if(setProfileURL()){clearProfile();}
					
					return false;
				}
				
				public boolean validateCache(){
					if(cacheURL != null){return true;}
					
					return false;
				}
//===============Environment & Runtime Variables=====================================================//		
										private static int profileFolderSize = 0;
										private static File profileURL = null;
										private static File cacheURL = null;
										private static File booksFolder = null;
//===============Getters and Setters for environment & runtime variables===================================================//		
										public static int getProfileFolderSize() {return profileFolderSize;}

										public static void setProfileFolderSize(int profileFolderSize) {SystemAgent.profileFolderSize = profileFolderSize;}

										public static File getProfileURL() {return profileURL;}

										public static void setProfileURL(File profileURL) {SystemAgent.profileURL = profileURL;}

										public static File getCacheURL() {return cacheURL;}

										public static void setCacheURL(File cacheURL) {SystemAgent.cacheURL = cacheURL;}

										public static File getBooksFolder() {return booksFolder;}

										public static void setBooksFolder(File booksFolder) {SystemAgent.booksFolder = booksFolder;}


										
				
			}
