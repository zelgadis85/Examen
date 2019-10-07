package main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class JobLoggerOptimizado {
	
	private static String logToFilePath;
	private static String usuDbo;
	private static String passDbo;
	private static String portNumber;
	private static String dbms;
	private static String serverName;
	private static Logger logger;
	private static Properties properties = new Properties();
			
	public JobLoggerOptimizado() {
		
		try {
			InputStream propertiesStream = ClassLoader.getSystemResourceAsStream("properties/config.properties");
			properties.load(propertiesStream);
			propertiesStream.close();
			
			logger = Logger.getLogger("MyLog");
			logToFilePath = properties.getProperty("log.path");
			
			usuDbo = properties.getProperty("dbo.user");
			passDbo = properties.getProperty("dbo.pass");			
			portNumber = properties.getProperty("dbo.portNumber");
			dbms = properties.getProperty("dbo.dbms");
			serverName = properties.getProperty("dbo.serverName");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void LogMessageConsole(String messageText, int level) throws Exception {
		ConsoleHandler ch = new ConsoleHandler();
		logger.addHandler(ch);		
		Message(messageText, level);
	}
	
	public static void LogMessageFile(String messageText, int level) throws Exception {
			 
		 Handler fileHandler = new FileHandler(logToFilePath + "/logFile.txt", false);
		 SimpleFormatter simpleFormatter = new SimpleFormatter();
		 logger.addHandler(fileHandler);		
		 fileHandler.setFormatter(simpleFormatter);		 
		 Message(messageText, level);
		 
	}

	public static void LogMessageDatabase(String messageText, int level) throws Exception {
		
		Connection connection = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", usuDbo);
		connectionProps.put("password", passDbo);

		connection = DriverManager.getConnection("jdbc:" + dbms + "://" + serverName
				+ ":" + portNumber + "/", connectionProps);
		
		Statement stmt = connection.createStatement();
		
		stmt.executeUpdate("insert into Log_Values value('" + messageText + "', " + String.valueOf(level) + ")");
		
	}
	
	private static void Message(String messageText, int level){
		switch (level) {
		case 1:
			logger.log(Level.INFO, "message: " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + messageText);
			break;
		case 2:
			logger.log(Level.WARNING, "warning: " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + messageText);
			break;
		case 3:
			logger.log(Level.SEVERE, "error: " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + messageText);			
			break;
		default:
			break;
		}
				
    }
	
}