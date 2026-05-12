package TestngDemo;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int min= 1, max= 3;

	@Override
	public boolean retry(ITestResult result) {
		if(min<max)
		{
			min++;
			return true;
		}
		return false;
	}

}
