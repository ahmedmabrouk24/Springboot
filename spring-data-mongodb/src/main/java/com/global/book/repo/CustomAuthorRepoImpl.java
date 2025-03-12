package com.global.book.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.global.book.docs.Author;
import com.mongodb.client.result.UpdateResult;

@Component
public class CustomAuthorRepoImpl implements CustomAuthorRepo {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public Author updateByEmail(String email, String name, String phone) {
		Query query = new Query(Criteria.where("email").is(email));
		Update update = new Update();
		update.set("name", name);
		update.set("phone", phone);
		UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Author.class);
		if(updateResult == null)
            System.out.println("No documents updated");
        else
            System.out.println(updateResult.getModifiedCount() + " document(s) updated..");
		return null;
		
	}

}
