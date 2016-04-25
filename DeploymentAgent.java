/**
 * 
 * @author Intel Education SDI LAR 
 *
 */
package com.ieducation.study.deployment.agent;
	
	import java.io.File;
	import java.io.IOException;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.util.concurrent.*;
	import java.util.List;
	import java.util.Map;
	
	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.winium.DesktopOptions;
	import org.openqa.selenium.winium.WiniumDriver;
	import org.openqa.selenium.winium.*;
	import org.testng.annotations.*;

import com.ieducation.study.deployment.agent.protocol.Commands;
import com.ieducation.study.deployment.agent.protocol.Responses;
import com.ieducation.study.deployment.agent.protocol.Statuses;

import winium.elements.desktop.*;
	import winium.elements.desktop.extensions.*;
	import org.openqa.selenium.remote.*;

	@SuppressWarnings("unused")
			public class DeploymentAgent {
			
				/**
				 * Class Constructor
				 */
				public DeploymentAgent() {

					System.out.println("Seting up depoyer environment...");
					
					studyUser = "amado.guerena@intel.com";
					studyPwd= "lala1234";

					
					System.out.println("Intel® Education Study Deployment tool");
					System.out.println("User e-mail:     "+studyUser);
					System.out.println("User password: "+studyPwd);
					
					
					
					try {appAgent = new StudyAppAgent();} 
					catch (InterruptedException e) {e.printStackTrace();}
					
					fsAgent = new SystemAgent();
				
						System.out.println("No errors at the moment.");
					
						try {Thread.sleep(6000);} 
						catch (InterruptedException e) {e.printStackTrace();}
					
				}
			
				/**
				 * @param args
				 */
				public static void main(String[] args){
					new DeploymentAgent();
					try {Thread.sleep(5000);} 
					catch (InterruptedException e) {e.printStackTrace();}
					
					appAgent.signIn(studyUser, studyPwd);
					try {Thread.sleep(45000);} 
					catch (InterruptedException e) {e.printStackTrace();}
					
					
					long expected_size = 10400000;
					while(!fsAgent.monitorBookCoverSideload(expected_size)){
						try {Thread.sleep(60000);} 
						catch (InterruptedException e) {e.printStackTrace();}
						}
					
					appAgent.navigateTo(1);	
					try {Thread.sleep(4000);} 
					catch (InterruptedException e) {e.printStackTrace();}
					
					appAgent.downloadAllbooks();
					
					while(fsAgent.monitorBooksDownload()){
						try {Thread.sleep(4000);} 
						catch (InterruptedException e) {e.printStackTrace();}
					}
					
					try {Thread.sleep(30000);} 
					catch (InterruptedException e) {e.printStackTrace();}
					
					appAgent.destroyEnviroment();
				}
			
				

//===============Environment & Runtime Variables=====================================================//		
						private static String studyUser = null;
						private static String studyPwd = null;
						private static StudyAppAgent appAgent=null;
						private static SystemAgent fsAgent=null;
						private static CommunicationsAgent comAgent = null;
						private static Statuses actual_status= null;
						private static Commands command = null;
						private static Responses response = null;
//===============Environment _& _Runtime_Variables===================================================//		


				
				
				
			}
