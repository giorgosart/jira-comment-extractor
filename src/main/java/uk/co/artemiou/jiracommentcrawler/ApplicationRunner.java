package uk.co.artemiou.jiracommentcrawler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final JiraService jiraService;
    private final CsvWriter csvWriter;

    public ApplicationRunner(JiraService jiraService, CsvWriter csvWriter) {
        this.jiraService = jiraService;
        this.csvWriter = csvWriter;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> ticketIds = List.of("TICKET-1", "TICKET-2", "TICKET-3");
        String filePath = "comments.csv";

        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            csvFile.createNewFile();
            System.out.println("Created new file: " + filePath);
        }

        for (String ticketId : ticketIds) {
            List<JiraComment> comments = jiraService.getComments(ticketId);
            csvWriter.writeCommentsToCsv(ticketId, comments, filePath);
        }

        System.out.println("Comments written to " + filePath);
    }
}
