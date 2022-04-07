package com.example.demo.entity.request;

import lombok.Data;

@Data
public class AlunoRequest {
    private Long id;
    private String matricula;
    private String nome;
    private String cpf;
    private String email;
    private String fone;
}