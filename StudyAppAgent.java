package com.ieducation.study.deployment.agent;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import io.selendroid.server.common.exceptions.NoSuchElementException;

@SuppressWarnings("unused")
public class StudyAppAgent {
		
		public StudyAppAgent() throws InterruptedException {
				dskOptions.setApplicationPath(appURL);
				dskOptions.setLaunchDelay(3000);
				Thread.sleep(15000);
				
					try {
							ieStudy = new WiniumDriver(new URL(winiumSvrURL), dskOptions);
							if(ieStudy != null){ System.out.println("Winium driver created");}
						 } 
					catch (Exception e) {System.err.println(e.getStackTrace());}
			}
		
			
		public static boolean signIn(String usr, String pwd){
				System.out.println("Starting Sign In ");
						//Find SignInButton control
				try{	WebElement signInButton = ieStudy.findElementById("SignInButton");
					if(signInButton != null){		System.out.println("SignInButton acquired...");
												//Click the SignInButton
													signInButton.click();
													System.out.println("SignInButton clicked");
												//Wait 2secs for the window to change
													Thread.sleep(2000);
												//Get TextBoxEmail
													WebElement TextBoxEmail = ieStudy.findElementById("TextBoxEmail");
												//Input user email
													TextBoxEmail.sendKeys(usr);
												//Get the TextBoxPassword
													WebElement pwdTextBox = ieStudy.findElementById("TextBoxPassword");
												//Input password
													pwdTextBox.sendKeys(pwd);
												//Get the "ButtonConfirm" element
													WebElement ButtonConfirm = ieStudy.findElementById("ButtonConfirm");
												//Click it!
													ButtonConfirm.click();													
												return true;
											}
					else{System.out.println("Ooops! Something wetn wrong, we couldn't get hold of the SignInButton X(");}
					}
				catch(NoSuchElementException e){e.printStackTrace();}
				catch(InterruptedException e){e.printStackTrace();}
				
						
				return false;
			}
			
		public static boolean navigateTo(int target){
							//Receive a integer value pass it through a switch to access the desired section 
							//0="My Collections"; 1="My library"; 2="MyStats"; 3="Store"
								System.out.println("Init navigateTo");
							    WebElement ContentManagerCollectionSection = ieStudy.findElementById("ContentManagerCollectionSectionControl");
							    List<WebElement> CollectionTiles = ContentManagerCollectionSection.findElements(By.className("CollectionTile"));
								WebElement tile = null;
											tile = CollectionTiles.get(target);
							if(tile != null){	System.out.println("About to click the navTile");
													tile.click();
													try {Thread.sleep(30000);} catch (InterruptedException e) {e.printStackTrace();}
													return true;
												}
							return false;
						}
	
		public static void downloadAllbooks(){
			WebElement ScrollViewer = ieStudy.findElement(By.className("ScrollViewer"));
			List<WebElement> books = ScrollViewer.findElements(By.className("BookModelJacket"));
			
				for (WebElement book : books) {
							book.click();
							
								try {Thread.sleep(1000);} 
								catch (InterruptedException e) {e.printStackTrace();}
						}
		}		
		
		public static void findBook(String book_title){
			WebElement seach_box = ieStudy.findElement(By.className("CustomTextBox"));
				seach_box.sendKeys(book_title);
		}

		public static void downloadNbooks(int amount_of_books){
			WebElement ScrollViewer = ieStudy.findElement(By.className("ScrollViewer"));
			List<WebElement> books = ScrollViewer.findElements(By.className("BookModelJacket"));
			
				for (int i = 0; i < amount_of_books; i++) {
						WebElement book = books.get(i);
						book.click();
						
							try{Thread.sleep(1000);}
							catch(InterruptedException e){e.printStackTrace();}
				}
			
		}
		
//===============Environment & Runtime Variables=====================================================//		
			private static WiniumDriver ieStudy = null;
			private static String appURL = new String("C:\\Program Files (x86)\\Intel Education\\Study App\\Kno.TextBooks.exe");
			private static String winiumSvrURL = new String("http://localhost:9999");
			private static DesktopOptions dskOptions = new DesktopOptions();
			private static String appName = new String("Intel® Education Study");
			private static WebElement appWindow = null;
			private static String[] credentials = new String[2];
//===============Environment & Runtime Variables===================================================//		
			
			
			
			
		}
