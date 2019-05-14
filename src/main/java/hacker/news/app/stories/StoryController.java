package hacker.news.app.stories;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class StoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private StoryEntryRepository storyEntriesRepo ;

    public StoryController(StoryEntryRepository storyEntriesRepo) {
        this.storyEntriesRepo = storyEntriesRepo;
    }

    @GetMapping("stories")
    public String index(Model model) {
        logger.debug("entry list");
        model.addAttribute("stories", storyEntriesRepo.list());
        return "stories";
    }
}