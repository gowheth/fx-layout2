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
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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


    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "demo-topic";

    @FXML
    public void initialize() throws InterruptedException {
        Platform.runLater(() -> {
            double v = mainVBox.getHeight() - (bottomHBox.getHeight() + mainMenuBar.getHeight() + topAnchorPane.getHeight());
            System.out.println("v: " + v);
            leftToolBar.setPrefWidth(v);
        });

        mainVBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                leftToolBar.setPrefWidth(mainVBox.getHeight() - (bottomHBox.getHeight() + mainMenuBar.getHeight() + topAnchorPane.getHeight()));
            }
        });

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        SenderOptions<Integer, String> senderOptions = SenderOptions.create(props);

        sender = KafkaSender.create(senderOptions);
        dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");

        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);

        sendMessages(TOPIC, count, latch);
        latch.await(10, TimeUnit.SECONDS);
        sender.close();

    }

    private KafkaSender<Integer, String> sender;
    private SimpleDateFormat dateFormat;

    public void sendMessages(String topic, int count, CountDownLatch latch) throws InterruptedException {
        sender.send(Flux.range(1, count)
                        .map(i -> SenderRecord.create(new ProducerRecord<>(topic, i, "Message_" + i), i)))
                .doOnError(e -> System.out.println("Error: " + e))
                .subscribe(r -> {
                    RecordMetadata metadata = r.recordMetadata();
                    System.out.printf("Message %d sent successfully, topic-partition=%s-%d offset=%d timestamp=%s\n",
                            r.correlationMetadata(),
                            metadata.topic(),
                            metadata.partition(),
                            metadata.offset(),
                            dateFormat.format(new Date(metadata.timestamp())));
                    latch.countDown();
                });
    }

}