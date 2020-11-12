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
public class Professor extends Pessoa {
    int registro;
    LinkedList<String> disciplinas;
    
    Professor(String nome, char sexo, String dataNascimento, int registro){
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = new String();
        this.endereco = new String();
        //PROFESSOR
        this.registro = registro;
        this.disciplinas = new LinkedList<String>();
    }
    Professor(String nome, char sexo, String dataNascimento, String telefone, int registro){
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = new String();
        //PROFESSOR
        this.registro = registro;
        this.disciplinas = new LinkedList<String>();
    }
    Professor(String nome, char sexo, String dataNascimento, String telefone, String endereco, int registro){
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        //PROFESSOR
        this.registro = registro;
        this.disciplinas = new LinkedList<String>();
    }
    
}
