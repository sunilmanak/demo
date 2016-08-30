package com.sun.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.app.bean.ActionResult;
import com.sun.app.bean.CustomerDetails;
import com.sun.app.bean.CustomerDetailsDTO;
import com.sun.app.bean.Employee;
import com.sun.app.bean.RoomBooking;
import com.sun.app.bean.User;
import com.sun.app.service.ApplicationService;

@RestController
public class ApplicationController {
	
    private static final Logger log = Logger.getLogger(ApplicationController.class);
	private static final String IS_LOGIN = "IS_LOGIN";

	@Autowired
	private ApplicationService appService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ActionResult login(@RequestBody User user, HttpSession session) {
		log.info("ApplicationController login ::: Starts" );
		ActionResult result = new ActionResult();
		try {
			Employee employee = appService.login(user);
			result.setStatus(true);
			result.setData(employee);
			session.setAttribute(IS_LOGIN, true);
		} catch (Exception e) {
			log.error("Exception In ApplicationController login() ",e );
			result.addError(e.getMessage());
		}
		log.info("ApplicationController login ::: End" );
		return result;
	}

	@RequestMapping(value = "isLogin", method = RequestMethod.GET)
	public ActionResult isLogin(HttpSession session) {
		log.info("ApplicationController isLogin() ::: Starts" );
		ActionResult result = new ActionResult();
		try {
			Boolean isLogin = (Boolean) session.getAttribute(IS_LOGIN);
			result.setStatus(isLogin);
		} catch (Exception e) {
			log.error("Exception In ApplicationController isLogin() ",e );
			result.addError(e.getMessage());
		}
		log.info("ApplicationController isLogin() ::: End" );
		return result;
	}

	@RequestMapping(value = "loadAllRooms", method = RequestMethod.GET)
	public ActionResult loadAllRooms() {
		log.info("ApplicationController loadAllRooms() ::: Starts" );
		ActionResult result = new ActionResult();
		try {
			List<RoomBooking> rooms = appService.loadAllRooms();
			result.setStatus(true);
			result.setData(rooms);
		} catch (Exception e) {
			log.error("Exception In ApplicationController loadAllRooms() ",e );
			result.addError(e.getMessage());
		}
		log.info("ApplicationController loadAllRooms() ::: End" );
		return result;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ActionResult logout(HttpSession session) {
		log.info("ApplicationController logout() ::: Starts" );
		ActionResult result = new ActionResult();
		try {
			session.removeAttribute(IS_LOGIN);
		} catch (Exception e) {
			log.error("Exception In ApplicationController logout() ",e );
			result.addError(e.getMessage());
		}
		log.info("ApplicationController logout() ::: End" );
		return result;
	}

	@RequestMapping(value = "showCustomerDetails", method = RequestMethod.POST)
	public ActionResult showCustomerDetails(@RequestBody int roomNumber){
		log.info("ApplicationController saveCustomerDetails() ::: Starts" );
		ActionResult result = new ActionResult();
		try {
			CustomerDetailsDTO customerDetails = appService.showCustomerDetails(roomNumber);
			result.setData(customerDetails);
			result.setStatus(true);
		} catch (Exception e) {
			log.error("Exception In ApplicationController saveCustomerDetails() ",e );
			result.addError(e.getMessage());
		}
		log.info("ApplicationController saveCustomerDetails() ::: End" );
		return result;
	}
	
	@RequestMapping(value = "saveCustomerDetails", method = RequestMethod.POST)
	public ActionResult saveCustomerDetails(@RequestBody CustomerDetailsDTO customerDetailsDTO){
		log.info("ApplicationController saveCustomerDetails() ::: Starts" );
		ActionResult result = new ActionResult();
		try {
			CustomerDetails customerDetails = appService.saveCustomerDetails(customerDetailsDTO);
			result.setData(customerDetails);
			result.setStatus(true);
		} catch (Exception e) {
			log.error("Exception In ApplicationController saveCustomerDetails() ",e );
			result.addError(e.getMessage());
		}
		log.info("ApplicationController saveCustomerDetails() ::: End" );
		return result;
	}
	
}
