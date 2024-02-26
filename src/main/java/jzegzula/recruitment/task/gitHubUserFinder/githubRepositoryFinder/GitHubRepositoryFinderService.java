package jzegzula.recruitment.task.gitHubUserFinder.githubRepositoryFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jzegzula.recruitment.task.gitHubUserFinder.model.Branch;
import jzegzula.recruitment.task.gitHubUserFinder.model.GitHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GitHubRepositoryFinderService {

  private final String GITHUB_API_URL = "https://api.github.com/users/";
  private final String GITHUB_API_USER_REPOSITORY_URL = "https://api.github.com/repos/";

  private final String GITHUB_TOKEN;

  @Autowired private final RestTemplate restTemplate;

  public GitHubRepositoryFinderService(
      @Value("${gitHubToken}") String GITHUB_TOKEN, RestTemplate restTemplate) {
    this.GITHUB_TOKEN = GITHUB_TOKEN;
    this.restTemplate = restTemplate;
  }

  public List<GitHubRepository> getRepositories(String username)
      throws HttpClientErrorException.NotFound {

    List<GitHubRepository> repositories = new ArrayList<>();

    ResponseEntity<GitHubRepository[]> response =
        restTemplate.exchange(
            buildApiUrlForGitHubRepository(username),
            HttpMethod.GET,
            getHeaderWithAuthorization(),
            GitHubRepository[].class);
    GitHubRepository[] repos = response.getBody();

    if (repos != null) {
      for (GitHubRepository repo : repos) {
        if (!repo.isFork()) {
          repo.setBranches(collectBranchesFromGitHubRepository(repo.getName(), username));
          repositories.add(repo);
        }
      }
    }

    return repositories;
  }

  private List<Branch> collectBranchesFromGitHubRepository(String repositoryName, String username) {
    ResponseEntity<Branch[]> response =
        restTemplate.exchange(
            buildApiUrlForGitHubRepositoryBranchesUrl(username, repositoryName),
            HttpMethod.GET,
            getHeaderWithAuthorization(),
            Branch[].class);
    Branch[] branches = response.getBody();
    List<Branch> branchList = new ArrayList<>();
    if (branches != null) {
      branchList.addAll(Arrays.asList(branches));
    }
    return branchList;
  }

  private String buildApiUrlForGitHubRepository(String username) {
    return UriComponentsBuilder.fromHttpUrl(GITHUB_API_URL)
        .path(username)
        .path("/repos")
        .toUriString();
  }

  public String buildApiUrlForGitHubRepositoryBranchesUrl(String username, String repositoryName) {
    return UriComponentsBuilder.fromHttpUrl(GITHUB_API_USER_REPOSITORY_URL)
        .path(username)
        .path("/")
        .path(repositoryName)
        .path("/branches")
        .toUriString();
  }

  private HttpEntity<String> getHeaderWithAuthorization() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + GITHUB_TOKEN);
    return new HttpEntity<>(headers);
  }
}
