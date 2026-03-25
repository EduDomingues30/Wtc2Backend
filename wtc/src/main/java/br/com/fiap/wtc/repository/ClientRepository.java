package br.com.fiap.wtc.repository;

import br.com.fiap.wtc.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    // Busca clientes pelo status (ACTIVE / INACTIVE)
    List<Client> findByStatus(String status);

    // Busca clientes que possuem determinada tag
    List<Client> findByTagsContaining(String tag);

    // Busca clientes com score maior ou igual ao valor informado
    List<Client> findByScoreGreaterThanEqual(Integer minScore);

    // Busca por nome (case insensitive) — útil para o campo de busca do app
    List<Client> findByNameContainingIgnoreCase(String name);
}
