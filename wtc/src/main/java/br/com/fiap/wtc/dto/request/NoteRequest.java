package br.com.fiap.wtc.dto.request;

public class NoteRequest {
    private String text;

    public NoteRequest() {}

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
