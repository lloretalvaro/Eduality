package com.test.edualitytest.logic;
//import serverRESTinterface.MySQLAccess;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;

import com.test.edualitytest.models.Content;
import com.test.edualitytest.services.ContentService;

public class TimeReputationUpdater extends Thread {
	IteratorBase allContent; 
	EdualityLogic eduality;
	
	@Autowired
	ContentService cs;
	
	public TimeReputationUpdater() {
		eduality = new EdualityLogic();
	}
	
	public void run() {
		//I create the objects for every content in the database
    	AggregateBase myAggregateContent = new ContentAggregate();
    	
    	//Then I have to create the iterator
    	allContent = myAggregateContent.createIterator();
    
    	 
		Timer timer = new Timer ();
		
		TimerTask reputationUpdate= new TimerTask(){
		    @Override
		    public void run () {
		        
		        //WE NEED TO CREATE ALL THE ContentLogic OBJECTS AND GET THE PARAMETERS FROM THE DATABASE
		    	
		    	List<Content> contents = cs.getAll();
		    	ContentLogic cl;
		    	
		    	for(int i = 0; i <= contents.size(); i++) {
		    		
		    		  cl = new ContentLogic(contents.get(i).getContentId(), contents.get(i).getTitle(), contents.get(i).getBody(), contents.get(i).getTopic().getName(),contents.get(i).getUpvotes(),contents.get(i).getUploadDate(),contents.get(i).getUser().getIdUser(),contents.get(i).isHasAward());
		    		  eduality.fairAlgorithm(cl);
		    		  
		    		
		    	}
		    	
		    	
		    	//---------------------------------------------------------------------
		    	
		    	//Applying the fairAlgorithm to every content we just fetched from the database
		    	
		    	//first condition works when the list is of only one item!!!
				 while(myAggregateContent.count()==1 || allContent.hasNextItem()){
				 	//for every content, we have to apply the fairAlgorithm(content);
	 				 
				 	eduality.fairAlgorithm((ContentLogic) allContent.currentItem());
				 	allContent.nextItem();
				 }
				 
				
		    	
		    }
		};
		
		
		timer.schedule (reputationUpdate, 60, 1000*60);
	}
	
}


