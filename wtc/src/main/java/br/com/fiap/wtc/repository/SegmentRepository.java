package br.com.fiap.wtc.repository;

import br.com.fiap.wtc.model.Segment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends MongoRepository<Segment, String> {
}