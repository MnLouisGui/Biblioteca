public class Exemplar {

    private Livro livro;
    private boolean status;

    public Exemplar(Livro livro) {
        this.livro = livro;
        this.status = true;
    }

    public Livro getLivro() {
        return livro;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
