package test;

import org.junit.Test;

import main.JobLoggerOptimizado;

public class Test1 {

	JobLoggerOptimizado job = new JobLoggerOptimizado();
	/*
	@Test
	public void testConsola() {
				
		try {
			job.LogMessageConsole("Error Console Info 1", 1);
			job.LogMessageConsole("Error Console Info 2", 2);
			job.LogMessageConsole("Error Console Info 3", 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	@Test
	public void testFile() {
				
		try {
			job.LogMessageFile("Error File Info 1", 1);
			job.LogMessageFile("Error File Info 2", 2);
			job.LogMessageFile("Error File Info 3", 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
	@Test
	public void testDbo() {
				
		try {
			job.LogMessageDatabase("Error Database Info 1", 1);
			job.LogMessageDatabase("Error Database Info 2", 2);
			job.LogMessageDatabase("Error Database Info 3", 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	*/
}
