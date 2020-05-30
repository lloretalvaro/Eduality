package com.test.edualitytest.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Topic {


	// PRIMARY KEY
	@Id
	@NotNull
	@GeneratedValue
	private Integer idTopic;


	// OTHER
    private String name;
	

	// FOREIGN RELATIONS
	@OneToMany (mappedBy = "contentId")
	private List<Content> contentList;
	
	
	public Topic(String topicName) {
		name=topicName;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
	
}