package br.com.fiap.wtc.repository;

import br.com.fiap.wtc.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Busca usuário pelo email — usado no login
    Optional<User> findByEmail(String email);

    // Verifica se já existe um usuário com esse email — usado no registro
    boolean existsByEmail(String email);
}
