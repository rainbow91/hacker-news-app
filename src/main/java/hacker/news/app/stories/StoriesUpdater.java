package hacker.news.app.stories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.springframework.http.HttpMethod.GET;

@Service
public class StoriesUpdater {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private StoryEntryRepository timeEntriesRepo ;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ParameterizedTypeReference<List<Integer>> idsTypeReferece =
            new ParameterizedTypeReference<List<Integer>>() {};

    public StoriesUpdater(StoryEntryRepository timeEntriesRepo ) {
        this.timeEntriesRepo = timeEntriesRepo;
    }

    public void update() throws IOException {
        logger.debug("Delete all stories");
        this.timeEntriesRepo.deleteAll();
        getStories();
    }

    private void getStories() throws IOException {
        List<Integer> ids = restTemplate
                .exchange("https://hacker-news.firebaseio.com/v0/topstories.json", GET, null, idsTypeReferece)
                .getBody();

        Optional
                .ofNullable(ids)
                .orElse(emptyList())
                .stream()
                .limit(10)
                .map(this::getStoryDetails)
                .forEach(x ->
                        this.timeEntriesRepo.create(new StoryEntry(x.getTitle(), x.getUrl())));
    }

    private StoryJson getStoryDetails(long id) {
        String url = "https://hacker-news.firebaseio.com/v0/item/" + id + ".json";
        return restTemplate.getForObject(url, StoryJson.class);
    }

    public static class StoryJson {
        private String url;
        private String title;

        public StoryJson(String url, String title) {
            this.url = url;
            this.title = title;
        }

        public StoryJson() {}

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StoryJson storyJson = (StoryJson) o;
            return Objects.equals(url, storyJson.url) &&
                    Objects.equals(title, storyJson.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url, title);
        }
    }
}
