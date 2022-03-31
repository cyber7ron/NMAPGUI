package com.uniovi.nmapgui.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Script {
	private List<String> categories;
	private String description;
	private String filename;
	private String name;
	
	public String getName() {
		if (name!=null)
			return name;
		String[] split = filename.split("/");
		return name=split[split.length-1].replace(".nse", "");
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElementWrapper(name="categories", required=false)
	@XmlElement(name="category", required=false)
	public List<String> getCategories() {
		return categories;
	}
	
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	@XmlElement(name="description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlAttribute(name="filename")
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	

}
