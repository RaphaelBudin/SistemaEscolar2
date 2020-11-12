/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author raphael
 */
public class SistemaAcademico {
    LinkedList<Aluno> llAluno;
    LinkedList<Professor> llProfessor;
    
    SistemaAcademico(){
        this.llAluno = new LinkedList<>();
        this.llProfessor = new LinkedList<>();
        //cadastrarAlunosTestes();
        //cadastrarProfessoresTestes();
        alunosPreCadastrados();
        professoresPreCadastrados();
    }
    
    static public void menu(){
        System.out.println("Opcções do Sistema: \n"
                + "1 - Cadastros\n"
                + "2 - Exibição\n"
                + "3 - Alterações\n"
                + "0 - Sair");
    }
    
    public void menuOpcao(){
        menu();
        int opcao = obterOpcaoInt(1, 3, 0);
        switch(opcao){
            case 0: break;
            case 1: cadastros();        break;
            case 2: menuExibirOpcao();  break;
            case 3: alteracoes();       break;
        }
    }
    
    public void menuCadastros(){
        System.out.println("Opções de cadastro: \n"
                + "1 - Aluno\n"
                + "2 - Professor\n"
                + "0 - Sair");
    }            
    
    public void cadastros(){
        menuCadastros();
        int opcao = obterOpcaoInt(1,2,0);
        switch(opcao){
            case 0: break;
            case 1: cadastrarPessoa(1); break;
            case 2: cadastrarPessoa(2); break;
        }
    }
    
    //Cadastra aluno (recebe 1) ou professor (recebe 2) de acordo com o parâmetro
    private void cadastrarPessoa(int pessoa){
        char sexo;
        String nome, tempSexo, dataNascimento, telefone, endereco;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Insira o nome: ");
        nome = sc.nextLine();
        System.out.println("Insira o sexo: ");
        tempSexo = sc.nextLine();
        sexo = tempSexo.charAt(0);
        //if (sc.hasNext() == true) sc.next();
        System.out.println("Insira data de nascimento: ");
        dataNascimento = sc.nextLine();
        System.out.println("Insira o telefone: ");
        telefone = sc.nextLine();
        System.out.println("Insira o endereço: ");
        endereco = sc.nextLine();
        
        if (pessoa == 1){
            int RA = llAluno.size()+1;
            Aluno a = new Aluno(nome,sexo,dataNascimento,telefone,endereco, RA);
            llAluno.add(a);
        }
        else if (pessoa == 2){
            int registro = llProfessor.size()+1;
            Professor p = new Professor(nome,sexo,dataNascimento,telefone,endereco, registro);
            llProfessor.add(p);
        }
        
    }
    
    public void cadastrarMateria(int pessoa){
        int opcao, pos = -1; 
        String disciplina;
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o nome da disciplina a ser cadastrada: ");
        disciplina = sc.nextLine();
        if (pessoa == 1){ //Aluno
            menuProcurarAluno();
            opcao = obterOpcaoInt(1,2,0);
            switch(opcao){
                case 0: return;
                case 1: 
                    pos = procurarAlunoRA_retornaPos(); 
                    if (pos == -1) {
                        System.out.println("Matéria não cadastrada. Aluno não encontrado");
                        return;
                    } 
                    break;
                case 2: 
                    pos = procurarAlunoNome_retornaPos(); 
                    if (pos == -1){
                        System.out.println("Matéria não cadastrada. Aluno não encontrado");
                        return;
                    }
                    break;
            }
        }
        else { //Professor 
            menuProcurarProfessor();
            opcao = obterOpcaoInt(1,2,0);
            switch(opcao){
                case 0: return;
                case 1: 
                    pos = procurarProfessorRegistro_retornaPos();
                    if (pos == -1){
                        System.out.println("Matéria não cadastrada. Professor não encontrado");
                        return;
                    }
                    break;
                case 2:
                    pos = procurarProfessorNomeSubstring_retornaPos();
                    if (pos == -1){
                        System.out.println("Matéria não cadastrada. Professor não encontardo");
                        return;
                    }
                    break;
            }
        }
        cadastrarMateria(pessoa,pos,disciplina);
    }
    
    //Cadastra matéria para (1 - aluno ou 2 - professor) de acordo com o parâmetro
    public void cadastrarMateria(int pessoa, int pos, String materia){
        if (pessoa == 1)    llAluno.get(pos).materiasCursadas.add(materia);
        else                llProfessor.get(pos).disciplinas.add(materia);
    }
    
    static public void menuExibirProfessores_Alunos(){
        System.out.println("Opções: \n"
                + "1 - Alunos\n"
                + "2 - Professores\n"
                + "0 - Sair");
    }
    
    static public void menuExibirProfessores_Alunos(String textoAdicional){
        System.out.println("Opções " + textoAdicional + ":\n"
                + "1 - Alunos\n"
                + "2 - Professores\n"
                + "0 - Sair");
    }
    
    public void menuExibirOpcao(){
        menuExibirProfessores_Alunos();
        int opcao = obterOpcaoInt(1,2,0);
        switch(opcao){
            case 0: return;
            case 1: menuExibirAlunosOpcao(); break;
            case 2: menuExibirProfessoresOpcao(); break;
            case 3: break;
        }
    }
    
    public void menuProcurarAluno(){
         System.out.println("Insira a opção desejada: \n"
                + "1 - Procurar por RA\n"
                + "2 - Procurar por Nome\n"
                + "0 - Sair");
    }
    
    public void menuProcurarAlunoOpcao(){
        menuProcurarAluno();
        int opcao = obterOpcaoInt(1,2,0);
        switch(opcao){
            case 0: return;
            case 1: procurarAlunoRA(); break;
            case 2: procurarAlunoNome(); break;
            
        }
    }
    
    public void alteracoes(){
        menuExibirProfessores_Alunos(" de cadastro");
        int opcao = obterOpcaoInt(1,2,0);
        switch(opcao){
            case 0: return;
            case 1: cadastrarMateria(1); break;
            case 2: cadastrarMateria(2); break;
        }
    }
    
    // ============================================================================
    // ================================== ALUNOS ==================================
    // ============================================================================
    
    //Menu com opções de exibir TODOS os alunos ou apenas UM
    static public void menuExibirAlunos(){
        System.out.println("Informa a opção desejada: \n"
                + "1 - Exibir TODOS os alunos\n"
                + "2 - Exibir UM aluno\n"
                + "0 - Sair\n");
    }
    //Exibe o menudos alunos, obtem a opção e direciona para próxima função
    public void menuExibirAlunosOpcao(){
        menuExibirAlunos();
        int opcao = obterOpcaoInt(1,2,0);
        switch(opcao){
            case 0: return;
            case 1: exibirAlunos(); break;
            case 2: procurarAluno(); break;
        }
    }
    
    public void exibirAlunos(){
        llAluno.stream().map((a) -> {
            System.out.println("RA: " + a.RA);
            return a;
        }).map((a) -> {
            System.out.println("Nome: " + a.nome);
            return a;
        }).map((a) -> {
            System.out.println("Sexo: " + a.sexo);
            return a;
        }).map((a) -> {
            System.out.println("Telefone: " + a.telefone);
            return a;
        }).map((a) -> {
            System.out.println("Endereco: " + a.endereco + "\n");
            return a;
        }).forEachOrdered((a) -> {
            exibirDisciplinas(a);
        });
    }
    
    public void exibirAluno(Aluno a){
        System.out.println("RA: " + a.RA);
        System.out.println("Nome: " + a.nome);
        System.out.println("Sexo: " + a.sexo);
        System.out.println("Telefone: " + a.telefone);
        System.out.println("Endereco: " + a.endereco + "\n");
        System.out.println("Disciplinas: ");
        exibirDisciplinas(a);
    }
    
    //Exibe as disciplinas dos alunos/professores (1-alunos/2-professores)
    public void exibirDisciplinas(Aluno a){
        for (String s : a.materiasCursadas){
            System.out.print(s);
            System.out.print(',');
        }
        System.out.println("");
    }
    
    public void procurarAluno(){
        int opcao = 0;
        do{
            System.out.println("Insira a opção desejada: \n"
                + "1 - Procurar por RA\n"
                + "2 - Procurar por Nome\n"
                + "0 - Sair");
            opcao = obterOpcaoInt(1,2,0);
        } while (opcao != 1 && opcao != 2 && opcao != 0);
        
        switch(opcao){
            case 0: return;
            case 1: procurarAlunoRA(); break;
            case 2: procurarAlunoNome(); break;
        }
    }
    
    //Exibe o aluno de acordo com a posição informada no parâmetro
    public void exibirAlunoPos(int pos){
        exibirAluno(llAluno.get(pos));
    }
    
    //(Interativa) Informar RA do aluno para pesquisa
    public void procurarAlunoRA(){
        Scanner sc = new Scanner(System.in);
        int RA, posRA;
        boolean achou = false;
        do{
            System.out.println("Informe o RA do aluno a ser pesquisado: ");
            RA = sc.nextInt();
            posRA = retornarPosicaoAlunoRA(RA);
            // ====================
            //Se o RA não existir...
            if (posRA == -1){
                System.out.println("RA informado não foi encontrado. Deseja tentar inserir outro RA?\n"
                        + "1 - Sim\n"
                        + "2 - Não");
                int opcao = sc.nextInt();
                if (opcao == 2) return; //Finaliza a função
            }
            // ====================
            else {
                exibirAlunoPos(posRA); 
                achou = true;
            }
        } while (!achou);
    }
    
    //(Interativo) Retorna posição do aluno na LL ou -1 se não achar
    public int procurarAlunoRA_retornaPos(){
        Scanner sc = new Scanner(System.in);
        int RA, posRA;
        boolean achou = false;
        do{
            System.out.println("Informe o RA do aluno a ser pesquisado: ");
            RA = sc.nextInt();
            posRA = retornarPosicaoAlunoRA(RA);
            // ====================
            //Se o RA não existir...
            if (posRA == -1){
                System.out.println("RA informado não foi encontrado. Deseja tentar inserir outro RA?\n"
                        + "1 - Sim\n"
                        + "2 - Não");
                int opcao = sc.nextInt();
                if (opcao == 2) achou=true; //Finaliza a função
            }
            // ====================
            else  
                return posRA;
        } while (!achou);
        return -1;
    }
    
    public void procurarAlunoNome(){
        Scanner sc = new Scanner(System.in);
        String nome;
        int pos, opcao;
        do{
            System.out.println("Informe o nome do aluno a ser pesquisado: ");
            nome = sc.next();
            pos = retornarPosicaoAlunoNomeSubstring(nome);
            if (pos == -1){
                System.out.println("Nome informado não foi encontrado. Deseja inserir outro nome?\n"
                        + "1 - Sim\n"
                        + "2 - Não");
                opcao = sc.nextInt();
                if (opcao == 2) return;
            }
            else{
                exibirAlunoPos(pos);
                return;
            }
        } while (true);
    }
    
    public int procurarAlunoNome_retornaPos(){
        Scanner sc = new Scanner(System.in);
        String nome;
        int pos, opcao;
        do{
            System.out.println("Informe o nome do aluno a ser pesquisado: ");
            nome = sc.next();
            pos = retornarPosicaoAlunoNomeSubstring(nome);
            if (pos == -1){
                System.out.println("Nome informado não foi encontrado. Deseja inserir outro nome?\n"
                        + "1 - Sim\n"
                        + "2 - Não");
                opcao = sc.nextInt();
                if (opcao == 2) break;
            }
            else
                return pos;
        } while (true);
        return -1;
    }
    
    //Retorna a posição em que o aluno especificado está na lista (ou -1 caso não seja encontrado)
    public int retornarPosicaoAlunoRA(int RA){
        int i = 0;
        for (Aluno a: llAluno){
            if (a.RA == RA)
                return i;
            i++;
        }
        return -1;
    }
    
    //Recebe o nome do aluno e procura por uma ocorrência exata desse nome na LL de Alunos
    public int retornarPosicaoAlunoNomeExato(String nome){
        int i = 0;
        for (Aluno a : llAluno){
            if (nome.equalsIgnoreCase(a.nome))
                return i;
            i++;
        }
        return -1;
    }
    
     //Recebe o nome do aluno e procura por uma ocorrência abrangente desse nome na LL de Alunos
    public int retornarPosicaoAlunoNomeSubstring(String nome){
        int i = 0;
        for (Aluno a : llAluno){
            if (nome.contains(nome))
                return i;
            i++;
        }
        return -1;
    }
    
    //Retorna um objeto do tipo Aluno (ou null caso não seja encontrado)
    public Aluno retornarAlunoRA(int RA){
        for (Aluno a: llAluno){
            if (a.RA == RA)
                return a;
        }
        return null;
    }
    // ================================================================================
    // ================================== FIM ALUNOS ==================================
    // ================================================================================
    
    // =================================================================================
    // ================================== PROFESSORES ==================================
    // =================================================================================
    
//Exibe um menu com opções de visualização dos professores
    public void menuExibirProfessor(){
        System.out.println("Informa a opção desejada: \n"
                + "1 - Exibir TODOS os Professores\n"
                + "2 - Exibir UM professor\n"
                + "0 - Sair\n");
    }
    
    //Exibe o menu, lê a opção selecionada e manda para a função de direção dos professores
    public void menuExibirProfessoresOpcao(){
        menuExibirProfessor();
        int opcao = obterOpcaoInt(1,2,0);
        direcionarFuncaoExibirProfessor(opcao);
    }
    
    public void menuProcurarProfessor(){
        System.out.println("Informe a opção desejada: \n"
                + "1 - Procurar por Registro: \n"
                + "2 - Procurar por Nome\n"
                + "0 - Sair");
    }
    
    public void direcionarFuncaoExibirProfessor(int opcao){
        switch(opcao){
            case 0 : return;
            case 1 : 
                exibirProfessores();
                break;
            case 2 :
                exibirProfessor();
                break;
        }
    }
    
    public void exibirProfessores(){
        llProfessor.stream().map((p) -> {
            System.out.println("RA: " + p.registro);
            return p;
        }).map((p) -> {
            System.out.println("Nome: " + p.nome);
            return p;
        }).map((p) -> {
            System.out.println("Sexo: " + p.sexo);
            return p;
        }).map((p) -> {
            System.out.println("Telefone: " + p.telefone);
            return p;
        }).forEachOrdered((p) -> {
            System.out.println("Endereco: " + p.endereco + "\n");
        });
    }
    
    public void exibirProfessor(){
        int opcao;
        do{
            System.out.println("Deseja procurar o professor por: \n"
                + "1 - Registro\n"
                + "2 - Nome\n"
                + "0 - Sair");
            opcao = obterOpcaoInt(1,2,0);
            if (opcao == 0) return;
        } while (opcao != 1 && opcao != 2);
        
        if (opcao == 1 ) procurarProfessorRegistro();
        if (opcao == 2 ) procurarProfessorNomeSubstring();
    }
    
    //Recebe um objeto do tipo Professor e exibe suas características
    public void exibirProfessor(Professor p){
        System.out.println("RA: " + p.registro);
        System.out.println("Nome: " + p.nome);
        System.out.println("Sexo: " + p.sexo);
        System.out.println("Telefone: " + p.telefone);
        System.out.println("Endereco: " + p.endereco + "\n");
    }
    
    //Recebe a posição em que o professor se encontra na LL e chama uma função para exibir
    public void exibirProfessorPos(int pos){
        exibirProfessor(llProfessor.get(pos));
    }
    
    //Interage com o usuário pedindo o número do registro para futuras pesquisas
    public void procurarProfessorRegistro(){
        Scanner sc = new Scanner(System.in);
        int registro, posicao, opcao;
        do{
            System.out.println("Insira o registro do professor: ");
            registro = sc.nextInt();
            posicao = retornaPosProfessorRegistro(registro);
            if (posicao == -1){
                System.out.println("Número de registro não encontrado. Gostaria de inserir outro número? :\n"
                    + "1 - Sim"
                    + "2 - Não"
                    + "0 - Sair");
                opcao = obterOpcaoInt(1,2,0);
                if (opcao != 1) return;
            }
            else{
                exibirProfessorPos(posicao);
                return;
            }
        } while (opcao == 1);
    }
    
    public int procurarProfessorRegistro_retornaPos(){
    Scanner sc = new Scanner(System.in);
        int registro, posicao, opcao;
        do{
            System.out.println("Insira o registro do professor: ");
            registro = sc.nextInt();
            posicao = retornaPosProfessorRegistro(registro);
            if (posicao == -1){
                System.out.println("Número de registro não encontrado. Gostaria de inserir outro número? :\n"
                    + "1 - Sim"
                    + "2 - Não"
                    + "0 - Sair");
                opcao = obterOpcaoInt(1,2,0);
                if (opcao != 1) break;
            }
            else
                return posicao;

        } while (opcao == 1);
        return -1;
    }
   
    //Função que recebe um int registro e retorna a posição em que achar o mesmo, ou -1 caso não encontre nada
    public int retornaPosProfessorRegistro(int registro){
        int i = 0;
        for (Professor p : llProfessor){
            if (registro == p.registro)
                return i;
            i++;
        }
        return -1;
    }
    
    //(Interativo) Obtém nome do professor para pesquisa
    public void procurarProfessorNomeSubstring(){
        Scanner sc = new Scanner(System.in);
        int posicao, opcao;
        String nome;
        do{
            System.out.println("Insira o nome do professor: ");
            nome = sc.next();
            posicao = retornaPosProfessorNome(nome);
            if (posicao == -1){
                System.out.println("Nome do professsor não encontrado. Gostaria de inserir outro nome? :\n"
                    + "1 - Sim\n"
                    + "2 - Não\n"
                    + "0 - Sair");
                opcao = obterOpcaoInt(1,2,0);
                if (opcao != 1) return;
            }
            else{
                exibirProfessorPos(posicao);
                return;
            }
        } while (opcao == 1);
    }
    
    public int procurarProfessorNomeSubstring_retornaPos(){
        Scanner sc = new Scanner(System.in);
        int posicao, opcao;
        String nome;
        do{
            System.out.println("Insira o nome do professor: ");
            nome = sc.next();
            posicao = retornaPosProfessorNome(nome);
            if (posicao == -1){
                System.out.println("Nome do professsor não encontrado. Gostaria de inserir outro nome? :\n"
                    + "1 - Sim\n"
                    + "2 - Não\n"
                    + "0 - Sair");
                opcao = obterOpcaoInt(1,2,0);
                if (opcao != 1) break;
            }
            else
                return posicao;
        } while (opcao == 1);
        return -1;
    }
    
    //Função que retorna -1 se não achar o professor por nome, ou um inteiro com a posição na LL do professor em questão
    public int retornaPosProfessorNome(String nome){
        int i = 0;
        for (Professor p : llProfessor){
            if (p.nome.contains(nome))
                return i;
            i++;
        }
        return -1;
    }
    
    // =====================================================================================
    // ================================== FIM PROFESSORES ==================================
    // =====================================================================================
    
    // =====================================================================================
    // ==================================   UTILITÁRIOS   ==================================
    // =====================================================================================
    
    //Obtêm e retorna opções entre os parâmetros mim,max,sair
    static public int obterOpcaoInt(int min, int max, int sair){
        Scanner sc = new Scanner(System.in);
        int opcao;
        do{
            opcao = sc.nextInt();
        } while ((opcao < min || opcao > max) && opcao != sair);
        return opcao;
    }
    
    //Exibe o texto  "[0-Não/1-Sim]" e retorna a opção escolhida
    static public int simNao(){
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        do{
            System.out.println("[0-Não/1-Sim]");
            opcao = sc.nextInt();
        } while (opcao != 0 && opcao != 1);
        return opcao;
    }
    
    // =======================================================================================
    // ================================== MÉTODOS P/ TESTES ==================================
    // =======================================================================================
    
    //Gera nomes aleatórios, sempre respeitando a regra: vogal depois consoante
    private String gerarNomeAleatorio(){
        int i,nrAleatorioVogal,nrAleatorioConsoante;		
	String vogal [] = {"a","e","i","o","u",}, vc = "", nome = "", consoante [] ={"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","w","x","y","z"}; 		
	Random random = new Random();								
            for(i = 0 ; i <= 8; i++){		
                nrAleatorioVogal = 0 + random.nextInt(4);//escolhe uma pos de 0 a 4		
		nrAleatorioConsoante = 0 + random.nextInt(19);//escolhe pos de 0 a 19  		   	
		vc = vogal[nrAleatorioVogal] + consoante[nrAleatorioConsoante];		
		nome += vc;	
            }
        return nome;
    }
    
    private void alunosPreCadastrados(){
        int i = 1;
        Aluno a1 = new Aluno("Raphael Budin", 'M', "21/09/1996", "4726-9545", "Av. Pedro Machado, 830 - Casa 31", i++);
        Aluno a2 = new Aluno("Deborah Budin", 'F', "31/10/1998", "4726-9545", "Av. Pedro Machado, 830 - Casa 31", i++);
        Aluno a3 = new Aluno("Agnolia Amaral Vaz Budin", 'F', "26/12/1966", "4726-9545", "Av. Pedro Machado, 830 - Casa 31", i++);
        Aluno a4 = new Aluno("Silvio Budin", 'M', "07/04/1965", "4726-9545", "Av. Pedro Machado, 830 - Casa 31", i++);
        Aluno a5 = new Aluno("Thirza Siqueira de Souza", 'F', "14/10/1999", "(11)9 9999-8888", "Rua Régis Plínio Batalha, 545", i++);
        llAluno.add(a1);
        llAluno.add(a2);
        llAluno.add(a3);
        llAluno.add(a4);
        llAluno.add(a5);
    }
    
    private void professoresPreCadastrados(){
        int i = 0;
        Professor a1 = new Professor("Raphael Budin", 'M', "21/09/1996", "4726-9545", "Av. Pedro Machado, 830 - Casa 31", i++);
        Professor a2 = new Professor("Deborah Budin", 'F', "31/10/1998", "4726-9545", "Av. Pedro Machado, 830 - Casa 31", i++);
        Professor a3 = new Professor("Agnolia Amaral Vaz Budin", 'F', "26/12/1966", "4726-9545", "Av. Pedro Machado, 830 - Casa 31", i++);
        Professor a4 = new Professor("Silvio Budin", 'M', "07/04/1965", "4726-9545", "Av. Pedro Machado, 830 - Casa 31", i++);
        Professor a5 = new Professor("Thirza Siqueira de Souza", 'F', "14/10/1999", "(11)9 9999-8888", "Rua Régis Plínio Batalha, 545", i++);
        llProfessor.add(a1);
        llProfessor.add(a2);
        llProfessor.add(a3);
        llProfessor.add(a4);
        llProfessor.add(a5);
    }
    
    //Cadastra uma lista de alunos gerados aleatóriamente para testes básicos do sistema
    private void cadastrarAlunosTestes(){
        int numAlunos = 100;
        String nome = "Raphael", telefone = "(11)4799-9999", endereco = "Av. Alguma coisa", dataNascimento = "21/09/1996";
        char sexo = 'M';
        Aluno a = new Aluno(nome,sexo,dataNascimento,telefone,endereco, 1);
        llAluno.add(a);
        
        Random gerador = new Random();
        
        for (int i = 2; i <= numAlunos; ++i){
            a.RA = i;
            a.dataNascimento =  Integer.toString(gerador.nextInt(31+1))+1 
                                + '/' + Integer.toString(gerador.nextInt(12+1)+1)
                                + '/' + Integer.toString(gerador.nextInt(2020+1)+1);
            a.sexo = (gerador.nextInt(1+1) == 0) ? 'F' : 'M';
            a.telefone = "(11)" + Integer.toString(gerador.nextInt(999+1)+1000) + '-' + Integer.toString(gerador.nextInt(9999)+1);
            a.nome = gerarNomeAleatorio();
            a.endereco = gerarNomeAleatorio() + ' ' + gerarNomeAleatorio() + ' ' + gerarNomeAleatorio();
            llAluno.add(a);
        }
    }
    
    //Cadastra uma lista de professores gerados aleatóriamente para testes básicos do sistema
    private void cadastrarProfessoresTestes(){
        int numProfessores = 100;
        String nome = "Sofia", telefone = "(11)4799-5555", endereco = "Av. Libano Saraiva", dataNascimento = "15/1/2000";
        char sexo = 'F';
        Professor p = new Professor(nome,sexo,dataNascimento,telefone,endereco, 1);
        llProfessor.add(p);
        
        Random gerador = new Random();
        
        for (int i = 2; i <= numProfessores; ++i){
            p.registro = i;
            p.dataNascimento =  Integer.toString(gerador.nextInt(31+1))+1 
                                + '/' + Integer.toString(gerador.nextInt(12+1)+1)
                                + '/' + Integer.toString(gerador.nextInt(2020+1)+1);
            p.sexo = (gerador.nextInt(1+1) == 0) ? 'F' : 'M';
            p.telefone = "(11)" + Integer.toString(gerador.nextInt(9999+1)+4000) + '-' + Integer.toString(gerador.nextInt(9999)+1);
            p.nome = gerarNomeAleatorio();
            p.endereco = gerarNomeAleatorio() + ' ' + gerarNomeAleatorio() + ' ' + gerarNomeAleatorio();
            llProfessor.add(p);
        }
    }
    
    // ========================================================================================
    // ================================== FIM MÉTODOS TESTES ==================================
    // ========================================================================================

}
