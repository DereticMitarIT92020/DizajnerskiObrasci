public class DrawingFrame implements Observer {

    @Override
	public void update(int createdShapesCount, int selectedShapesCount, int executedCommandsCount,
			int unexecutedCommandsCount, int currentShapeIndex, int commandsFromFileCount) {
		
		if (executedCommandsCount > 0) {
			btnNewButton_7.setEnabled(true);
		} else {
			btnNewButton_7.setEnabled(false);
		}
		
		if (unexecutedCommandsCount > 0) {
			btnNewButton_8.setEnabled(true);
		} else {
			btnNewButton_8.setEnabled(false);
		}
		
		if (createdShapesCount > 0) {
			getSelectButton().setEnabled(true);
		} else {
			getSelectButton().setEnabled(true);
		}
		
		if (selectedShapesCount == 1) {
			getModifyButton().setEnabled(true);
			
			if (currentShapeIndex < createdShapesCount - 1) {
				getToFrontButton().setEnabled(true);
				getBringToFrontButton().setEnabled(true);
			} else {
				getToFrontButton().setEnabled(false);
				getBringToFrontButton().setEnabled(false);
			}
			
			if (currentShapeIndex > 0) {
				getToBackButton().setEnabled(true);
				getBringToBackButton().setEnabled(true);
			} else {
				getToBackButton().setEnabled(false);
				getBringToBackButton().setEnabled(false);
			}
			
			
		} else {
			getModifyButton().setEnabled(false);
			getToFrontButton().setEnabled(false);
			getToBackButton().setEnabled(false);
			getBringToFrontButton().setEnabled(false);
			getBringToBackButton().setEnabled(false);
		}
		
		if (selectedShapesCount >= 1) {
			getDeleteButton().setEnabled(true);
		} else {
			getDeleteButton().setEnabled(false);
		}
		
		if (createdShapesCount > 0) {
			btnNewButton_16.setEnabled(true);
		} else {
			btnNewButton_16.setEnabled(false);
		}
		
		if (executedCommandsCount > 0 || unexecutedCommandsCount > 0) {
			btnNewButton_15.setEnabled(true);
		} else {
			btnNewButton_15.setEnabled(false);
		}
		
		if (commandsFromFileCount > 0) {
			btnNewButton_19.setEnabled(true);
		} else {
			btnNewButton_19.setEnabled(false);
		}
	}
}
