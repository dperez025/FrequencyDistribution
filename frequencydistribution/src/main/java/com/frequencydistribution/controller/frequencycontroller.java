package com.frequencydistribution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frequencydistribution.model.DistributionTable;
import com.frequencydistribution.services.IFrequency;

@Controller
public class frequencycontroller {

	@Autowired
	IFrequency frequency;
	
	@GetMapping("/")
	public String Index(Model model) {
		return "Index";
	}
	
	@PostMapping("/")
	public String Calc(@RequestParam(name = "data", defaultValue = "", required = true) String data, Model model) {
		String[] ArrayStr = data.split(",");
		
		//Functions
		int range = frequency.GetRange(data);
		int intervals = frequency.GetIntervals(ArrayStr.length);
		double amplitud = frequency.GetAmplitude(range, intervals);
		List<DistributionTable> result = frequency.FrequencyDistribution(ArrayStr, amplitud, intervals);
		
		//Data Bind
		model.addAttribute("data",data);
		model.addAttribute("range", range);
		model.addAttribute("interval", intervals);
		model.addAttribute("amplitude", amplitud);
		model.addAttribute("result",result);
		
		return "Index";
	}
}
