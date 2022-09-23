package pl.devopsi.kafkatutorial;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@Getter
public class MessageListener {

    private CountDownLatch latch;

    @PostConstruct
    private void postConstruct() {
        latch = new CountDownLatch(3);
    }

    @KafkaListener(topics = "${message.topic.name}", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload String message,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Received message: {} from partition: {}}", message, partition);
        latch.countDown();
    }
}
