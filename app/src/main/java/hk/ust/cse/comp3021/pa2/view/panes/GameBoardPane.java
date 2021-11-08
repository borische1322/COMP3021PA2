package hk.ust.cse.comp3021.pa2.view.panes;

import hk.ust.cse.comp3021.pa2.model.GameBoard;
import hk.ust.cse.comp3021.pa2.model.GameState;
import hk.ust.cse.comp3021.pa2.view.GameUIComponent;
import hk.ust.cse.comp3021.pa2.view.controls.GameCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * A {@link javafx.scene.layout.Pane} for displaying the status of {@link hk.ust.cse.comp3021.pa2.model.GameBoard}.
 */
public class GameBoardPane extends GridPane implements GameUIComponent {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeComponents() {
        this.setAlignment(Pos.CENTER);
        this.setCenterShape(true);
    }

    /**
     * Updates the game board display with latest {@link GameState}.
     *
     * @param gameState The latest {@link GameState}.
     */
    public void showGameState(GameState gameState) {
        // TODO: ok Update the content based on the state of the game board.
        GridPane x = new GridPane();
        createGameBoardPane(gameState.getGameBoard().getNumRows(),
                            gameState.getGameBoard().getNumCols(),
                            x, gameState.getGameBoard());

        this.getChildren().add(x);

    }
    //helper func
    private static void createGameBoardPane(final int numRows, final int numCols, GridPane a, GameBoard x) {
        a.setAlignment(Pos.CENTER);

        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j++){
                a.add(new GameCell(x.getCell(i, j)),j,i);
            }
        }
    }

}
