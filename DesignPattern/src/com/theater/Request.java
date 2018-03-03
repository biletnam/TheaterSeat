package com.theater;

final public class Request {

	private String clientName;
	private Integer noOfSeats;
	
	public Request(String clientName, Integer noOfSeats) {
		this.clientName = clientName;
		this.noOfSeats = noOfSeats;
	}

	public String getClientName() {
		return clientName;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	@Override
	public String toString() {
		return "Request [clientName=" + clientName + ", noOfSeats=" + noOfSeats + "]";
	}
	
}
