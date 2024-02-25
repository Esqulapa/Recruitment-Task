package jzegzula.recruitment.task.gitHubUserFinder.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import jzegzula.recruitment.task.gitHubUserFinder.model.GitHubRepository;

public class GitHubRepoDeserializer extends JsonDeserializer<GitHubRepository> {
  @Override
  public GitHubRepository deserialize(JsonParser jsonParser, DeserializationContext ctxt)
      throws IOException {
    JsonNode node = jsonParser.readValueAsTree();

    GitHubRepository repo = new GitHubRepository();
    repo.setName(node.get("name").asText());
    repo.setOwner(node.get("owner").get("login").asText());

    return repo;
  }
}
