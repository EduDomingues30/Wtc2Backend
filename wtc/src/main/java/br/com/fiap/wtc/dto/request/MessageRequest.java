package br.com.fiap.wtc.dto.request;

import java.util.List;

public class MessageRequest {
    private String fromUserId;
    private String toUserId;
    private String content;
    private String messageType;
    private List<ActionRequest> actions;

    public static class ActionRequest {
        private String label;
        private String type;
        private String value;
        public String getLabel() { return label; }
        public void setLabel(String label) { this.label = label; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }

    public String getFromUserId() { return fromUserId; }
    public void setFromUserId(String fromUserId) { this.fromUserId = fromUserId; }
    public String getToUserId() { return toUserId; }
    public void setToUserId(String toUserId) { this.toUserId = toUserId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getMessageType() { return messageType; }
    public void setMessageType(String messageType) { this.messageType = messageType; }
    public List<ActionRequest> getActions() { return actions; }
    public void setActions(List<ActionRequest> actions) { this.actions = actions; }
}