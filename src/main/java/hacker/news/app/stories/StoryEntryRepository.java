package hacker.news.app.stories;

import java.util.List;

public interface StoryEntryRepository {
    StoryEntry create(StoryEntry any);

    StoryEntry find(long timeEntryId);

    StoryEntry update(long id, StoryEntry any);

    boolean delete(long timeEntryId);

    List<StoryEntry> list();

    void deleteAll();
}
