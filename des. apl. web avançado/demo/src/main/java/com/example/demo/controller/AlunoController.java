package com.example.demo.controller;

import com.example.demo.entity.request.AlunoRequest;
import com.example.demo.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody AlunoRequest request){
        this.alunoService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(this.alunoService.listar());
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(this.alunoService.buscarPorId(id));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        this.alunoService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody AlunoRequest request, @PathVariable Long id){
        this.alunoService.atualizar(request, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
