package com.theater;

public class AllocatedSeatDetails {
	
	private String clientName;
	private Integer rowId;
	private Integer sectionId;
	
	public AllocatedSeatDetails(String clientName, Integer rowId, Integer sectionId) {
		this.clientName = clientName;
		this.rowId = rowId;
		this.sectionId = sectionId;
	}
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	@Override
	public String toString() {
		return clientName + " Row " + rowId + " Section " + sectionId;
	}
}
