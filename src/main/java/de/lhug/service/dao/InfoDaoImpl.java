package de.lhug.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import de.lhug.entities.Entry;
import de.lhug.entities.Entry.EntryType;

@Service
public class InfoDaoImpl implements InfoDao {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void storeEntry(Entry entry) {
		mongoOperations.save(entry);
	}

	@Override
	public List<Entry> getEntries(EntryType... types) {
		Criteria criterion = new Criteria();
		int length = types.length;
		Criteria[] criteria = new Criteria[length];
		for (int i = 0; i < length; i++) {
			criteria[i] = Criteria.where("type").is(types[i]);
		}
		criterion.orOperator(criteria);
		return mongoOperations.find(Query.query(criterion), Entry.class);
	}

	@Override
	public void deleteEntry(String topic) {
		Criteria criteria = Criteria.where("topic").is(topic);
		mongoOperations.remove(Query.query(criteria), Entry.class);
	}
}
