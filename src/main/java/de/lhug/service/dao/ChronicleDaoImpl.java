package de.lhug.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import de.lhug.entities.Chronicle;
import de.lhug.entities.Entry;

@Service
public class ChronicleDaoImpl implements ChronicleDao {

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private InfoDao infoDao;

	@Override
	public Chronicle getChronicle(String name) {
		Criteria criteria = getNameCriteria(name);
		return mongoOperations.findOne(Query.query(criteria), Chronicle.class);
	}

	@Override
	public void updateChronicle(Chronicle chronicle) {
		Assert.notNull(chronicle);
		Criteria criteria = Criteria.where("_id").is(chronicle.getId());
		Query query = Query.query(criteria);
		Update update = Update.update("entries", chronicle.getEntries());
		mongoOperations.updateFirst(query, update, chronicle.getClass());
	}

	@Override
	public void deleteChronicle(String name) {
		Chronicle chronicle = removeEntriesFromMongo(name);
		mongoOperations.remove(chronicle);
	}

	@Override
	public List<Chronicle> getChronicles() {
		return mongoOperations.findAll(Chronicle.class);
	}

	private static Criteria getNameCriteria(String name) {
		return Criteria.where("name").is(name);
	}

	private Chronicle removeEntriesFromMongo(String name) {
		Criteria criteria = getNameCriteria(name);
		Chronicle chronicle = mongoOperations.findOne(Query.query(criteria), Chronicle.class);

		for (Entry entry : chronicle.getChronicleEntries()) {
			infoDao.deleteEntry(entry.getTopic());
		}
		return chronicle;
	}

	@Override
	public void insertChronicle(Chronicle chronicle) {
		mongoOperations.save(chronicle);
	}
}
