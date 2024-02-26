package jzegzula.recruitment.task.gitHubUserFinder.githubRepositoryFinder;

import jzegzula.recruitment.task.gitHubUserFinder.model.GitHubRepository;
import jzegzula.recruitment.task.gitHubUserFinder.model.dto.GitHubRepositoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/finder")
public class GitHubRepositoryFinderController {

  private final GitHubRepositoryFinderService gitHubRepositoryFinderService;

  public GitHubRepositoryFinderController(
      GitHubRepositoryFinderService gitHubRepositoryFinderService) {
    this.gitHubRepositoryFinderService = gitHubRepositoryFinderService;
  }

  @GetMapping()
  public ResponseEntity<List<GitHubRepositoryDto>> getInfoFromGitHub(String username) {
    List<GitHubRepository> repositories = gitHubRepositoryFinderService.getRepositories(username);
    List<GitHubRepositoryDto> repositoryDto =
        repositories.stream().map(GitHubRepositoryDto::new).collect(Collectors.toList());

    return ResponseEntity.ok().body(repositoryDto);
  }
}
