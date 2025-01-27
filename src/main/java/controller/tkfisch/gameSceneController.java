package controller.tkfisch;

import backend.Entity;
import backend.Gameplay;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gameSceneController implements SceneController {
    private Controller appController;
    private final String diceIdleUrl = "/animation/dice/diceIdle/";
    private final Gameplay gameplay = new Gameplay();
    private final List<Entity> entities = gameplay.getEntities();
    private Timeline timeline = new Timeline();

    //create timeline to play idle animation for entities
    @FXML
    private ImageView ship;
    @FXML
    private ImageView blueFish;
    private final String blueFishMoveURL = "/animation/fish/move/blueFishMove/";
    private final String blueFishStillURL = "/animation/fish/idle/blueFishStill/";

    @FXML
    private ImageView orangeFish;
    private final String orangeFishMoveURL = "/animation/fish/move/blueFishMove/";
    private final String orangeFishStillURL = "/animation/fish/idle/blueFishStill/";

    @FXML
    private ImageView pinkFish;
    private final String pinkFishMoveURL = "/animation/fish/move/blueFishMove/";
    private final String pinkFishStillURL = "/animation/fish/idle/blueFishStill/";

    @FXML
    private ImageView yellowFish;
    private final String yellowFishMoveURL = "/animation/fish/move/blueFishMove/";
    private final String yellowFishStillURL = "/animation/fish/idle/blueFishStill/";;

    @FXML
    private ImageView dice;
    private final Map<ImageView, Timeline> idleTimelines = new HashMap<>();

    public gameSceneController() throws IOException {
    }


    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }
    public void initialize() throws IOException {
        //TODO add background animation
    }


    public void move (String color) throws IOException {
        // TODO MIGHT NEED TO REMOVE X AND Y POS IN ENTITY AS REDUNDANCY

        for (Entity entity : entities) {
            if (entity.getColors().contains(color)) {
                entity.move();
                System.out.println(entity.getPosition());
                System.out.println(entity.getxPos());

                ImageView actEntity = null;
                String moveURL = null;
                String stillURL = null;

                // Determine which fish to move based on color
                switch (color) {
                    case "Blue":
                        actEntity = blueFish;
                        moveURL = blueFishMoveURL;
                        stillURL = blueFishStillURL;
                        break;
                    case "Pink":
                        actEntity = pinkFish;
                        moveURL = pinkFishMoveURL;
                        stillURL = pinkFishStillURL;
                        break;
                    case "Yellow":
                        actEntity = yellowFish;
                        moveURL = yellowFishMoveURL;
                        stillURL = yellowFishStillURL;
                        break;
                    case "Orange":
                        actEntity = orangeFish;
                        moveURL = orangeFishMoveURL;
                        stillURL = orangeFishStillURL;
                        break;
                }

                if (actEntity != null) {
                    // Stop and clear the idle animation if it exists
                    Timeline idleTimeline = idleTimelines.get(actEntity);
                    if (idleTimeline != null) {
                        idleTimeline.stop();
                        idleTimelines.remove(actEntity);
                    }
                    moveImageView(18, actEntity);
                    // Play the movement animation
                    timeline.getKeyFrames().clear();
                    timeline = fishMove(moveURL, actEntity);
                    timeline.play();
                    String tempStillURL = stillURL;
                    ImageView tempFish = actEntity;
                    timeline.setOnFinished(e -> {
                        moveImageView(14, tempFish);
                        try {
                            // Start the idle animation again after moving
                            Timeline newIdleTimeline = fishIdle(tempStillURL, tempFish);
                            idleTimelines.put(tempFish, newIdleTimeline);
                            newIdleTimeline.play();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }
                break;
            }
        }
    }
    //MISC
    public void moveImageView (int amount, ImageView obj){
        //move image view obj on x axis
        obj.setX(obj.getX() + amount);
    }
    //FISH SECTION
    public Timeline fishMove (String moveURL, ImageView fish) throws IOException {
        return appController.playAnimation(moveURL,21,0.2,fish);
    }
    public Timeline fishIdle (String idleURL, ImageView fish) throws IOException {
        return appController.playIdleAnimation(idleURL,12,0.2,fish);
    }

    //SHIP SECTION
    public Timeline shipMove (String moveURL,ImageView ship) throws IOException {
        return appController.playAnimation(moveURL,7,0.2,ship);
    }
    public Timeline shipCatch (String catchURL, ImageView ship) throws IOException {
        return appController.playAnimation(catchURL,12,0.2,ship);
    }

    //DICE SECTION
    public void dicePressed(MouseEvent event) throws IOException {
        appController.switchToScene("dice");
    }
    public void diceEntered(MouseEvent event) throws IOException {
        System.out.println("dice hovered");
        Timeline tl = appController.playAnimation(diceIdleUrl,12, 0.2, dice);
        tl.play();
    }
    public void diceExited(MouseEvent event){
        System.out.println("dice exited");
        dice.setImage(new Image(getClass().getResourceAsStream("/image/dice.png")));
    }
}
