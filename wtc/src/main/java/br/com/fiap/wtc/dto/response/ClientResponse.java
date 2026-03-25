package br.com.fiap.wtc.dto.response;

import java.util.List;

public class ClientResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private List<String> tags;
    private Integer score;
    private String status;

    public ClientResponse() {}

    public ClientResponse(String id, String name, String email, String phone,
                          List<String> tags, Integer score, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.tags = tags;
        this.score = score;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
