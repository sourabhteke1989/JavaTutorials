package com.example.sample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainClient {
	public static void main(String[] args) {
		
		//Step 1 - Configure Hibernate using Hibernate.cfg.xml
		Configuration config = new Configuration();
		config.configure("Hibernate.cfg.xml");
		
		//Step 2 - Building session factory object.
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		//Step 3 - Opening session
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			//Step 4 - Begin Transaction
			tx = session.beginTransaction();
			
			//Student student = new Student();
			Student student = new Student("Sourabh", "sourabhteke@gmail.com", 78);
			//student.setId(5);
			
			//Persist Object into Database
			/*
			 * Using session.persist() method
			 * 		- If object does not exists in DB then persist.
			 * 		- If object exists in DB with same ID, throw exception.
			 */
			session.persist(student);
			
			/*
			 * Using session.save() method
			 * 		- Always insert new entry with new ID and return ID.
			 */
			int id = (Integer) session.save(student);
			
			/*
			 * Using session.saveOrUpdate() method
			 * 		- If object does not exists in DB, then insert new entry.
			 * 		- If object exists in DB with same ID, then Always fire update query. (Even if no value changed from object)
			 */
			session.saveOrUpdate(student);
			
			//Update Object from DB
			/*
			 * Using session.update() method
			 *		- If object does not exists in DB, throw exception "SlateStateException"
			 *		- If object exists in DDB with same ID, then Always fire update query. (Even if no value changed from object)
			 */
			session.update(student);
			
			/*
			 * Using session.merger() method
			 * 		- If object does not exists in DB, then insert new entry
			 * 		- If object exists in DB with same ID, then fire update query only if some values changed. 
			 */
			session.merge(student);
			
			//Get Object from DB
			/*
			 * Using session.get() method
			 * 		- If object exists in DB, then return persistent state object. This method never returns an uninitialized instance.
			 * 		- If object does not exists, then return null.
			 */
			Student stud = (Student) session.get(Student.class, 3);
			
			/*
			 * Using session.load() method
			 * 		- If object exists in DB, then return a proxied instance that is initialized on-demand, when a non-identifier method is accessed.
			 * 		- If object does not exists, then throw exception "ObjectNotFoundException".
			 */
			Student stud1 = (Student) session.load(Student.class, 4);
			
			//Delete Object from DB
			session.delete(student);
			
			Student student1 = (Student) session.get(Student.class, 8);
			
			tx.commit();
		}catch(Exception ex){
			tx.rollback();
			ex.printStackTrace();
			System.out.println("Error while persiting object in to database");
		}finally {
			session.close();
		}
	}
}
