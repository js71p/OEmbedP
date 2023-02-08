package com.oEmbed.service;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface OEmbedSerivce {
	public Map<String, Object> oEmbed(String url) throws IOException ;
}
