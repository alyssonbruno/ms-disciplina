package br.unitins.msdisciplina.service;

import br.unitins.msdisciplina.model.Disciplina;
import br.unitins.msdisciplina.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repo;

    public List<Disciplina> listar() {
        return repo.findAll();
    }

    public Optional<Disciplina> buscar(Long id) {
        return repo.findById(id);
    }

    public Disciplina salvar(Disciplina d) {
        return repo.save(d);
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }
}
