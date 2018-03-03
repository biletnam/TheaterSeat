package com.theater;

import java.util.concurrent.BlockingQueue;

import com.theater.exception.CannotHandlePartyException;
import com.theater.exception.SplitPartyException;

public class TheaterService extends Thread {
	
	private BlockingQueue<Request> requests;
	private Theater theater;
	
	public TheaterService(BlockingQueue<Request> requests, Theater theater) {
		this.requests = requests;
		this.theater = theater;
	}
	
	@Override
	public void run() {
		Request request;
		while(true) {
			try {
				request = requests.take();
				try {
					AllocatedSeatDetails seatDetails = theater.allotSeats(request);
					System.out.println(seatDetails);
				} catch (SplitPartyException | CannotHandlePartyException e) {
					System.out.println(request.getClientName()+" "+e.getMessage());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
