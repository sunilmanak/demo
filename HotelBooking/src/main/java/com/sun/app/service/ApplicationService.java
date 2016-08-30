package com.sun.app.service;

import java.util.List;

import com.sun.app.bean.CustomerDetails;
import com.sun.app.bean.CustomerDetailsDTO;
import com.sun.app.bean.Employee;
import com.sun.app.bean.RoomBooking;
import com.sun.app.bean.User;

public interface ApplicationService {
 
	public Employee login(User user);

	public List<RoomBooking> loadAllRooms();

	public CustomerDetails saveCustomerDetails(CustomerDetailsDTO customerDetailsDTO);

	public CustomerDetailsDTO showCustomerDetails(int roomNumber);
	
}
