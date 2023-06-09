package com.github.saulmmbp.app.services;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.github.saulmmbp.app.dto.*;

@Service
public class ScrappyService {

	public List<Tag> getTag(Request req) {
		List<Tag> tags = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(req.url()).get();
			Elements elements = doc.getElementsByTag(req.tag());
			elements.forEach(e -> tags.add(new Tag(e)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tags;
	}
	
}
