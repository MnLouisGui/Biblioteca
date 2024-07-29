public class Livro {
    private String titulo;
    private String autor;
    private String ano;

    Livro(String titulo, String autor, String ano){
        this.titulo=titulo;
        this.autor=autor;
        this.ano=ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAno() {
        return ano;
    }
    public String getAutor() {
        return autor;
    }
}
