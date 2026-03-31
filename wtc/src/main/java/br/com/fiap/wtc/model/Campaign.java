package br.com.fiap.wtc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "campaigns")
public class Campaign {

    @Id
    private String id;
    private String title;
    private String content;
    private String segmentId;
    private String createdBy;
    private LocalDateTime createdAt;
    private List<String> actionButtons;

    public Campaign() {
    }

    public Campaign(String id, String title, String content, String segmentId, String createdBy,
                    LocalDateTime createdAt, List<String> actionButtons) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.segmentId = segmentId;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.actionButtons = actionButtons;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<String> getActionButtons() {
        return actionButtons;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setActionButtons(List<String> actionButtons) {
        this.actionButtons = actionButtons;
    }
}