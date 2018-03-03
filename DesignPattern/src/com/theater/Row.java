package com.theater;

import java.util.LinkedHashSet;
import java.util.Set;

final public class Row implements Cloneable, CheckAvailability {

	private int rowId;
	private Set<Section> sections;
	
	public Row(int rowId, final LinkedHashSet<Section> sections) {
		this.rowId = rowId;
		this.sections = sections;
	}
	
	public int getRowId() {
		return rowId;
	}

	public Set<Section> getSections() {
		return new LinkedHashSet<Section>(sections);
	}

	public boolean isAvailable() {
		boolean available = true;
		
		for (Section section : sections) {
			if(section.isAvailable() == true) {
				available = false;
				break;
			}
		}
		
		return available;
	}
	
	public void allocateSection(int requiredSeats) {
		
		for (Section section : sections) {
			if(section.isAvailable() && requiredSeats != 0)
				requiredSeats = section.allocateSeats(requiredSeats);
			else
				break;
		}
		
	}
	
	public void allocateSection() {
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rowId;
		result = prime * result + ((sections == null) ? 0 : sections.hashCode());
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
		Row other = (Row) obj;
		if (rowId != other.rowId)
			return false;
		if (sections == null) {
			if (other.sections != null)
				return false;
		} else if (!sections.equals(other.sections))
			return false;
		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "Row [rowId=" + rowId + ", sections=" + sections + "]";
	}
	
}
