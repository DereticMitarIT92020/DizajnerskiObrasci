package strategy;

import java.io.FileNotFoundException;

public interface Record {
	
	void save();
	Object load() throws FileNotFoundException;
	
}
