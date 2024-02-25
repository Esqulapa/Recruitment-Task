package jzegzula.recruitment.task.gitHubUserFinder.finder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jzegzula.recruitment.task.gitHubUserFinder.exception.UserNotFoundException;
import jzegzula.recruitment.task.gitHubUserFinder.model.Branch;
import jzegzula.recruitment.task.gitHubUserFinder.model.GitHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class FinderService {

  private final String GITHUB_API_URL = "https://api.github.com/users/";

  @Autowired private RestTemplate restTemplate;

  public List<GitHubRepository> getRepositories(String username) {
    List<GitHubRepository> repositories = new ArrayList<>();

    try {
      //        ResponseEntity<GitHubRepository[]> response =
      // restTemplate.getForEntity(GITHUB_API_URL + username + "/repos", GitHubRepository[].class);
      ResponseEntity<GitHubRepository[]> response =
          restTemplate.exchange(
              GITHUB_API_URL + username + "/repos",
              HttpMethod.GET,
              getValidToken(),
              GitHubRepository[].class);
      GitHubRepository[] repos = response.getBody();

      if (repos != null) {
        for (GitHubRepository repo : repos) {
          if (!repo.isFork()) {
            repo.setBranches(getBranches(repo.getName(), username));
            repositories.add(repo);
          }
        }
      }
    } catch (HttpClientErrorException.NotFound e) {
      throw new UserNotFoundException(e.getStatusCode().value(), "User not found");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return repositories;
  }

  private List<Branch> getBranches(String repoName, String username) {
    String url = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
    //        ResponseEntity<Branch[]> response = restTemplate.getForEntity(url, Branch[].class);
    ResponseEntity<Branch[]> response =
        restTemplate.exchange(url, HttpMethod.GET, getValidToken(), Branch[].class);
    Branch[] branches = response.getBody();
    List<Branch> branchList = new ArrayList<>();
    if (branches != null) {
      branchList.addAll(Arrays.asList(branches));
    }
    return branchList;
  }

  public static HttpEntity<String> getValidToken() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer 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");
    HttpEntity<String> entity = new HttpEntity<>(headers);
    return entity;
  }
}
