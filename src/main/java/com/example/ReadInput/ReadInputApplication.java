package com.example.ReadInput;

//import jdk.jfr.internal.tool.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;

@SpringBootApplication
public class ReadInputApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadInputApplication.class, args);
		ReadInputApplication read = new ReadInputApplication();

		Scanner scan = new Scanner(System.in);
		String input = "";

		while ( !input.equals("start") ) {
			System.out.println("Type to 'start' to execute:");
			input = scan.nextLine();
		}
		while ( input.equals("start") ) {
			read.readFile("ConfigTest");
			input = scan.nextLine();
		}
	}

	public void readFile(String path){
		try (InputStream is = ReadInputApplication.class.getClassLoader().getResourceAsStream(path);
			 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {


			String line;
			Map<String, StringBuilder> entriesByHeader = new HashMap<>();

			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (parts.length == 5) {
					String header = parts[0];
					String value = parts[1];
					String number = parts[2].trim();
					String status = parts[4];
					StringBuilder entry = new StringBuilder("- Envy").append(value).append(" Port ").append(number).append(" terpantau ").append(status);
					entriesByHeader.computeIfAbsent(header, k -> new StringBuilder()).append(entry).append("\n");
				}
			}

			// Print the output
			for (Map.Entry<String, StringBuilder> entry : entriesByHeader.entrySet()) {
				System.out.println("Hello " + entry.getKey());
				System.out.println(entry.getValue());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
