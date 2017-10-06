package com.smartbics.controller.rest;



import com.smartbics.model.Booking;
import com.smartbics.model.MeetigResp;
import com.smartbics.model.MeetingReq;
import com.smartbics.model.intermediate.BookingIntermediate;
import com.smartbics.service.IntermediateService;
import com.smartbics.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MeetingController {

	@Autowired
	IntermediateService intermediateService;

	@Autowired
	ResultService resultService;

	@PostMapping(value = "/reserve")
	@ResponseBody
	public MeetingReq method(@RequestBody BookingIntermediate data) {
		Booking booking = intermediateService.createBoking(data);

		System.out.println(booking);

		List<MeetigResp> meetigRespList = resultService.getMeetingResp(booking);


		return null;
	}




}
