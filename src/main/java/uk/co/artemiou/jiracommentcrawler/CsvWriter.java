package uk.co.artemiou.jiracommentcrawler;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CsvWriter {

    public void writeCommentsToCsv(String ticketId, List<JiraComment> comments, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) { // 'true' to append data
            String[] header = {"Ticket ID", "Comment ID", "Author Name", "Author Email", "Comment", "Created Date", "Updated Date"};

            // Check if file is empty to write header only once
            if (new java.io.File(filePath).length() == 0) {
                writer.writeNext(header);
            }

            for (JiraComment comment : comments) {
                String[] row = {
                        ticketId,
                        comment.getId(),
                        comment.getAuthorName(),
                        comment.getAuthorEmail(),
                        comment.getBody(),
                        comment.getCreatedDate(),
                        comment.getUpdatedDate()
                };
                writer.writeNext(row);
            }
        }
    }
}
