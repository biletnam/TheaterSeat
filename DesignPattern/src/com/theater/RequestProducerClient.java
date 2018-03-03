package com.theater;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class RequestProducerClient extends Thread {

	private BlockingQueue<Request> requests;
	private Scanner scanner;
	
	public RequestProducerClient(BlockingQueue<Request> requests, Scanner scanner) {
		this.requests = requests;
		this.scanner = scanner;
	}
	
	@Override
	public void run() {
		String ticketReq = null;
		while(scanner.hasNextLine()) {
			ticketReq = scanner.nextLine();
			
			if(ticketReq.isEmpty())
				break;
			
			String ticketDetails[] = ticketReq.split(" ");
			String clientName = ticketDetails[0];
			Integer noOfSeats = Integer.parseInt(ticketDetails[1]);
			
			Request request = new Request(clientName, noOfSeats);
			try {
				requests.put(request);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		scanner.close();
	}
	
}
