package jzegzula.recruitment.task.gitHubUserFinder.finder;

import jzegzula.recruitment.task.gitHubUserFinder.model.GitHubRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/finder")
public class FinderController {

  private final FinderService finderService;

  public FinderController(FinderService finderService) {
    this.finderService = finderService;
  }

  @GetMapping()
  public ResponseEntity<List<GitHubRepository>> getInfoFromGitHub(String username) {

    return ResponseEntity.ok().body(finderService.getRepositories(username));
  }
}
