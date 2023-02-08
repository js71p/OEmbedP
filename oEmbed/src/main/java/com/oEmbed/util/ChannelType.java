package com.oEmbed.util;

import static java.util.Arrays.stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

public enum ChannelType {

    YOUTUBE("youtube"),
    INSTAGRAM("instagram"),
    TWITTER("twitter"),
    VIMEO("vimeo");
	final String channelType;
	
    ChannelType(String channelType) {
		this.channelType = channelType;
	}
    public String getChannelType() {
        return channelType;
    }

    public static String  getChannelTypeName(String url) throws NoSuchElementException, IOException{
    	 Optional<String> channelTypeName = stream(ChannelType.values()).filter(e -> url.toLowerCase().contains(e.getChannelType())).findFirst().map(Object::toString);
    	 channelTypeName.orElseThrow(()-> new NoSuchElementException());
    	 JSONParser jsonParser = new JSONParser();
    	 ClassPathResource classPathResource = new ClassPathResource("json/providers.json");
     	 BufferedReader rd = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));
     	 List<String> urlList = new ArrayList<String>();
     	 
     	 String uri="";
     	
     	try {
     		
			JSONArray jsonArray = (JSONArray) jsonParser.parse(rd);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					String endpoints =   (String) jsonObject.get("endpoints").toString();
					JSONArray endpointsArray = (JSONArray) jsonParser.parse(endpoints);
					JSONObject urlObj = (JSONObject) endpointsArray.get(0);
					
					
					urlList.add((String) urlObj.get("url"));
				}
				for (String str : urlList) {
					if(str.contains(channelTypeName.get().toLowerCase())) {
						if (str.contains("oembed.")) {
							if (str.contains("{format}")) {
		                        str = str.replace("{format}", "json");
		                    }
						}
						uri = str;
					}
				}
				
     	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;
    }
   
}
