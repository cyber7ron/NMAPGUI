package com.uniovi.nmapgui.model;

public class Link {

	private Host source;
	private Host target;
	
	public Link(Host source, Host target) {
		super();
		setSource(source);
		setTarget(target);
	}
	
	public Host getSource() {
		return source;
	}
	public void setSource(Host source) {
		this.source = source;
	}
	public Host getTarget() {
		return target;
	}
	public void setTarget(Host target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Link [source=" + getSource() + ", target=" + getTarget() + "]";
	}
	
	
	

}
