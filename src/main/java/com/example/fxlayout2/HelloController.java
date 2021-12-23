package com.example.fxlayout2;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private ToolBar leftToolBar;

    @FXML
    private VBox mainVBox;
    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private ToolBar rightToolBar;

    @FXML
    private AnchorPane leftAnchorPane;
    @FXML
    private AnchorPane topAnchorPane;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private HBox bottomHBox;

    @FXML
    private Label leftStatusLabel;
    @FXML
    private Label rightStatusLabel;
    @FXML
    private Label anchorStatusLabel;

    @FXML
    public void initialize() {

//        final ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
//                leftStatusLabel.setText("W: " + mainBorderPane.getWidth() + " H: " + mainBorderPane.getHeight());
//
//        mainBorderPane.widthProperty().addListener(stageSizeListener);
//        mainBorderPane.heightProperty().addListener(stageSizeListener);
//        mainBorderPane.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
////                leftToolBar.setPrefWidth(leftAnchorPane.getHeight());
//                System.out.println("anchor height " + leftAnchorPane.getHeight() + " toolbar width: " + leftToolBar.getWidth());
//            }
//        });


        Platform.runLater(() -> {
            double v = mainVBox.getHeight() - (bottomHBox.getHeight() + mainMenuBar.getHeight() + topAnchorPane.getHeight());
            System.out.println("v: " + v);
            leftToolBar.setPrefWidth(v);
        });


        mainVBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                leftToolBar.setPrefWidth(mainVBox.getHeight() - (bottomHBox.getHeight() + mainMenuBar.getHeight() + topAnchorPane.getHeight()));

//                System.out.println("anchor height " + leftAnchorPane.getHeight() + " toolbar width: " + leftToolBar.getWidth());
            }
        });


//        leftToolBar.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                leftStatusLabel.setText("W: " + leftToolBar.getWidth() + " H: " + leftToolBar.getHeight());
//            }
//        });
//        leftToolBar.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                leftStatusLabel.setText("W: " + leftToolBar.getWidth() + " H: " + leftToolBar.getHeight());
//            }
//        });
//
//        leftAnchorPane.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                anchorStatusLabel.setText("W: " + leftAnchorPane.getWidth() + " H: " + leftAnchorPane.getHeight());
//            }
//        });
//        leftAnchorPane.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                anchorStatusLabel.setText("W: " + leftAnchorPane.getWidth() + " H: " + leftAnchorPane.getHeight());
//            }
//        });
    }


//        leftToolBar.setMinWidth(mainBorderPane.getHeight());

//        leftToolBar.prefWidthProperty().bind(leftAnchorPane.heightProperty().asObject());
//        leftAnchorPane.prefHeightProperty().bind(leftToolBar.widthProperty());
//        final ChangeListener<Number> listener = new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                leftAnchorPane.getHeight();
//                leftToolBar.getWidth();
//                leftStatusLabel.setText("W: " + leftToolBar.getWidth() + " H: " + leftToolBar.getHeight());
//                leftToolBar.setPrefWidth(leftAnchorPane.getHeight() );
//            }
//        };
//
//        mainBorderPane.widthProperty().addListener(listener);
//        mainBorderPane.heightProperty().addListener(listener);
//
//        mainBorderPane.widthProperty().addListener(stageSizeListener);
//        mainBorderPane.heightProperty().addListener(stageSizeListener);
//
//
//        final ChangeListener<Number> toolbarListener = (observable, oldValue, newValue) ->
//                rightStatusLabel.setText("W: " + leftToolBar.getWidth() + " H: " + leftToolBar.getHeight());
//        leftToolBar.widthProperty().addListener(toolbarListener);
//        leftToolBar.heightProperty().addListener(toolbarListener);
//
//        final ChangeListener<Number> anchorPaneListener = (observable, oldValue, newValue) ->
//                anchorStatusLabel.setText("W: " + leftAnchorPane.getWidth() + " H: " + leftAnchorPane.getHeight());
//        leftAnchorPane.widthProperty().addListener(anchorPaneListener);
//        leftAnchorPane.heightProperty().addListener(anchorPaneListener);


}