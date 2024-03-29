package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		//D:\eclipse-workspace\working pipe and functions\pipeline.txt;
		
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter with a file path:");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			List<Product> list = new ArrayList<>();
			String line = br.readLine();
			
			while(line!=null) {
				System.out.println(line);
				String filds[] = line.split(",");
				list.add(new Product(filds[0], Double.parseDouble(filds[1])));
				line = br.readLine();
			}
			
			double avg = list.stream().map(p -> p.getPrice()).reduce(0.0, (x, y)-> x + y) / list.size();
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> names = list.stream().filter(p -> p.getPrice() < avg).map(p -> p.getName()).sorted(comp.reversed())
					.collect(Collectors.toList());
			
			System.out.println();
			System.out.println("Average price: " + String.format("%.2f", avg) + "\n");
			
			names.forEach(System.out::println);
		 
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		
		

	}

}
