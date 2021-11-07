package hk.ust.cse.comp3021.pa2.view.panes;

import hk.ust.cse.comp3021.pa2.controller.GameController;
import hk.ust.cse.comp3021.pa2.model.Direction;
import hk.ust.cse.comp3021.pa2.model.MoveResult;
import hk.ust.cse.comp3021.pa2.util.NotImplementedException;
import hk.ust.cse.comp3021.pa2.view.GameUIComponent;
import hk.ust.cse.comp3021.pa2.view.events.MoveEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link javafx.scene.layout.Pane} representing the control area of the game.
 */
public class GameControlPane extends GridPane implements GameUIComponent {

    private final Button upButton = new Button("\u2191");
    private final Button downButton = new Button("\u2193");
    private final Button leftButton = new Button("\u2190");
    private final Button rightButton = new Button("\u2192");

    private final Button undoButton = new Button("UNDO");

    private GameController gameController;

    private final ObjectProperty<EventHandler<MoveEvent>> moveEvent = new ObjectPropertyBase<>() {
        @Override
        public Object getBean() {
            return GameControlPane.this;
        }

        @Override
        public String getName() {
            return "onMove";
        }
    };

    /**
     * Performs a move action towards the specified {@link Direction}.
     *
     * @param direction The {@link Direction} to move.
     */
    private void move(@NotNull Direction direction) {
        // TODO:? Perform move action on game controller and trigger the move event with its result.
        moveEvent.get().handle(new MoveEvent(gameController.processMove(direction)));
    }

    /**
     * Sets the {@link EventHandler} for the move event.
     *
     * @param handler The handler.
     */
    public void setOnMove(EventHandler<MoveEvent> handler) {
        this.moveEvent.set(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeComponents() {
        setLayout();

        // Setup and add the move buttons.
        setMoveButtonsHandler();
        styleMoveButtons();
        addMoveButtons();

        // Setup and add the undo button.
        setUndoButtonLayout();
        this.add(undoButton, 0, 3, 3, 1);
    }

    private void setUndoButtonLayout() {
        this.undoButton.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(undoButton, true);

        // TODO:? Add event handler for the click event of the undo button.
        this.undoButton.setOnAction(e -> {performUndo();});
    }

    /**
     * Set the event handlers for the move buttons
     */
    private void setMoveButtonsHandler() {
        // TODO:? Add event handler for the click event of the move buttons.
        this.upButton.setOnAction(e -> {move(Direction.UP);});
        this.downButton.setOnAction(e -> {move(Direction.DOWN);});
        this.leftButton.setOnAction(e -> {move(Direction.LEFT);});
        this.rightButton.setOnAction(e -> {move(Direction.RIGHT);});
    }

    /**
     * Sets the layout of the panel.
     */
    private void setLayout() {
        this.setPadding(new Insets(10));
        this.setVgap(5);
        this.setHgap(5);
    }

    /**
     * Adds the move buttons to the appropriate position in the grid.
     */
    private void addMoveButtons() {
        // TODO:? Place the move buttons to appropriate position of the pane.
        GridPane a = new GridPane();
        a.setAlignment(Pos.CENTER);
        a.add(upButton, 2, 1);
        a.add(leftButton, 1, 2);
        a.add(rightButton, 3,2);
        a.add(downButton, 2, 3);
        a.add(undoButton,2,4);

        this.getChildren().add(a);
    }

    /**
     * Sets the CSS class for the move buttons
     */
    private void styleMoveButtons() {
        this.upButton.getStyleClass().add("move-button");
        this.downButton.getStyleClass().add("move-button");
        this.leftButton.getStyleClass().add("move-button");
        this.rightButton.getStyleClass().add("move-button");
    }

    /**
     * Sets the {@link GameController} on which the move actions are performed.
     *
     * @param gameController The {@link GameController}.
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Performs an undo action on the game.
     */
    public void performUndo() {
        // TODO:?  Perform undo on the game controller and trigger the move event with the move *that was just undone*.
        if (gameController.getGameState().getMoveStack().isEmpty())
            return;
        MoveResult x = gameController.getGameState().getMoveStack().peek();
        gameController.processUndo();
        moveEvent.get().handle(new MoveEvent(x));
    }
}
