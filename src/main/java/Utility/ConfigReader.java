package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	Properties pro;

	public ConfigReader()
	{
		try {
			File src=new File("./Configuration/Config.property");
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}
		catch (Exception e) {
			System.out.println("Exception is ===" +e.getMessage());

		} 
	}

	public String getApplicationUrl()
	{
		return pro.getProperty("baseURL");

	}

	public String getSGAUserName()
	{
		return pro.getProperty("SGAUserName");

	}

	public String getSGAUserPassword()
	{
		return pro.getProperty("SGAUserPassword");

	}

	public String getuserJoiningDate()
	{
		return pro.getProperty("UserJoiningDate");

	}

	public String getISONPassword()
	{
		return pro.getProperty("IsonPassword");

	}

	public String getRAdminUserName()
	{
		return pro.getProperty("RAdminUserName");

	}

	public String getSDHeadUserName()
	{
		return pro.getProperty("SDHeadUserName");

	}

	public String getTraineeUserName()
	{
		return pro.getProperty("TraineeUserName");

	}


	public String getTrainerUserName()
	{
		return pro.getProperty("TrainerUserName");

	}



	public String getClientUserName()
	{
		return pro.getProperty("ClientUserName");

	}

	public String getGuestUserName()
	{
		return pro.getProperty("GuestUserName");

	}

	public String getSiteHeadUserName()
	{
		return pro.getProperty("SiteHeadUserName");

	}

	public String getEnvironmentName() {

		return pro.getProperty("env");
	}

	public String getTrainingStartDate() {

		return pro.getProperty("TrainingStartDate");
	}

	public String getTrainingEndDate() {

		return pro.getProperty("TrainingEndDate");
	}

	public String getmanagepoll() {

		return pro.getProperty("managepoll");
	}












}
