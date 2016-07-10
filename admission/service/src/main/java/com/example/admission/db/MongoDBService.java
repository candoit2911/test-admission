package com.example.admission.db;

import java.util.List;
import org.apache.log4j.Logger;
import net.vz.mongodb.jackson.JacksonDBCollection;
import com.example.admission.resource.Order;
import com.example.admission.resource.OrderItem;
import com.example.admission.resource.Student;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoException;

/**
 * Handle interaction with Mongo DB
 * @author 
 *
 */
public class MongoDBService {
	
	final static private Logger LOGGER = Logger.getLogger(MongoDBService.class);
	
	final private static String STUDENT_COLLECTION = "students";
	final private static String STUDENT_ITEM_COLLECTION = "items";
	final private static String STUDENT_ITEM_ORDER_COLLECTION = "orders";
	
	final private DB db;
	
	public MongoDBService(DB db) {
		this.db = db;
	}
	
	public Student getStudent(int rollNumber) {
		JacksonDBCollection<Student, String> coll = JacksonDBCollection.wrap(db.getCollection(STUDENT_COLLECTION), Student.class,
		        String.class);		
		try {
			return coll.findOne(new BasicDBObject("rollNumber", rollNumber));
		} catch(MongoException ex) {
			LOGGER.error("failed to get student", ex);
			return null;
		}
	}
	
	public List<OrderItem> getOrderItems() {
		JacksonDBCollection<OrderItem, String> coll = JacksonDBCollection.wrap(db.getCollection(STUDENT_ITEM_COLLECTION), OrderItem.class,
		        String.class);		
		try {
			return coll.find().toArray();
		} catch(MongoException ex) {
			LOGGER.error("failed to get items", ex);
			return null;
		}
	}
	
	public String insertOrder(Order order) {
		JacksonDBCollection<Order, String> coll = JacksonDBCollection.wrap(db.getCollection(STUDENT_ITEM_ORDER_COLLECTION), Order.class,
		        String.class);		
		try {
			net.vz.mongodb.jackson.WriteResult<Order, String> result = coll.insert(order);
			return result.getSavedId();
		} catch(MongoException ex) {
			LOGGER.error("failed to insert order", ex);
			return null;
		}
	}

}
