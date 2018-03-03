package com.theater;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import com.theater.exception.CannotHandlePartyException;
import com.theater.exception.SplitPartyException;

public class Theater implements AllocateSeats {
	
	private String name;
	private ArrayList<Row> rows;
	private Integer totalAvailRows;
	
	public Theater(String name, final LinkedHashSet<Row> rows) {
		this.name = name;
		this.rows = new ArrayList<>(rows);
		this.totalAvailRows = rows.size()-1;
	}

	@Override
	public AllocatedSeatDetails allotSeats(Request request) throws SplitPartyException, CannotHandlePartyException {
		AllocatedSeatDetails seatDetails = null;
		if(request != null) {
			int reqSeats = request.getNoOfSeats();
			
			if(reqSeats > getAvailableSeats()) {
				throw new CannotHandlePartyException();
			}
			
			Row row = getCompatibleRow(reqSeats);
			
			if(row == null) {
				throw new SplitPartyException();
			}
			Section section = getCompatibleSection(row, reqSeats);
			if(section == null) {
				throw new SplitPartyException();
			}
			//Section wise we need to allocate the seats.
			section.allocateSeats(reqSeats);
			
			seatDetails = new AllocatedSeatDetails(request.getClientName(), row.getRowId(),
					section.getSectionId());
		}
		
		return seatDetails;
	}
	
	/**
	 * It will ensure the seats are not free in previous allocation and get the
	 * required seats as per user's request.
	 * 
	 * @param requiredSeats
	 * @return
	 */
	private Row getCompatibleRow(int requiredSeats) {

		Row prevRow = null;
		Section prevRowSection = null;
				
		for (int i = totalAvailRows; i >= 0; i--) {
			Row row = rows.get(i);
			Section section = getCompatibleSection(row, requiredSeats);
			if(section != null) {
				if(prevRowSection == null && prevRow == null) {
					prevRow = row;
					prevRowSection = section; 
				}
				else if(prevRowSection.getNonOccupieddSeats() > section.getNonOccupieddSeats()) {
					prevRow = row;
					prevRowSection = section; 
				}
				else if(prevRowSection.getNonOccupieddSeats() == section.getNonOccupieddSeats()) {
					return prevRow;
				}
			}
		}
		
		return prevRow;
	}
	
	/**
	 * It will ensure the seats are not free in previous allocation and get the
	 * required seats as per user's request.
	 * 
	 * @param requiredSeats
	 * @return
	 */
	private Section getCompatibleSection(Row row, int requiredSeats) {

		Set<Section> sections = row.getSections();
		Section prevAvailableSection = null;
		
		for (Section section : sections) {
			if (section.isAvailable()) {
				int availableSeats = section.getNonOccupieddSeats();
				if (availableSeats > requiredSeats) {
					if(prevAvailableSection == null)
						prevAvailableSection = section;
					else if(prevAvailableSection != null && prevAvailableSection.getNonOccupieddSeats() > section.getNonOccupieddSeats())
						prevAvailableSection = section;
				} else if (availableSeats == requiredSeats)
					return section;
			}
		}

		return prevAvailableSection;
	}
	
	private Integer getAvailableSeats() {
		int availableSeats = 0;
		for (int i = totalAvailRows; i >= 0; i--) {
			Row row = rows.get(i);
			Set<Section> sections = row.getSections();
			
			for (Section section : sections) {
				if(section.isAvailable()) {
					availableSeats += section.getNonOccupieddSeats();
				}
			}
		}
		
		return availableSeats;
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<Row> getRows() {
		return rows;
	}

	@Override
	public String toString() {
		return "Theater [name=" + name + ", rows=" + rows + "]";
	}
}
