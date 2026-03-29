package com.UniespTech.service;

import com.UniespTech.model.Aluno;
import com.UniespTech.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno salvar(Aluno aluno) {

        if (aluno.getNome() == null || aluno.getNome().isEmpty()) {
            throw new RuntimeException("Nome não pode ser vazio");
        }

        if (aluno.getCpf().length() != 11) {
            throw new RuntimeException("CPF deve ter 11 dígitos");
        }

        if (repository.existsByCpf(aluno.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        return repository.save(aluno);
    }

    public List<Aluno> listar() {
        return repository.findAll();
    }

    public void deletarTodos() {
        repository.deleteAll();
    }
}