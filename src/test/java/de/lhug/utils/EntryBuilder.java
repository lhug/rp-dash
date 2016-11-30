package de.lhug.utils;

import org.apache.commons.lang3.StringUtils;

import de.lhug.entities.Entry;
import de.lhug.entities.Entry.EntryType;

public class EntryBuilder {

	private EntryBuilder() {
	}

	public static Entry entry(EntryType type) {
		return entry(type, StringUtils.EMPTY);
	}

	public static Entry entry(EntryType type, String topic) {
		return entry(type, topic, StringUtils.EMPTY);
	}

	public static Entry entry(EntryType type, String topic, String content) {
		Entry entry = new Entry();
		entry.setType(type);
		entry.setTopic(topic);
		entry.setContent(content);
		return entry;
	}
}
