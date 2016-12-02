package de.lhug.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class CharacterDao {

	@Autowired
	private MongoOperations mongoOperations;
}
