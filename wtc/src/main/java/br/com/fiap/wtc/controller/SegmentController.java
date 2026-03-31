package br.com.fiap.wtc.controller;

import br.com.fiap.wtc.model.Segment;
import br.com.fiap.wtc.service.SegmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/segments")
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @PostMapping
    public ResponseEntity<Segment> create(@RequestBody Segment segment) {
        Segment created = segmentService.create(segment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Segment>> findAll() {
        return ResponseEntity.ok(segmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Segment> findById(@PathVariable String id) {
        Segment segment = segmentService.findById(id);
        if (segment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(segment);
    }
}