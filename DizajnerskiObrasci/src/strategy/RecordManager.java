package strategy;

import java.io.FileNotFoundException;

public class RecordManager {
	
	private Record record;
	
	public RecordManager() {

	}
	
	public RecordManager(Record record) {
		this.record = record;
	}
	
	public void setRecord(Record record) {
		this.record = record;
	}
	
	public void save() {
		record.save();
	}
	
	public Object load() throws FileNotFoundException {
		return record.load();
	}
}
