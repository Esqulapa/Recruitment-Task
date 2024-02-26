package jzegzula.recruitment.task.gitHubUserFinder.model;

public class Commit {
  private String sha;

  public Commit(String sha) {
    this.sha = sha;
  }

  public String getSha() {
    return sha;
  }

  public void setSha(String sha) {
    this.sha = sha;
  }
}
