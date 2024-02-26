package jzegzula.recruitment.task.gitHubUserFinder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class GitHubRepository {

  private String name;
  private Boolean fork;
  private Owner owner;
  private List<Branch> branches;

  public GitHubRepository(String name, Boolean fork, Owner owner, List<Branch> branches) {
    this.name = name;
    this.fork = fork;
    this.owner = owner;
    this.branches = branches;
  }

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

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public List<Branch> getBranches() {
    return branches;
  }

  public void setBranches(List<Branch> branches) {
    this.branches = branches;
  }
}
