package pl.devopsi.kafkatutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaTutorialApplication {

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaTutorialApplication.class, args);

		MessageProducer producer = context.getBean(MessageProducer.class);
		MessageListener listener = context.getBean(MessageListener.class);

		Thread.sleep(4000);
		producer.sendMessage("Hello world");
		listener.getLatch().await(10, TimeUnit.SECONDS);

		context.close();
	}

}
