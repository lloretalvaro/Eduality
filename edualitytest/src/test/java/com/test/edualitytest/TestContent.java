/**
 * 
 */
package testeduality;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.test.edualitytest.logic.AwardLogic;
import com.test.edualitytest.logic.ContentLogic;

/**
 * @author alvaro & manu
 *
 */
public class TestContent {
	
	private ContentLogic content;
	
	@Before
	public void setUp() throws Exception {
		content = ContentLogic.createContent(Integer.valueOf(1), "test", "bodytest", "topictest", Integer.valueOf(0), new Date(20200518), 1, false);
	}

	
	@After
	public void tearDown() throws Exception {
		content=null;
	}

	
	@Test(expected = RuntimeException.class)
	public void testCreateContentBigTitle() {
		final Integer id = 20;
		final String title ="This is a test to check that the title cannot have more than 50 letters inside of it";
		final String body = "Body";
		final String topic= "Topic";
		final Integer votes = 20;
		final Date date = new Date(20200620);
		final Integer user = 5;
		final boolean award = false;
		
		ContentLogic localcontent = ContentLogic.createContent(id, title, body, topic, votes, date, user, award);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCreateContentBigBody() {
		final Integer id = 20;
		final String title ="Title";
		final String body = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. "
				+ "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "
				+ "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. "
				+ "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, "
				+ "venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus "
				+ "elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, "
				+ "enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. "
				+ "Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. "
				+ "Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque "
				+ "sed ipsum. Nam";
		final String topic= "Topic";
		final Integer votes = 20;
		final Date date = new Date(20200620);
		final Integer user = 5;
		final boolean award = false;
		
		ContentLogic localcontent = ContentLogic.createContent(id, title, body, topic, votes, date, user, award);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCreateContentBigTopic() {
		final Integer id = 20;
		final String title ="Title";
		final String body = "Body";
		final String topic= "Lorem ipsum dolor sit.";
		final Integer votes = 20;
		final Date date = new Date(20200620);
		final Integer user = 5;
		final boolean award = false;
		
		ContentLogic localcontent = ContentLogic.createContent(id, title, body, topic, votes, date, user, award);
	}
	
	/**
	 * Test method for {@link serverlogic.Content#resetPartialVotes()}.
	 */
	@Test
	public void testResetPartialVotes() {
		content.upvote();
		
		content.resetPartialVotes();
		
		assertEquals("Reset partial votes incorrect",0,content.getPartialVotes());
	}

	/**
	 * Test method for {@link serverlogic.Content#upvote()}.
	 */
	@Test
	public void testUpvote() {
		int initialTotalVotes=content.getTotalVotes();
		int initialPartialVotes=content.getPartialVotes();
		
		content.upvote();
		
		assertEquals("Upvotes doesn't work properly for total votes",initialTotalVotes+1,content.getTotalVotes());
		assertEquals("Upvotes doesn't work properly for partial votes",initialPartialVotes+1,content.getPartialVotes());
	}

	/**
	 * Test method for {@link serverlogic.Content#downvote()}.
	 */
	@Test
	public void testDownvoteWhenTotalVotesIs0() {
		int initialTotalVotes=content.getTotalVotes();
		
		content.downvote();
		
		assertEquals("Downvotes doesn't work properly for total votes",initialTotalVotes,content.getTotalVotes());
	}

	/**
	 * Test method for {@link serverlogic.Content#addAward(serverlogic.Award)}.
	 */
	@Test
	public void testAddAward() {
		//when we add an award, the size of our list of awards should increase
		int initialNumberOfAwards=content.getListAwards().size();
		AwardLogic localAward=new AwardLogic(1);
		
		content.addAward(localAward);
		assertEquals("Number of awards not correctly updated",initialNumberOfAwards+1,content.getListAwards());
		
	}

}
