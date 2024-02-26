package jzegzula.recruitment.task.gitHubUserFinder.model.dto;

import jzegzula.recruitment.task.gitHubUserFinder.model.Branch;
import jzegzula.recruitment.task.gitHubUserFinder.model.GitHubRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GitHubRepositoryDto {
  private String name;
  private String owner;
  private List<BranchDto> branches;

  public GitHubRepositoryDto(GitHubRepository repository) {
    this.name = repository.getName();
    this.owner = repository.getOwner().getLogin();
    this.branches = branchesToBranchesDTOConverter(repository.getBranches());
  }

  private List<BranchDto> branchesToBranchesDTOConverter(List<Branch> branches) {
    return branches.stream().map(BranchDto::new).collect(Collectors.toList());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public List<BranchDto> getBranches() {
    return branches;
  }

  public void setBranches(List<BranchDto> branches) {
    this.branches = branches;
  }
}
