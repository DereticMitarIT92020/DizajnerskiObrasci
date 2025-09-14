public class DrawingController implements Observable {
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
	public void addObservers(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObservers(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		
		int createdShapesCount = 0;
		int selectedShapesCount = 0;
		int executedCommandsCount = commandsForUndo.size();
		int unexecutedCommandsCount = commandsForRedo.size();
		int currentShapePosition = 0;
		int commandsFromFileCount = commandsFromFile.size();
		
		for (CustomShape shape : this.model.getShapes()) {
			createdShapesCount++;
			if (shape.isSelected()) {
				selectedShapesCount++;
				currentShapePosition = this.model.getShapes().indexOf(shape);
			}
		}
		
		for (Observer observer : this.observers) {	
			observer.update(createdShapesCount, selectedShapesCount, executedCommandsCount, unexecutedCommandsCount, currentShapePosition, commandsFromFileCount);
		}
		
	}
    
}
