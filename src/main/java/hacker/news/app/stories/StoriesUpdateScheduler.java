package hacker.news.app.stories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;
import java.util.Date;

@Configuration
@EnableAsync
@EnableScheduling
public class StoriesUpdateScheduler {

    private static final long SECONDS = 1000;
    private static final long MINUTES = 60 * SECONDS;
    private long lastUpdate = 0;

    private final StoriesUpdater storiesUpdater;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public StoriesUpdateScheduler(StoriesUpdater storiesUpdater) {
        this.storiesUpdater = storiesUpdater;
    }

    @Scheduled(initialDelay = 1 * SECONDS, fixedRate = MINUTES)
    public void run() {
        try {
            if (startStorieSchedulerTask()) {
                logger.debug("Start to create stories");
                storiesUpdater.update();
                logger.debug("Finished creating stories");

            } else {
                logger.debug("Nothing to start");
            }

        } catch (Throwable e) {
            logger.error("Error while updating stories", e);
        }
    }

    private boolean startStorieSchedulerTask() {
        return new Date().getTime() >= this.lastUpdate ? true : false;
    }
}
