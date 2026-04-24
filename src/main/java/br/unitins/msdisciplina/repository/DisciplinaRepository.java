package br.unitins.msdisciplina.repository;

import br.unitins.msdisciplina.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    // O Spring Data JPA gera automaticamente:
    // findAll(), findById(), save(), deleteById() e outros.
}
