import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class App {
    private static List<Exemplar> exemplares = new ArrayList<>();
    private static List<Livro> livros = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Boolean on=true;
    
        Usuario usuario1=new Usuario();
        usuario1.setLogin(0);

        Livro livro1=new Livro("A volta dos que não foram", "Frederico da Costa", "2077");
        Livro livro2=new Livro("Lindos cachos de um careca", "Carlos Antônio", "1999");
        livros.add(livro1);
        livros.add(livro2);
        exemplares.add(new Exemplar(livro1));
        exemplares.add(new Exemplar(livro1));
        exemplares.add(new Exemplar(livro2));
        exemplares.add(new Exemplar(livro2));

        Curso curso1=new Curso("Informática");
        Curso curso2=new Curso("Meio ambiente");

        Scanner scanner=new Scanner(System.in);
        while (on==true) {
            System.out.println("==========================");
            System.out.println("------- Biblioteca -------");
            System.out.println("==========================");
            System.out.println("1.Cadastrar Usuário\n2.Login\n3.Sair");
            int op=scanner.nextInt();
            switch (op) {
                case 1:
                    Curso curso=null;
                    System.out.println("==========================");
                    System.out.println("-------- Cadastro --------");
                    System.out.println("==========================");
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
                        usuario1.cadastrar(nome, matricula, senha, curso);
                        System.out.println("Usuário Cadastrado");
                        Thread.sleep(1500);
                    break;
                case 2:
                    System.out.println("==========================");
                    System.out.println("---------- Login ---------");
                    System.out.println("==========================");
                    System.out.println("Matricula:");
                    matricula=scanner.nextLine(); matricula=scanner.nextLine(); 
                    System.out.println("Senha:");
                    senha=scanner.nextLine();

                    if(usuario1.verificar(matricula, senha)){
                        usuario1.setLogin(1);
                        System.out.print("Logando");
                        Thread.sleep(750);System.out.print("."); Thread.sleep(750); System.out.print(".");Thread.sleep(750); System.out.print(".\n"); Thread.sleep(500);
                        System.out.println("Logado com sucesso");
                        Thread.sleep(1500);
                    }
                    break;
                case 3:
                    on=false;
                    break;
                default:
                    break;
            }
            while (usuario1.getLogin()==1) {
                System.out.println("==========================");
                System.out.println("----- Menu - "+usuario1.getNome()+" -----");
                System.out.println("==========================");
                System.out.println("1.Emprestimo\n2.Devolução\n3.Alterar\n4.Deslogar");
                op=scanner.nextInt();
                switch (op) {
                    case 01:
                        fazerEmprestimo(scanner, usuario1);
                        break;
                    case 02:
                        devolverEmprestimo(scanner, usuario1);
                        break;
                    case 03:
                        Curso curso=null;
                        System.out.println("==========================");
                        System.out.println("-------- Alterar ---------");
                        System.out.println("==========================");
                        System.out.println("Nome do Usuário:");
                        String nome=scanner.nextLine(); nome=scanner.nextLine();
                        System.out.println("Número de matricula:");
                        String matricula=scanner.nextLine();
                        System.out.println("Senha:");
                        String senha=scanner.nextLine();
                        System.out.println("==========================");
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
                        usuario1.cadastrar(nome, matricula, senha, curso);
                        break;
                    case 04:
                        usuario1.setLogin(0);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void fazerEmprestimo(Scanner scanner, Usuario usuario) throws Exception {
        System.out.println("==========================");
        System.out.println("Livros disponíveis:");
        System.out.println("==========================");
        List<Exemplar> exemplaresDisponiveis = new ArrayList<>();
        for (Exemplar exemplar : exemplares) {
            if (exemplar.isStatus()) {
                exemplaresDisponiveis.add(exemplar);
                System.out.println((exemplaresDisponiveis.size()) + ". " + exemplar.getLivro().getTitulo()+" === Autor: "+exemplar.getLivro().getAutor()+" === Em: "+exemplar.getLivro().getAno());
                Thread.sleep(750);
            }
        }

        if (exemplaresDisponiveis.isEmpty()) {
            System.out.println("==========================");
            System.out.println("Não há livros disponíveis no momento.");
            System.out.println("==========================");
            return;
        }

        System.out.println("==========================");
        System.out.println("Escolha o livro: ");
        System.out.println("==========================");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > exemplaresDisponiveis.size()) {
            System.out.println("Opção inválida. Tente novamente.");
            return;
        }

        Exemplar exemplarSelecionado = exemplaresDisponiveis.get(escolha - 1);

        Emprestimo emprestimo = new Emprestimo(usuario, exemplarSelecionado);
        usuario.adicionarEmprestimo(emprestimo);
        exemplarSelecionado.setStatus(false);
    
        System.out.println("Empréstimo realizado com sucesso!");
        Thread.sleep(1500);
        System.out.println("Livro: " + exemplarSelecionado.getLivro().getTitulo());
        Thread.sleep(2000);
    }

    private static void devolverEmprestimo(Scanner scanner, Usuario usuario) throws Exception{
        List<Emprestimo> emprestimos = usuario.getEmprestimos();
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataLimite = hoje.plusDays(7);
        if (emprestimos.isEmpty()) {
            System.out.println("==========================");
            System.out.println("Você não possui nenhum empréstimo.");
            Thread.sleep(1500);
            return;
        }
        System.out.println("==========================");
        System.out.println("Empréstimos:");
        System.out.println("==========================");
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo emprestimo = emprestimos.get(i);
            System.out.println((i + 1) + ". " + emprestimo.getExemplar().getLivro().getTitulo() + "(Devolver Em: " + dataLimite.format(formatter)+ ")");
            Thread.sleep(1500);
        }
        System.out.println("==========================");
        System.out.println("Escolha o empréstimo para devolver: ");
        System.out.println("==========================");
        int emprestimoOpcao = scanner.nextInt();
        scanner.nextLine();

        Emprestimo emprestimoSelecionado = emprestimos.get(emprestimoOpcao - 1);

        emprestimoSelecionado.getExemplar().setStatus(true);
        usuario.removerEmprestimo(emprestimoSelecionado);
        Thread.sleep(1500);
        System.out.println("Empréstimo devolvido com sucesso!");
        Thread.sleep(1500);
    }
}

