package observer;

public interface Observer {
	
	void update(int createdShapesCount, int selectedShapesCount, int executedCommandsCount, int unexecutedCommandsCount, int currentShapeIndex, int commandsFromFileCount);
}
