package com.theater;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BookingSystem {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String req = null;
		int rowId = 1;
		final LinkedHashSet<Row> rows = new LinkedHashSet<>();
		
		while(scanner.hasNextLine()) {
			req = scanner.nextLine();
			
			if(req.isEmpty())
				break;
			
			String seatsWithSection[] = req.split(" ");
			final LinkedHashSet<Section> sections = new LinkedHashSet<>();
			for (int section = 0; section < seatsWithSection.length; section++) {
				final LinkedHashSet<Seat> seats = new LinkedHashSet<>();
				for(int seat = 0; seat < Integer.parseInt(seatsWithSection[section]); seat++) {
					Seat st = new Seat(seat+1);
					seats.add(st);
				}
				Section sc = new Section(section+1, seats);
				sections.add(sc);
			}
			Row row = new Row(rowId++, sections);
			rows.add(row);
		}
		
		Theater theater = new Theater("xyz", rows);
		BlockingQueue<Request> requests = new LinkedBlockingQueue<>();
		
		RequestProducerClient producerClient = new RequestProducerClient(requests, scanner);
		TheaterService theaterService = new TheaterService(requests, theater);
		
		producerClient.start();
		theaterService.start();
	}
}
