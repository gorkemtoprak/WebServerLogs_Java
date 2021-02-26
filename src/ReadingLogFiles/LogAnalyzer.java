package ReadingLogFiles;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {

	private ArrayList<LogEntry> records;

	public LogAnalyzer() {

		records = new ArrayList<LogEntry>();
	}

	public void readFile(String filename) {

		FileResource resource = new FileResource();

		for (String line : resource.lines()) {
			WebLogParser.parseEntry(line);
			records.add(WebLogParser.parseEntry(line));
		}
	}
	
	public HashMap<String, Integer> countVisitIp(){
		
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		
		for(LogEntry le : records) {
			String ip = le.getIpAddress();
			if(!counts.containsKey(ip)) {
				counts.put(ip, 1);
			}
			else {
				counts.put(ip, counts.get(ip) + 1);
			}
		}
		
		return counts;
	}

	public int countUniqueIPs() {
		
		ArrayList<String> ar = new ArrayList<String>();
		
		for(LogEntry le : records) {
			
			String ipAdress = le.getIpAddress();
			if(!ar.contains(ipAdress)) {
				ar.add(ipAdress);
			}
		}
		
		return ar.size();
	}

	public void printAll() {

		for (LogEntry le : records) {
			System.out.println(le);
		}
	}


	public static void main(String[] args) {
		
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("short-test_log");
		int unq = la.countUniqueIPs();
		System.out.println("There are: " + unq + "ips");
		/*iHashMap<String, Integer> count = la.countVisitIp();
		System.out.println(count);*/
	}

}
