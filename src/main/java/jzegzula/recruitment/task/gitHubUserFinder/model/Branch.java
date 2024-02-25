package jzegzula.recruitment.task.gitHubUserFinder.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jzegzula.recruitment.task.gitHubUserFinder.deserializer.BranchDeserializer;

@JsonDeserialize(using = BranchDeserializer.class)
public class Branch {

  private String name;
  private String sha;

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
