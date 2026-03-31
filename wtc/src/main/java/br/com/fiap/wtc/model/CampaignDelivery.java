package br.com.fiap.wtc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "campaign_deliveries")
public class CampaignDelivery {

    @Id
    private String id;
    private String campaignId;
    private String clientId;
    private String status;
    private LocalDateTime deliveredAt;
    private LocalDateTime readAt;

    public CampaignDelivery() {
    }

    public CampaignDelivery(String id, String campaignId, String clientId, String status,
                            LocalDateTime deliveredAt, LocalDateTime readAt) {
        this.id = id;
        this.campaignId = campaignId;
        this.clientId = clientId;
        this.status = status;
        this.deliveredAt = deliveredAt;
        this.readAt = readAt;
    }

    public String getId() {
        return id;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }
}