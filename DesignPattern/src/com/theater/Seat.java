package com.theater;

final public class Seat implements Cloneable, CheckAvailability {
	
	private int seatNo;
	private boolean booked;
	
	public Seat(int seatNo) {
		this.seatNo = seatNo;
		this.booked = false;
	}
	
	public int getSeatNo() {
		return seatNo;
	}
	
	public boolean isBooked() {
		return booked;
	}
	public void setBooked(boolean booked) {
		this.booked = booked;
	}
	
	@Override
	public boolean isAvailable() {
		return isBooked();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + seatNo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (seatNo != other.seatNo)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Seat [seatNo=" + seatNo + ", booked=" + booked + "]";
	}
}
