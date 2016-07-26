package com.ieducation.study.deployment.agent;

	import java.io.File;
	import java.io.IOException;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.nio.file.Path;
	import java.util.concurrent.*;
	
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.winium.DesktopOptions;
	import org.openqa.selenium.winium.WiniumDriver;
	
	import java.util.List;
	import java.util.Map;

@SuppressWarnings("unused")
			public class FileSystemAgent {
			
				public FileSystemAgent() {
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
				
				public static boolean monitorBookCoverSideload(long expectedFolderSize_bytes){
						long actual_folder_size = getFolderSize(booksFolder);
							
							if (actual_folder_size >= expectedFolderSize_bytes) {return true;}
						return false;
				}
				
				public static boolean monitorBookcoverSideload(){
						long folder_size = getFolderSize(booksFolder);
								
							if (folder_size >= 218000) {return true;}
								
						return false; 
				}
				
				public static boolean monitorPackageSideload(){ 
					long folder_size = getFolderSize(booksFolder);	
					
						if (folder_size >= 117000000) { return true;}
					
					return false;
				}

				public static boolean monitorPackageSideload(long cache_package_size_bytes ){
					long folder_size = getFolderSize(booksFolder);
					
						if (folder_size >= cache_package_size_bytes) {return true;}
					
					return false;
				}
				
				public static long getFolderSize(File directory) {
				    long length = 0;
				    for (File file : directory.listFiles()) {
				        if (file.isFile()){
					            length += file.length();
					        	System.out.println(file.getName()+" FolderSize= "+length);
				    			}
					    else{
					            length += getFolderSize(file);
					        	System.out.println(file.getName()+" FolderSize= "+length);
				              }
				    }
				    System.out.println(directory.getName()+" FolderSize= "+length);
				    return length;
				}
				
//===============Environment & Runtime Variables=====================================================//		
										private static int profileFolderSize = 0;
										private static File profileURL = null;
										private static File cacheURL = null;
										private static File booksFolder = null;
//===============Getters and Setters for environment & runtime variables===================================================//		
										public static int getProfileFolderSize() {return profileFolderSize;}

										public static void setProfileFolderSize(int profileFolderSize) {FileSystemAgent.profileFolderSize = profileFolderSize;}

										public static File getProfileURL() {return profileURL;}

										public static void setProfileURL(File profileURL) {FileSystemAgent.profileURL = profileURL;}

										public static File getCacheURL() {return cacheURL;}

										public static void setCacheURL(File cacheURL) {FileSystemAgent.cacheURL = cacheURL;}

										public static File getBooksFolder() {return booksFolder;}

										public static void setBooksFolder(File booksFolder) {FileSystemAgent.booksFolder = booksFolder;}


										
				
			}
