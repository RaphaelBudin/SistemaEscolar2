/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Scanner;

/**
 *
 * @author raphael
 */
public class Main {
    public static void main(String[] args){
        SistemaAcademico sistema = new SistemaAcademico();
        do{
            sistema.menuOpcao();
        } while (continuar()== 1);
    }
    
    //Método que verifica se o usuário quer continuar executando o programa ou não
    static private int continuar(){
        int opcao = 0;
        do{
            System.out.println("Deseja continuar? [0-Não/1-Sim]: ");
            Scanner sc = new Scanner(System.in);
            //if (sc.hasNext()) sc.next();
            opcao = sc.nextInt();
        }while(opcao != 1 && opcao != 0);
        return opcao;
    }
}
