package jzegzula.recruitment.task.gitHubUserFinder.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import jzegzula.recruitment.task.gitHubUserFinder.model.Branch;

import java.io.IOException;

public class BranchDeserializer extends JsonDeserializer<Branch> {
  @Override
  public Branch deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    JsonNode node = jsonParser.readValueAsTree();
    String name = node.get("name").asText();
    String sha = node.get("commit").get("sha").asText();

    Branch branch = new Branch();
    branch.setName(name);
    branch.setSha(sha);

    return branch;
  }
}
