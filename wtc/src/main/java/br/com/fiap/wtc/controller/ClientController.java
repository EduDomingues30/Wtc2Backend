package br.com.fiap.wtc.controller;

import br.com.fiap.wtc.dto.request.ClientRequest;
import br.com.fiap.wtc.dto.request.NoteRequest;
import br.com.fiap.wtc.dto.response.ClientResponse;
import br.com.fiap.wtc.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable String id,
                                                 @RequestBody ClientRequest request) {
        return ResponseEntity.ok(clientService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/notes")
    public ResponseEntity<ClientResponse> addNote(@PathVariable String id,
                                                  @RequestBody NoteRequest request) {
        return ResponseEntity.ok(clientService.addNote(id, request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) Integer minScore) {
        return ResponseEntity.ok(clientService.search(name, status, tag, minScore));
    }
}
