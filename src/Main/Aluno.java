/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.util.LinkedList;

/**
 *
 * @author raphael
 */
public class Aluno extends Pessoa{
    int RA;
    LinkedList<String> materiasCursadas;
    
    //CONSTRUTORES
    Aluno(String nome, char sexo, String dataNascimento, int RA){
        //PESSOA
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = new String();
        this.endereco = new String();
        //ALUNO
        this.RA = RA;
        this.materiasCursadas = new LinkedList<>();
        
    }
    Aluno(String nome, char sexo, String dataNascimento, String telefone, int RA){
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = new String();
        //ALUNO
        this.RA = RA;
        this.materiasCursadas = new LinkedList<>();
        
    }
    Aluno(String nome, char sexo, String dataNascimento, String telefone, String endereco, int RA){
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        //ALUNO
        this.RA = RA;
        this.materiasCursadas = new LinkedList<>();
    }
}
