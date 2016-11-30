package de.lhug.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.Assert;

import de.lhug.entities.Entry.EntryType;

public class Chronicle {

	@Id
	private String id;

	private String name;

	private Date beginDate;

	@DBRef
	private final List<Entry> entries = new LinkedList<>();

	public Chronicle() {
		this(StringUtils.EMPTY);
	}

	public Chronicle(String name) {
		this(name, new Date());
	}

	public Chronicle(String name, Date beginDate) {
		this.name = name;
		this.beginDate = beginDate;
	}

	public String getName() {
		return name;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public List<Entry> getChronicleEntries() {
		return entries;
	}

	public void addChronicleEntry(Entry entry) {
		Assert.isTrue(entry.getType() == EntryType.CHRONICLE);
		entries.add(entry);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
}
