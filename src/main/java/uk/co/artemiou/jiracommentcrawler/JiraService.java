package uk.co.artemiou.jiracommentcrawler;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class JiraService {

    @Value("${jira.api.url}")
    private String jiraApiUrl;

    @Value("${jira.jsessionid}")
    private String jsessionId;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<JiraComment> getComments(String ticketId) {
        String url = String.format("%s/%s/comment", jiraApiUrl, ticketId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "JSESSIONID=" + jsessionId);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                JsonNode.class
        );

        JsonNode body = response.getBody();
        List<JiraComment> comments = new ArrayList<>();

        if (body != null && body.has("comments")) {
            for (JsonNode commentNode : body.get("comments")) {
                JiraComment comment = new JiraComment();
                comment.setId(commentNode.get("id").asText());
                comment.setAuthorName(commentNode.get("author").get("displayName").asText());
                comment.setAuthorEmail(commentNode.get("author").get("emailAddress").asText());
                comment.setBody(commentNode.get("body").asText());
                comment.setCreatedDate(commentNode.get("created").asText());
                comment.setUpdatedDate(commentNode.get("updated").asText());
                comments.add(comment);
            }
        }
        return comments;
    }
}
