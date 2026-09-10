public class Produto {

    private Descricao descricao;
    private double preco;
    private int quantidadeEstoque;

    public Produto(Descricao descricao, double preco, int quantidadeEstoque) {
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Descricao getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    // confere se dá pra vender essa quantidade
    public boolean temEstoque(int quantidade) {
        return quantidade > 0 && quantidade <= quantidadeEstoque;
    }

    public void baixarEstoque(int quantidade) {
        quantidadeEstoque = quantidadeEstoque - quantidade;
    }

    public String toString() {
        return descricao + " - R$ " + preco + " (estoque: " + quantidadeEstoque + ")";
    }
}
