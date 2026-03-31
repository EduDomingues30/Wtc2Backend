package br.com.fiap.wtc.service;

import br.com.fiap.wtc.model.Segment;
import br.com.fiap.wtc.repository.SegmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SegmentService {

    private final SegmentRepository segmentRepository;

    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public Segment create(Segment segment) {
        segment.setId(null);
        segment.setCreatedAt(LocalDateTime.now());
        return segmentRepository.save(segment);
    }

    public List<Segment> findAll() {
        return segmentRepository.findAll();
    }

    public Segment findById(String id) {
        return segmentRepository.findById(id).orElse(null);
    }
}