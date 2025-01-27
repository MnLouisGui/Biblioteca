import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String matricula;
    private String senha;
    private int login;

    private Curso curso;
    private List<Emprestimo> emprestimos;
    
    //get and set
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getLogin() {
        return login;
    }
    public void setLogin(int login) {
        this.login = login;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.remove(emprestimo);
    }
    //construtor
    Usuario(){
        this.emprestimos = new ArrayList<>();
    }

    //metodos
    public void cadastrar(String nome, String matricula, String senha, Curso curso){
        this.nome=nome;
        this.matricula=matricula;
        this.senha=senha;
        this.curso=curso;
    }

    public void exibir(){
        System.out.println("Nome: "+this.nome+"\nMatricula: "+this.matricula+"\nSenha: "+this.senha+"\nCurso: "+this.curso.getNome());
    }

    public boolean verificar(String matricula, String senha){
        return this.matricula.equals(matricula) && this.senha.equals(senha);
    }
}
