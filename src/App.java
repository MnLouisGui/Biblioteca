import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

public class App {
    public static void main(String[] args) throws Exception {
        Boolean on=true;
        int qtd_usuario=0;
        int login=0;
    
        Usuario usuario1=new Usuario();
        usuario1.setLogin(0);
        Usuario usuario2=new Usuario();
        Exemplar exemplar=new Exemplar();
        Livro livro1=new Livro();
        Livro livro2=new Livro();
        Emprestimo emprestimo=new Emprestimo();
        Curso curso1=new Curso("Informática");
        Curso curso2=new Curso("Meio ambiente");

        Scanner scanner=new Scanner(System.in);
        System.out.println("Biblioteca");
        while (on==true) {
            System.out.println("__________________________");
            System.out.println("---------- Menu ----------");
            System.out.println("1.Cadastrar Usuário\n2.Login\n3.Sair");
            int op=scanner.nextInt();
            switch (op) {
                case 1:
                    Curso curso=null;
                    System.out.println("__________________________");
                    System.out.println("-------- Cadastro --------");
                    System.out.println("Nome do Usuário:");
                    String nome=scanner.nextLine(); nome=scanner.nextLine();
                    System.out.println("Número de matricula:");
                    String matricula=scanner.nextLine();
                    System.out.println("Senha:");
                    String senha=scanner.nextLine();
                    System.out.println("Qual curso?\n1.Informática\n2.Meio Ambiente");
                    op=scanner.nextInt();
                    switch (op) {
                        case 1:
                            curso=curso1;
                            break;
                        case 2:
                            curso=curso2;
                            break;
                        default:
                            break;
                    }
                    if(qtd_usuario==0){
                        usuario1.cadastrar(nome, matricula, senha, curso);
                        usuario1.exibir();
                    }if(qtd_usuario==1){
                        usuario2.cadastrar(nome, matricula, senha, curso);
                        usuario2.exibir();
                    }
                    break;
                case 2:
                    System.out.println("__________________________");
                    System.out.println("---------- Login ---------");
                    System.out.println("Matricula:");
                    matricula=scanner.nextLine(); matricula=scanner.nextLine(); 
                    String m =usuario1.getMatricula();
                    System.out.println(matricula==m);
                    System.out.println("Senha:");
                    senha=scanner.nextLine();

                    if(usuario1.verificar(matricula, senha)){
                        login=1;
                    }
                    break;
                case 3:
                    on=false;
                    break;
                default:
                    break;
            }
            while (login==1) {
                System.out.println("logado");
                op=scanner.nextInt();
            }
        }
    }
}
