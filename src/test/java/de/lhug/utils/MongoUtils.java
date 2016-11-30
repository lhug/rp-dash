package de.lhug.utils;

import java.util.Set;

import org.springframework.data.mongodb.core.MongoOperations;

public class MongoUtils {

	public static void clean(MongoOperations mongoOperations) {
		Set<String> collectionNames = mongoOperations.getCollectionNames();
		collectionNames.remove("system.indexes");
		for (String collectionName : collectionNames) {
			mongoOperations.dropCollection(collectionName);
		}
	}
}
