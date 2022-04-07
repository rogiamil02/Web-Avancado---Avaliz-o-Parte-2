package com.example.demo.entity.response;

import lombok.Data;

@Data
public class AlunoResponse {
    private Long id;
    private String matricula;
    private String nome;
    private String email;
}
