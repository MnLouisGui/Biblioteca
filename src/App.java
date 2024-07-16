import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

public class App {
    public static void main(String[] args) throws Exception {
        Boolean on=true;
        int qtd_usuario=0;
    
        Usuario usuario1=new Usuario();
        usuario1.setLogin(0);
        Usuario usuario2=new Usuario();
        Exemplar exemplar=new Exemplar();
        Livro livro1=new Livro("A volta dos que não foram", "Frederico da Costa", "2077");
        Livro livro2=new Livro("Lindos cachos de um careca", "Carlos Antônio", "1999");
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
                    System.out.println("Senha:");
                    senha=scanner.nextLine();

                    if(qtd_usuario==0){
                        if(usuario1.verificar(matricula, senha)){
                            usuario1.setLogin(1);
                        }
                    }if(qtd_usuario==1){
                        if(usuario2.verificar(matricula, senha)){
                            usuario2.setLogin(2);
                        }
                    }
                    break;
                case 3:
                    on=false;
                    break;
                default:
                    break;
            }
            while (usuario1.getLogin()==1 || usuario2.getLogin()==2) {
                System.out.println("__________________________");
                System.out.println("----- Menu - Usuário -----");
                System.out.println("1.Emprestimo\n2.Devolução\n5.Alterar\n4.Deslogar");
                op=scanner.nextInt();
                switch (op) {
                    case 01:
                        
                        break;
                    case 02:

                        break;
                    case 03:

                        break;
                    case 04:
                        usuario1.setLogin(0);
                        usuario2.setLogin(0);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
