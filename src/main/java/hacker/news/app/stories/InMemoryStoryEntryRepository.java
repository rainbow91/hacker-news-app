package hacker.news.app.stories;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStoryEntryRepository implements StoryEntryRepository {
    private List<StoryEntry> storyEntryies;

    private long localId = 0L;

    public InMemoryStoryEntryRepository() {
    }
    public  List<StoryEntry> getStoryEntries() {
        return storyEntryies;
    }

    public void setStoryEntries(List<StoryEntry> storyEntryies) {
        storyEntryies = storyEntryies;
    }

    public  long getLocalId() {
        return localId;
    }

    public  long nextLocalId() {
        return ++localId;
    }

    @Override
    public StoryEntry create(StoryEntry any) {
        if ( any.getId() == 0) any.setId(nextLocalId());
        this.list().add(any);
        return any;
    }

    @Override
    public StoryEntry find(long storyEntryyId) {
        for(StoryEntry item: this.list()) {
            if(item.getId() == storyEntryyId){
                return item;
            }
        }

        return null;
    }

    @Override
    public StoryEntry update(long id, StoryEntry any) {
        StoryEntry item = this.find(id);
        if (item != null) {
            item.setId(any.getId());
            item.setTitle(any.getTitle());
            item.setUrl(any.getUrl());
        }
        return item;
    }

    @Override
    public boolean delete(long storyEntryyId) {
        StoryEntry item = this.find(storyEntryyId);
        boolean deleted = false;
        if (item != null) {
            deleted = list().remove(item);
        }
        return deleted;
    }

    @Override
    public List<StoryEntry> list() {

      return  this.storyEntryies;
    }

    @Override
    public void deleteAll() {
        this.storyEntryies = new ArrayList<StoryEntry>();
    };
}
