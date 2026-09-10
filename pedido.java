import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Cliente cliente;
    private List<Item> itens;
    private TipoPagamento pagamento;
    private boolean pago;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.pago = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public TipoPagamento getPagamento() {
        return pagamento;
    }

    public boolean isPago() {
        return pago;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.subtotal();
        }
        return total;
    }

    // baixa o estoque de tudo que foi pedido e marca como pago
    public void realizarPagamento(TipoPagamento forma) {
        for (Item item : itens) {
            item.getProduto().baixarEstoque(item.getQuantidade());
        }
        pagamento = forma;
        pago = true;
    }

    public String toString() {
        String s = "Pedido de " + cliente + "\n";
        for (Item item : itens) {
            s += "  " + item + "\n";
        }
        s += "Total: R$ " + calcularTotal();
        if (pago) {
            s += " (pago em " + pagamento + ")";
        }
        return s;
    }
}
