package controller.tkfisch;

import backend.Entity;
import backend.Gameplay;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class gameSceneController implements SceneController {
    private Controller appController;
    private final String diceIdleUrl = "/animation/dice/diceIdle/";
    private final Gameplay gameplay = new Gameplay();
    private final List<Entity> entities = gameplay.getEntities();
    private Timeline tl = new Timeline();

    //create timeline to play idle animation for entities
    @FXML
    private ImageView ship;
    @FXML
    private ImageView blueFish;
    private final String blueFishMoveURL = "/animation/fish/move/blueFishMove/";
    private final String blueFishStillURL = "/animation/fish/idle/blueFishStill/";
    private Timeline blueFishIdle = fishIdle(blueFishStillURL,blueFish);

    @FXML
    private ImageView orangeFish;
    private Timeline orangeFishIdle = fishIdle(blueFishStillURL,orangeFish);

    @FXML
    private ImageView pinkFish;
    private Timeline pinkFishIdle = fishIdle(blueFishStillURL,pinkFish);

    @FXML
    private ImageView yellowFish;
    private Timeline yellowFishIdle = fishIdle(blueFishStillURL,yellowFish);

    @FXML
    private ImageView dice;

    public gameSceneController() throws IOException {
    }


    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }
    public void initialize() {
        //TODO add background animation
        blueFishIdle.play();
        yellowFishIdle.play();
        pinkFishIdle.play();
        orangeFishIdle.play();
    }


    public void move (String color) throws IOException {
        // TODO MIGHT NEED TO REMOVE X AND Y POS IN ENTITY AS REDUNDANCY

        for (Entity entity : entities)
        {
            if (entity.getColors().contains(color))
            {
                entity.move();
                System.out.println(entity.getPosition());
                System.out.println(entity.getxPos());
                switch (color){
                    case "Blue":
                        System.out.println("blue is moved");
                        blueFishIdle.pause();
                        tl = fishMove(blueFishMoveURL,blueFish);
                        tl.play();
                        tl.setOnFinished(e ->{
                            moveImageView(32, blueFish);
                            blueFishIdle.play();
                        });
                        break;
                    case "Pink":
                        System.out.println("pink is moved");
                        moveImageView(32, pinkFish);
                        pinkFishIdle.pause();
                        tl = fishMove(blueFishMoveURL,pinkFish);
                        tl.play();
                        tl.setOnFinished(e ->{
                            moveImageView(32, pinkFish);
                            pinkFishIdle.play();
                        });

                        break;
                    case "Yellow":
                        System.out.println("yellow is moved");
                        moveImageView(32, yellowFish);
                        yellowFishIdle.pause();
                        tl = fishMove(blueFishMoveURL,yellowFish);
                        tl.play();
                        tl.setOnFinished(e ->{
                            moveImageView(32, yellowFish);
                            yellowFishIdle.play();
                        });
                        break;
                    case "Orange":
                        System.out.println("orange is moved");
                        moveImageView(32, orangeFish);
                        orangeFishIdle.pause();
                        tl = fishMove(blueFishMoveURL,orangeFish);
                        tl.play();
                        tl.setOnFinished(e ->{
                            moveImageView(32, orangeFish);
                            orangeFishIdle.play();
                        });
                        break;
                }
            }
        }
    }
    //MISC
    public void moveImageView (int amount, ImageView obj){
        //move image view obj on x axis positive
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
