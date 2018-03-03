package com.theater;

import com.theater.exception.CannotHandlePartyException;
import com.theater.exception.SplitPartyException;

public interface AllocateSeats {

	AllocatedSeatDetails allotSeats(Request request) throws SplitPartyException, CannotHandlePartyException;
	
}
