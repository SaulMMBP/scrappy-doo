package com.github.saulmmbp.app.dto;

import java.util.*;
import java.util.stream.Collectors;

import org.jsoup.nodes.*;

public record Tag(String name, String id, Set<String> classNames, List<Attribute> attributes) {
	
	public Tag(Element e) {
		this(e.tagName(), 
				e.id(), 
				e.classNames(),
				e.attributes().asList().stream().filter(a -> {
						return !(a.getKey().equals("class") ||
								a.getKey().equals("id"));
					}).collect(Collectors.toList())
				);
	}
	
}
