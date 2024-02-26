package jzegzula.recruitment.task.gitHubUserFinder.model.dto;

import jzegzula.recruitment.task.gitHubUserFinder.model.Branch;
import jzegzula.recruitment.task.gitHubUserFinder.model.Commit;

public class BranchDto {

  private String name;
  private String sha;

  public BranchDto(Branch branch) {
    this.name = branch.getName();
    this.sha = branch.getCommit().getSha();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSha() {
    return sha;
  }

  public void setSha(String sha) {
    this.sha = sha;
  }
}
