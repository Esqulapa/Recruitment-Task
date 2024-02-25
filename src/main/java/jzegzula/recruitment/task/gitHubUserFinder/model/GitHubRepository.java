package jzegzula.recruitment.task.gitHubUserFinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import jzegzula.recruitment.task.gitHubUserFinder.deserializer.GitHubRepoDeserializer;

@JsonDeserialize(using = GitHubRepoDeserializer.class)
public class GitHubRepository {

  private String name;
  @JsonIgnore private boolean fork;
  private String owner;
  private List<Branch> branches;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isFork() {
    return fork;
  }

  public void setFork(boolean fork) {
    this.fork = fork;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public List<Branch> getBranches() {
    return branches;
  }

  public void setBranches(List<Branch> branches) {
    this.branches = branches;
  }
}
