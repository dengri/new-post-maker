package postmaker.util;

import java.util.ArrayList;
import java.util.List;

public class DashExpander {

	public static void main(String[] args) {
		List<String> numbers = getNumbers("12-25");
		
		for(String number: numbers) {
			System.out.println(number);
		}
	}


	public static List<String> getNumbers(String interval) {
		List<String> numbers = new ArrayList<String>();
		String [] startEnd = interval.split("-");
		
		int start = Integer.parseInt(startEnd[0]);
		int end = Integer.parseInt(startEnd[1]);
	
		for(int i = start; i <= end; i++) {
			numbers.add(Integer.toString(i));
		}
		
		return numbers;
	}

}
