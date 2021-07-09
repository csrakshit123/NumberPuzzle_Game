import java.awt.*;

class CustomNumberPuzzleControl extends NumberPuzzleControl {
	public int getWidth() {
		return 200;
	}
	public int getHeight() {
		return 250;
	}
	public int getXPosition() {
		return 200;
	}
	public int getYPosition() {
		return 200;
	}
	public String getTitle(){
		return "Number Puzzle";
	}
	public int getShuffleButtonFontSize() {
		return 12;
	}
	public int getNumbersFontSize() {
		return 12;
	}
	public Color getEmptyButtonColor() {
		return Color.WHITE;
	}
	public String getWinnerMessage() {
		return "Congrats, you have won!";
	}

	
	public int handleButtonClicked(NumberPuzzleGame game){
		int emptyCellId = game.getEmptyCellId();
		Button buttonClicked = game.getButtonClicked();
		Button[] buttons = game.getButtons();
		int buttonClickedId = getButtonId(buttons, buttonClicked);
		boolean hasEmptyCell = hasAdjacentEmptyCell(emptyCellId, buttonClickedId);
		if (hasEmptyCell){
			swapButton(buttons[emptyCellId], buttonClicked);
			emptyCellId = buttonClickedId;
		}
		return emptyCellId;

	}

	/**
	 *
	 * @param buttons
	 * @param buttonClicked
	 * @return button ID of the button clicked
	 */
	public int getButtonId(Button[] buttons, Button buttonClicked){
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].getLabel().equals(buttonClicked.getLabel())){
				return i;
			}
		}
		return -1;
	}

	/**
	 *
	 * @param emptyCellId
	 * @param buttonClickedId
	 * @return true if clicked button has empty adjacent cell.
	 */
	public boolean hasAdjacentEmptyCell(int emptyCellId, int buttonClickedId){
		int buttonRow = buttonClickedId / 4;
		int buttonCol = buttonClickedId % 4;
		int emptyCellRow = emptyCellId / 4;
		int emptyCellCol = emptyCellId % 4;
		if (buttonRow == emptyCellRow){
			if ((buttonCol+1) == emptyCellCol){
				return true;
			}
			return (buttonCol - 1) == emptyCellCol;
		}
		else if (buttonCol == emptyCellCol){
			if ((buttonRow + 1) == emptyCellRow){
				return true;
			}
			return (buttonRow - 1) == emptyCellRow;
		}
		return false;
	}
	public int[] getRandomNumbersForGrid() {
		int[] arr = new int[15];
		boolean[] checkExisting = new boolean[15];
		for (int i = 0; i < 15; i++) {
			int randomNumber = (int) (getRandomNumber()*15/100.0);
//			if the number already exists in arr check if number + 1 is available.
//			hence next available number strategy is used.
			if (checkExisting[randomNumber]){
				do {
					randomNumber = (randomNumber + 1) % 15;
				}while (checkExisting[randomNumber]);
			}
			arr[i] = randomNumber + 1;
			checkExisting[randomNumber] = true;
		}
		return arr;
	}

	public boolean checkForWinner(Button[] buttons)
	{
		boolean winner = true;
		int[] buttonIds = getIntegerArrayOfButtonIds(buttons);
		for (int i = 0; i < buttonIds.length; i++) {
			if (buttonIds[i] != i + 1){
				return false;
			}
		}
		return winner;
	}
}