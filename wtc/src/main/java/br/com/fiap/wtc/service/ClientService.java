package br.com.fiap.wtc.service;

import br.com.fiap.wtc.dto.request.ClientRequest;
import br.com.fiap.wtc.dto.request.NoteRequest;
import br.com.fiap.wtc.dto.response.ClientResponse;
import br.com.fiap.wtc.exception.ResourceNotFoundException;
import br.com.fiap.wtc.model.Client;
import br.com.fiap.wtc.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponse create(ClientRequest request) {
        Client client = new Client();
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        client.setTags(request.getTags() != null ? request.getTags() : new ArrayList<>());
        client.setScore(request.getScore() != null ? request.getScore() : 0);
        client.setStatus(request.getStatus() != null ? request.getStatus() : "ACTIVE");
        client.setNotes(new ArrayList<>());
        client.setCreatedAt(LocalDateTime.now());
        return toResponse(clientRepository.save(client));
    }

    public List<ClientResponse> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ClientResponse findById(String id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
        return toResponse(client);
    }

    public ClientResponse update(String id, ClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        client.setTags(request.getTags());
        client.setScore(request.getScore());
        client.setStatus(request.getStatus());
        return toResponse(clientRepository.save(client));
    }

    public void delete(String id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente", id);
        }
        clientRepository.deleteById(id);
    }

    public ClientResponse addNote(String id, NoteRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
        if (client.getNotes() == null) {
            client.setNotes(new ArrayList<>());
        }
        Client.Note note = new Client.Note(request.getText(), LocalDateTime.now());
        client.getNotes().add(note);
        return toResponse(clientRepository.save(client));
    }

    public List<ClientResponse> search(String name, String status, String tag, Integer minScore) {
        return clientRepository.findAll()
                .stream()
                .filter(c -> name == null || c.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(c -> status == null || status.equals(c.getStatus()))
                .filter(c -> tag == null || (c.getTags() != null && c.getTags().contains(tag)))
                .filter(c -> minScore == null || (c.getScore() != null && c.getScore() >= minScore))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ClientResponse toResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                client.getTags(),
                client.getScore(),
                client.getStatus()
        );
    }
}
