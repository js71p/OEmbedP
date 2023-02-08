package com.oEmbed.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oEmbed.service.OEmbedSerivce;

@Controller
public class OEmbedController {

	@Autowired
	OEmbedSerivce oEmbedSerivce;
	
	@GetMapping("/")
	public String index() {
		return "index";  
	}
	
	@ResponseBody
	@PostMapping("/oEmbed")
	public Map<String, Object> oEmbed(@RequestParam("url") String url) throws Exception { 
		Map<String, Object> map = oEmbedSerivce.oEmbed(url);
		return map;
	}
	
}
