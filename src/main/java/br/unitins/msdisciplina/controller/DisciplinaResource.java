package br.unitins.msdisciplina.controller;

import br.unitins.msdisciplina.model.Disciplina;
import br.unitins.msdisciplina.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {

    @Autowired
    private DisciplinaService service;

    @GetMapping
    public List<Disciplina> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscar(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Disciplina> criar(@RequestBody Disciplina d) {
        return ResponseEntity.status(201).body(service.salvar(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(
            @PathVariable Long id, @RequestBody Disciplina dados) {
        return service.buscar(id).map(d -> {
            d.setNome(dados.getNome());
            d.setProfessor(dados.getProfessor());
            d.setCargaHoraria(dados.getCargaHoraria());
            return ResponseEntity.ok(service.salvar(d));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
