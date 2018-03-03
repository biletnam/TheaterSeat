package com.theater;

import java.util.LinkedHashSet;
import java.util.Set;

final public class Section implements Cloneable, CheckAvailability {

	private int sectionId;
	private Set<Seat> seats;

	/**
	 * Seats should not be null, otherwise throw exception
	 * @param sectionId
	 * @param seats
	 */
	public Section(int sectionId, final LinkedHashSet<Seat> seats) {
		this.sectionId = sectionId;
		this.seats = seats;
	}
	
	public int getSectionId() {
		return sectionId;
	}

	public Set<Seat> getSeats() {
		return new LinkedHashSet<>(seats);
	}

	public boolean isAvailable() {
		boolean available = false;
		
		for (Seat seat : seats) {
			if(seat.isBooked() == false) {
				available = true;
				break;
			}
		}
		
		return available;
	}
	
	public Integer getOccupieddSeats() {
		Integer filledSeats = 0;
		for (Seat seat : seats) {
			if(seat.isBooked() == true)
				++filledSeats;
			else
				break;
		}
		
		return filledSeats;
	}
	
	public Integer allocateSeats(int requiredSeats) {

		for (Seat seat : seats) {
			if (seat.isBooked() == false) {
				if (requiredSeats != 0) {
					seat.setBooked(true);
					requiredSeats--;
				} else
					break;
			}
		}

		return requiredSeats;
	}

	public Integer getNonOccupieddSeats() {
		
		Integer filledSeats = seats.size() - getOccupieddSeats();
		
		return filledSeats;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sectionId;
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
		Section other = (Section) obj;
		if (sectionId != other.sectionId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Section [sectionId=" + sectionId + ", seats=" + seats + "]";
	}
	
}
