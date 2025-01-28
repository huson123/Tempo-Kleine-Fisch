package controller.tkfisch;

import backend.Entity;
import backend.Fish;
import backend.Gameplay;
import controller.tkfisch.main.Main;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class gameSceneController implements SceneController {
    private Controller appController;
    private Gameplay gameplay;
    private List<Entity> entities;
    //timeline for action
    private Timeline timeline = new Timeline();
    //timeline for idle
    private final Map<ImageView, Timeline> idleTimelines = new HashMap<>();
    private String fishSelectedColor;

    @FXML
    AnchorPane pane;
    private String bg = "/animation/background/gameScene/";

    @FXML
    private Label fishScore,shipScore,result;

    @FXML
    private ImageView ship;
    private final String shipMoveURL = "/animation/ship/move/";
    private final String shipStillURL = "/animation/ship/idle/";
    private final String shipCatchURL = "/animation/ship/catch/";

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
    private final String diceIdleUrl = "/animation/dice/diceIdle/";


    public gameSceneController() throws IOException {
    }


    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }
    public void init() throws IOException {
        gameplay = appController.getGameplay();
        entities = gameplay.getEntities();



        Timeline temp = fishIdle(blueFishStillURL,blueFish);
        idleTimelines.put(blueFish,temp);
        temp.play();

        temp = fishIdle(blueFishStillURL,pinkFish);
        idleTimelines.put(pinkFish,temp);
        temp.play();

        temp = fishIdle(blueFishStillURL,orangeFish);
        idleTimelines.put(orangeFish,temp);
        temp.play();

        temp = fishIdle(blueFishStillURL,yellowFish);
        idleTimelines.put(yellowFish,temp);
        temp.play();

        temp = shipIdle(shipStillURL,ship);
        idleTimelines.put(ship,temp);
        temp.play();

        temp = appController.setBackgroundAnimation(bg, 12,0.2,pane);
        temp.play();
    }

    //MISC
    public void move (String color) throws IOException {
        // TODO MIGHT NEED TO REMOVE X AND Y POS IN ENTITY AS REDUNDANCY
        //MAIN LOGIC BLOCK
        //flag to determine if the color has been caught
        boolean fishCaught = false;
        List<String> caughtFish = gameplay.getCaughtFish();
        List <String> escapedFish = gameplay.getEscapedFish();
        ImageView actionEntity = null;
        String moveURL = null;
        String stillURL = null;

        System.out.println(entities);
        //System.out.println(escapedFish);
        //System.out.println(caughtFish);
        //System.out.println(gameplay.getPlayerType());

        //DETERMINE COLOR TO MOVE IN FRONT END
        //AND LOGIC REGARDING FISH CAUGHT AND ESCAPED
            if (escapedFish.contains(color)
                    && (gameplay.getPlayerType().equals("Fish")
            )){
                //System.out.println(stage.getScene());
                //System.out.println("fish when fish escaped");
                //System.out.println("Fish escaped while being Fish");
                    // Add a delay of 1 second before switching scenes
                    addDelay(0.1, () -> {
                        fishSelectSceneController fishSSC =
                                (fishSelectSceneController) appController.getSceneController("fishSelect");
                        appController.switchToScene("fishSelect");
                        fishSSC.init();
                    });
                return;
            }
            else if (escapedFish.contains(color) && (gameplay.getPlayerType().equals("Ship"))){
                //System.out.println("ship when fish escaped");
                do {
                    fishSelectedColor = gameplay.roll();
                } while (gameplay.getEscapedFish().contains(fishSelectedColor));
                color = fishSelectedColor;
                fishSelectedColor = null;
            }
            //System.out.println("keep running");
            //System.out.println((((Fish) entity)).isCaught());
            //System.out.println(entity.getColors().get(0));
            if(caughtFish.contains(color)) {
                // if fish has been caught boat move
                //System.out.println("fish caught");
                fishCaught = true;
                actionEntity = ship;
                moveURL = shipMoveURL;
                stillURL = shipStillURL;
                color = "Red";
            } else {
                // if fish has not been caught, move acc to color
                switch (color) {
                    case "Blue":
                        actionEntity = blueFish;
                        moveURL = blueFishMoveURL;
                        stillURL = blueFishStillURL;
                        break;
                    case "Pink":
                        actionEntity = pinkFish;
                        moveURL = pinkFishMoveURL;
                        stillURL = pinkFishStillURL;
                        break;
                    case "Yellow":
                        actionEntity = yellowFish;
                        moveURL = yellowFishMoveURL;
                        stillURL = yellowFishStillURL;
                        break;
                    case "Orange":
                        actionEntity = orangeFish;
                        moveURL = orangeFishMoveURL;
                        stillURL = orangeFishStillURL;
                        break;
                    case "Red", "Green":
                        actionEntity = ship;
                        moveURL = shipMoveURL;
                        stillURL = shipStillURL;
                        break;
                }
            }

        for (Entity entity : entities) {
            if (entity.getColors().contains(color)) {
                entity.move();
                System.out.println(entity.getPosition());
                System.out.println(entity.getxPos());


                if (actionEntity != null) {
                    String tempStillURL = stillURL;
                    ImageView tempEntity = actionEntity;
                    // Stop and clear the idle animation if it exists
                    Timeline idleTimeline = idleTimelines.get(actionEntity);
                    if (idleTimeline != null) {
                        idleTimeline.stop();
                        idleTimelines.remove(actionEntity);
                    }
                    timeline.getKeyFrames().clear();


                    // Play the movement animation

                    if (entity.getType() == Entity.Type.FISH && !fishCaught) {
                        moveImageView(18, tempEntity);
                        timeline = fishMove(moveURL, actionEntity);
                        timeline.play();
                        ImageView finalActionEntity = actionEntity;
                        timeline.setOnFinished(e -> {
                            moveImageView(14, tempEntity);
                            try {
                                // Start the idle animation again after moving
                                Timeline newIdleTimeline = fishIdle(tempStillURL, tempEntity);
                                idleTimelines.put(tempEntity, newIdleTimeline);
                                newIdleTimeline.play();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (gameplay.fishEndUpdate(entity)) {
                                removeImageView(finalActionEntity);
                                displayScore(fishScore);
                                gameplay.addEscapedFish(entity.getColors().get(0));
                            }
                        });
                    } else if (entity.getType() == Entity.Type.SHIP || fishCaught) {
                        moveImageView(-4,tempEntity);
                        //System.out.println("ship called");
                        //play ship move, if detect collision then play catch
                        //move
                        timeline = shipMove(moveURL, actionEntity);
                        //moveImageView(18, tempEntity);
                        timeline.play();
                        timeline.setOnFinished(e -> {
                            moveImageView(36, tempEntity);
                            //re adjust for smol animation
                            ship.setFitHeight(124);
                            ship.setFitWidth(105);
                            ship.setY(ship.getY() + 20);

                            List<Entity> temp = gameplay.collisionUpdate(entity);
                            displayScore(shipScore);
                            System.out.println(temp);
                            if (!temp.isEmpty()) {
                                try {
                                    //catch
                                    timeline = shipCatch(shipCatchURL, ship);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                timeline.play();
                                timeline.setOnFinished(event -> {
                                    try {
                                        //idle
                                        // Start the idle animation again after moving
                                        Timeline newIdleTimeline = shipIdle(tempStillURL, tempEntity);
                                        idleTimelines.put(tempEntity, newIdleTimeline);
                                        newIdleTimeline.play();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    for (Entity fish : temp) {
                                        gameplay.addCaughtFish(fish.getColors().get(0));
                                        removeImageView(convertToImageView(fish));
                                    }
                                });
                            } else {
                                try {
                                    //idle
                                    // Start the idle animation again after moving
                                    Timeline newIdleTimeline = shipIdle(tempStillURL, tempEntity);
                                    idleTimelines.put(tempEntity, newIdleTimeline);
                                    newIdleTimeline.play();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                        fishCaught = false;
                    }


                }

            }
        }
    }

    public void moveImageView (int amount, ImageView obj){
        //move image view obj on x axis
        obj.setX(obj.getX() + amount);
    }
    public void removeImageView(ImageView obj){
            pane.getChildren().remove(obj);
    }
    public ImageView convertToImageView(Entity fish){
            switch (fish.getName()){
                case "blueFish":
                    return blueFish;
                case "pinkFish":
                    return pinkFish;
                case "yellowFish":
                    return yellowFish;
                case "orangeFish":
                    return orangeFish;

            }
        return null;
    }
    public void setFishSelectedColor(String color){
        fishSelectedColor = color;
    }
    private void addDelay(double seconds, Runnable onComplete) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> onComplete.run());
        pause.play();
    }
    private void displayScore (Label obj){
        if (obj == fishScore){
            fishScore.setText("Fish: " + gameplay.getFishScore());
        }
        else {
            shipScore.setText("Ship: " + gameplay.getShipScore());
        }
    }
    public void displayResult(String str){
        result.setText(str);
    }

    //FISH SECTION
    public Timeline fishMove (String moveURL, ImageView fish) throws IOException {
        return appController.playAnimation(moveURL,21,0.1,fish);
    }
    public Timeline fishIdle (String idleURL, ImageView fish) throws IOException {
        return appController.playIdleAnimation(idleURL,12,0.2,fish);
    }

    //SHIP SECTION
    public Timeline shipMove (String moveURL,ImageView ship) throws IOException {
        //adjust for small animation
        ship.setFitHeight(150);
        ship.setFitWidth(150);
        ship.setY(ship.getY() - 20);
        return appController.playAnimation(moveURL,6,0.2,ship);
    }
    public Timeline shipCatch (String catchURL, ImageView ship) throws IOException {
        return appController.playAnimation(catchURL,11,0.2,ship);
    }
    public Timeline shipIdle (String idleURL,ImageView ship) throws IOException {
        return appController.playIdleAnimation(idleURL,12,0.2,ship);
    }

    //DICE SECTION
    public void dicePressed(MouseEvent event) throws IOException {
        appController.switchToScene("dice");
    }
    public void diceEntered(MouseEvent event) throws IOException {
        System.out.println("dice hovered");
        Timeline tl = appController.playAnimation(diceIdleUrl,12, 0.1, dice);
        tl.play();
    }
    public void diceExited(MouseEvent event){
        System.out.println("dice exited");
        dice.setImage(new Image(getClass().getResourceAsStream("/image/dice.png")));
    }
}
