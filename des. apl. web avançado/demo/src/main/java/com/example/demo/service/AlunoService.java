package com.example.demo.service;

import com.example.demo.entity.Aluno;
import com.example.demo.entity.request.AlunoRequest;
import com.example.demo.entity.response.AlunoResponse;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service

public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ConvertUtils convertUtils;

    public AlunoService(AlunoRepository alunoRepository, ConvertUtils convertUtils) {
        this.alunoRepository = alunoRepository;
        this.convertUtils = convertUtils;
    }

    public void salvar(@RequestBody AlunoRequest request){
        this.alunoRepository.save(
                (Aluno)convertUtils.convertRequestToEntity(request, Aluno.class));
    }

    public List<AlunoResponse> listar() {
        return (List<AlunoResponse>) convertUtils
                .convertToListResponse(this.alunoRepository.findAll(), AlunoResponse.class);

    }

    public AlunoResponse buscarPorId(Long id){
        return (AlunoResponse) convertUtils.convertEntityToResponse(
                this.alunoRepository.findById(id).get(), AlunoResponse.class);
    }

    public void atualizar(@RequestBody AlunoRequest resquest, Long id){
        var alunoDb = this.alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));

        var alunoAtualizado =
                (Aluno)convertUtils.convertRequestToEntity(resquest, alunoDb.getClass());

        alunoAtualizado.setId(id);

        this.alunoRepository.save(alunoAtualizado);
    }

    public void excluir(Long id){
        this.alunoRepository.deleteById(id);
    }
}
