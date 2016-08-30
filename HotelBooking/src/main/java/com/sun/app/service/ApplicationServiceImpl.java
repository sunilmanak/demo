package com.sun.app.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.app.bean.CustomerDetails;
import com.sun.app.bean.CustomerDetailsDTO;
import com.sun.app.bean.Employee;
import com.sun.app.bean.RoomBooking;
import com.sun.app.bean.User;
import com.sun.app.exception.ApplicationException;
import com.sun.app.repository.CustomerDetailsRepository;
import com.sun.app.repository.EmployeeRepository;
import com.sun.app.repository.RoomBookingRepository;
import com.sun.app.util.AppUtil;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	private static final Logger log = Logger.getLogger(ApplicationServiceImpl.class);

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private RoomBookingRepository roomRepo;
	
	@Autowired
	private CustomerDetailsRepository customerRepository;

	@Override
	public Employee login(User user) {
		log.info("ApplicationServiceImpl login ::: Starts" );
		Employee employee = empRepo.findByUserName(user.getUserName());
		if (AppUtil.isNull(employee) || AppUtil.isNull(employee.getEmpId())) {
			throw new ApplicationException(1001,
					"Employee Not Found with Given User Name : "
							+ user.getUserName());
		}

		if (!employee.getUserName().equalsIgnoreCase(user.getUserName())
				|| !employee.getPassword().equals(user.getPassword())) {
			throw new ApplicationException(1002, "Authentication Failed");
		}
		log.info("ApplicationServiceImpl login ::: End" );
		return employee;
	}

	@Override
	public List<RoomBooking> loadAllRooms() {
		log.info("ApplicationServiceImpl loadAllRooms ::: Starts" );
		List<RoomBooking> roomBoooks =  roomRepo.findAll();
		
		for(int i =0;i<roomBoooks.size();i++){
			RoomBooking room = roomBoooks.get(i);	
			Date checkin = room.getCheckINTime();
			Date checkout =  room.getCheckOUTTime();
			Date currentDate  = new Date();
			if(null == checkin && null == checkout){
				room.setAvilable(true);
			}
			else if(currentDate.after(checkout)){
				room.setCheckINTime(null);
				room.setCheckOUTTime(null);
				room.setAvilable(true);
			}
	    }
		log.info("ApplicationServiceImpl loadAllRooms ::: End" );
		return roomBoooks;
	}

	@Override
	public CustomerDetails saveCustomerDetails(CustomerDetailsDTO customerDetailsDTO) {
		log.info("ApplicationServiceImpl saveCustomerDetails ::: Starts" );
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAddress(customerDetailsDTO.getAddress());
		customerDetails.setFirstName(customerDetailsDTO.getFirstName());
		customerDetails.setLastName(customerDetailsDTO.getLastName());
		customerDetails.setPhotoId(customerDetailsDTO.getFileData().getBytes(StandardCharsets.UTF_8));
		customerDetails.setMobile(customerDetailsDTO.getMobile());
		customerDetails.setRoomNumber(new Integer(customerDetailsDTO.getRoomNumber()));
		customerDetails = customerRepository.save(customerDetails);
		RoomBooking room =  roomRepo.findOne(customerDetailsDTO.getRoomNumber());
		room.setCheckINTime(customerDetailsDTO.getCheckInDate());
		room.setCheckOUTTime(customerDetailsDTO.getCheckOutDate());
		room.setCustomerId(customerDetails.getCustomerId());
		roomRepo.saveAndFlush(room);
		log.info("ApplicationServiceImpl saveCustomerDetails ::: End" );
		return customerDetails;
	}

	@Override
	public CustomerDetailsDTO showCustomerDetails(int roomNumber) {
		log.info("ApplicationServiceImpl showCustomerDetails ::: Starts" );
		RoomBooking room =  roomRepo.findOne(roomNumber);
		CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
		customerDetailsDTO.setRoomNumber(room.getRoomNumber());
		customerDetailsDTO.setCheckInDate(room.getCheckINTime());
		customerDetailsDTO.setCheckOutDate(room.getCheckOUTTime());
		
		CustomerDetails customerDetails = customerRepository.findOne(room.getCustomerId());
		customerDetailsDTO.setAddress(customerDetails.getAddress());
		//customerDetailsDTO.setFileData(customerDetails.get);
		customerDetailsDTO.setFirstName(customerDetails.getFirstName());
		customerDetailsDTO.setLastName(customerDetails.getLastName());
		customerDetailsDTO.setMobile(customerDetails.getMobile());		
		
		
		log.info("ApplicationServiceImpl showCustomerDetails ::: End" );
		return customerDetailsDTO;
	}
}
