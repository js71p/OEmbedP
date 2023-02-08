package com.oEmbed.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oEmbed.service.OEmbedSerivce;
import com.oEmbed.util.ChannelType;

@Service
public class OEmbedServiceImpl implements OEmbedSerivce{

	@Override
	public Map<String, Object> oEmbed(String url) throws IOException {
		StringBuffer result = new StringBuffer(); 
		String strResult = ""; 
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		try {  
			String uri = ChannelType.getChannelTypeName(url);
			StringBuilder urlBuilder = new StringBuilder(uri); 
			urlBuilder.append("?url=" + java.net.URLEncoder.encode(url, "UTF-8")); 
			URL lasturl = new URL(urlBuilder.toString()); 
			HttpURLConnection conn = (HttpURLConnection)lasturl.openConnection(); 

			conn.setRequestMethod("GET"); 
			conn.setRequestProperty("Content-Type", "application/json"); 
			
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 & conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); 
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream())); 
			}
			
			String line; 
			while((line = rd.readLine()) != null) {
				result.append(line); 
			 } 
			rd.close(); 
			conn.disconnect(); 
			strResult = result.toString(); 
			map = mapper.readValue(strResult, Map.class);
			return map;
		} catch ( Exception e ){
			e.printStackTrace(); 
			return (Map<String, Object>) new NoSuchElementException();
		} 
	}

}
