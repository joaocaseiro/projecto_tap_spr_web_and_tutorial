package com.grupoatwork.celebrity.entities;

public enum Occupation {
	ACTOR(1), 
	WRITER(2), 
	ATHLETE(3);
	
	private int order;
	
	Occupation(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
