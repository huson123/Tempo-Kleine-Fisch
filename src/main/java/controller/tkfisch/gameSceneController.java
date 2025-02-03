package controller.tkfisch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.Entity;
import backend.Gameplay;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class gameSceneController implements SceneController {
    private Controller appController;
    private Gameplay gameplay;
    private List<Entity> entities;
    private Timeline timeline = new Timeline();
    private final Map<ImageView, Timeline> idleTimelines = new HashMap<>();
    private String fishSelectedColor;

    @FXML
    AnchorPane pane;
    private String bg = "/animation/background/gameScene/";

    @FXML
    private Label fishScore, shipScore, result;

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
    private final String orangeFishMoveURL = "/animation/fish/move/orangeFishMove/";
    private final String orangeFishStillURL = "/animation/fish/idle/orangeFishStill/";

    @FXML
    private ImageView pinkFish;
    private final String pinkFishMoveURL = "/animation/fish/move/pinkFishMove/";
    private final String pinkFishStillURL = "/animation/fish/idle/pinkFishStill/";

    @FXML
    private ImageView yellowFish;
    private final String yellowFishMoveURL = "/animation/fish/move/yellowFishMove/";
    private final String yellowFishStillURL = "/animation/fish/idle/yellowFishStill/";

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

        Timeline temp = fishIdle(blueFishStillURL, blueFish);
        idleTimelines.put(blueFish, temp);
        temp.play();

        temp = fishIdle(pinkFishStillURL, pinkFish);
        idleTimelines.put(pinkFish, temp);
        temp.play();

        temp = fishIdle(orangeFishStillURL, orangeFish);
        idleTimelines.put(orangeFish, temp);
        temp.play();

        temp = fishIdle(yellowFishStillURL, yellowFish);
        idleTimelines.put(yellowFish, temp);
        temp.play();

        temp = shipIdle(shipStillURL, ship);
        idleTimelines.put(ship, temp);
        temp.play();

        temp = appController.setBackgroundAnimation(bg, 12, 0.2, pane);
        temp.play();
    }

    public void move(String color) throws IOException {
        boolean fishCaught = false;
        List<String> caughtFish = gameplay.getCaughtFish();
        List<String> escapedFish = gameplay.getEscapedFish();
        ImageView actionEntity = null;
        String moveURL = null;
        String stillURL = null;

        if (escapedFish.contains(color) && gameplay.getPlayerType().equals("Fish")) {
            addDelay(0.1, () -> {
                fishSelectSceneController fishSSC = (fishSelectSceneController) appController.getSceneController("fishSelect");
                appController.switchToScene("fishSelect");
                try {
                    fishSSC.init();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            return;
        } else if (escapedFish.contains(color) && gameplay.getPlayerType().equals("Ship")) {
            do {
                fishSelectedColor = gameplay.roll();
            } while (escapedFish.contains(fishSelectedColor) || caughtFish.contains(fishSelectedColor) || fishSelectedColor.equals("Green") || fishSelectedColor.equals("Red"));
            color = fishSelectedColor;
            fishSelectedColor = null;
        }

        if (caughtFish.contains(color)) {
            fishCaught = true;
            actionEntity = ship;
            moveURL = shipMoveURL;
            stillURL = shipStillURL;
            color = "Red";
        } else {
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
                case "Red":
                case "Green":
                    actionEntity = ship;
                    moveURL = shipMoveURL;
                    stillURL = shipStillURL;
                    break;
            }
        }

        for (Entity entity : entities) {
            if (entity.getColors().contains(color)) {
                entity.move();

                if (actionEntity != null) {
                    String tempStillURL = stillURL;
                    ImageView tempEntity = actionEntity;
                    Timeline idleTimeline = idleTimelines.get(actionEntity);
                    if (idleTimeline != null) {
                        idleTimeline.stop();
                        idleTimelines.remove(actionEntity);
                    }
                    timeline.getKeyFrames().clear();

                    if (entity.getType() == Entity.Type.FISH && !fishCaught) {
                        timeline = fishMove(moveURL, actionEntity);
                        musicf();
                        timeline.play();
                        ImageView finalActionEntity = actionEntity;
                        timeline.setOnFinished(e -> {
                            moveImageView(32, tempEntity);
                            finalActionEntity.setFitHeight(32);
                            finalActionEntity.setFitWidth(32);
                            finalActionEntity.setY(ship.getY());
                            try {
                                Timeline newIdleTimeline = fishIdle(tempStillURL, tempEntity);
                                idleTimelines.put(tempEntity, newIdleTimeline);
                                newIdleTimeline.play();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (gameplay.fishEndUpdate(entity)) {
                                musicFinish();
                                removeImageView(finalActionEntity);
                                displayScore(fishScore);
                                gameplay.addEscapedFish(entity.getColors().get(0));
                            }
                        });
                    } else if (entity.getType() == Entity.Type.SHIP || fishCaught) {
                        fishCaught = false;
                        moveImageView(-10, tempEntity);
                        timeline = shipMove(moveURL, actionEntity);
                        musics();
                        timeline.play();
                        timeline.setOnFinished(e -> {
                            moveImageView(42, tempEntity);
                            ship.setFitHeight(75);
                            ship.setFitWidth(75);
                            ship.setY(ship.getY() + 32);

                            List<Entity> temp = gameplay.collisionUpdate(entity);
                            displayScore(shipScore);
                            if (!temp.isEmpty()) {
                                try {
                                    timeline = shipCatch(shipCatchURL, ship);
                                    musicc();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                timeline.play();
                                timeline.setOnFinished(event -> {
                                    try {
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
                                    Timeline newIdleTimeline = shipIdle(tempStillURL, tempEntity);
                                    idleTimelines.put(tempEntity, newIdleTimeline);
                                    newIdleTimeline.play();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    public void moveImageView(int amount, ImageView obj) {
        obj.setX(obj.getX() + amount);
    }

    public void removeImageView(ImageView obj) {
        pane.getChildren().remove(obj);
    }

    public ImageView convertToImageView(Entity fish) {
        switch (fish.getName()) {
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

    private void addDelay(double seconds, Runnable onComplete) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> onComplete.run());
        pause.play();
    }

    private void displayScore(Label obj) {
        if (obj == fishScore) {
            fishScore.setText("Fish: " + gameplay.getFishScore());
        } else {
            shipScore.setText("Ship: " + gameplay.getShipScore());
        }
    }

    public void displayResult(String str) {
        result.setText(str);
    }

    public Timeline fishMove(String moveURL, ImageView fish) throws IOException {
        fish.setFitHeight(70);
        fish.setFitWidth(70);
        fish.setY(ship.getY() - 15);
        return appController.playAnimation(moveURL, 21, 0.1, fish);
    }

    public Timeline fishIdle(String idleURL, ImageView fish) throws IOException {
        return appController.playIdleAnimation(idleURL, 12, 0.2, fish);
    }

    public Timeline shipMove(String moveURL, ImageView ship) throws IOException {
        ship.setFitHeight(150);
        ship.setFitWidth(150);
        ship.setY(ship.getY() - 32);
        return appController.playAnimation(moveURL, 6, 0.2, ship);
    }

    public Timeline shipCatch(String catchURL, ImageView ship) throws IOException {
        return appController.playAnimation(catchURL, 11, 0.2, ship);
    }

    public Timeline shipIdle(String idleURL, ImageView ship) throws IOException {
        return appController.playIdleAnimation(idleURL, 12, 0.2, ship);
    }

    public void dicePressed(MouseEvent event) throws IOException {
        music();
        appController.switchToScene("dice");
    }

    public void diceEntered(MouseEvent event) throws IOException {
        Timeline tl = appController.playAnimation(diceIdleUrl, 12, 0.1, dice);
        tl.play();
    }

    public void diceExited(MouseEvent event) {
        dice.setImage(new Image(getClass().getResourceAsStream("/image/dice.png")));
    }

    MediaPlayer mediaPlayer;

    public void musicf() {
        String s = getClass().getResource("/music/splashmusic.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.2);
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> mediaPlayer.play());
        delay.play();
        mediaPlayer.play();
    }

    public void musics() {
        String s = getClass().getResource("/music/shipmove.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
    }

    public void music() {
        String s = getClass().getResource("/music/tap.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }

    public void musicFinish() {
        String s = getClass().getResource("/music/finish.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }

    public void musicc() {
        String s = getClass().getResource("/music/catched.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }
}
