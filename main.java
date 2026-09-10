import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Produto> produtos = new ArrayList<>();
    static Pedido pedidoAtual;

    public static void main(String[] args) {
        produtos.add(new Produto(Descricao.ARROZ, 25.90, 50));
        produtos.add(new Produto(Descricao.FEIJAO, 8.50, 40));
        produtos.add(new Produto(Descricao.FARINHA, 6.20, 30));
        produtos.add(new Produto(Descricao.LEITE, 4.75, 60));

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n1) Novo pedido");
            System.out.println("2) Realizar pagamento");
            System.out.println("0) Sair da aplicacao");
            System.out.print("Opcao: ");
            opcao = Integer.parseInt(sc.nextLine());

            if (opcao == 1) {
                novoPedido();
            } else if (opcao == 2) {
                realizarPagamento();
            } else if (opcao != 0) {
                System.out.println("Opcao invalida");
            }
        }
    }

    static void novoPedido() {
        System.out.print("Nome do cliente: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        Pedido pedido = new Pedido(new Cliente(nome, cpf));

        int escolha = -1;
        while (escolha != 0) {
            System.out.println("\nProdutos:");
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println((i + 1) + " - " + produtos.get(i));
            }
            System.out.print("Produto (0 para terminar): ");
            escolha = Integer.parseInt(sc.nextLine());

            if (escolha < 0 || escolha > produtos.size()) {
                System.out.println("Produto invalido");
                continue;
            }
            if (escolha == 0) {
                continue;
            }

            Produto p = produtos.get(escolha - 1);
            System.out.print("Quantidade: ");
            int qtd = Integer.parseInt(sc.nextLine());

            if (!p.temEstoque(qtd)) {
                System.out.println("Nao tem estoque suficiente");
                continue;
            }

            pedido.adicionarItem(new Item(p, qtd));
            System.out.println("Adicionado");
        }

        if (pedido.getItens().size() == 0) {
            System.out.println("Pedido vazio, cancelado");
            return;
        }

        pedidoAtual = pedido;
        System.out.println("\n" + pedidoAtual);
    }

    static void realizarPagamento() {
        if (pedidoAtual == null) {
            System.out.println("Nenhum pedido em aberto");
            return;
        }
        if (pedidoAtual.isPago()) {
            System.out.println("Esse pedido ja foi pago");
            return;
        }

        System.out.println("Total: R$ " + pedidoAtual.calcularTotal());
        System.out.println("Forma de pagamento:");
        TipoPagamento[] formas = TipoPagamento.values();
        for (int i = 0; i < formas.length; i++) {
            System.out.println((i + 1) + " - " + formas[i]);
        }
        System.out.print("Escolha: ");
        int f = Integer.parseInt(sc.nextLine());

        if (f < 1 || f > formas.length) {
            System.out.println("Forma invalida, cancelando pagamento");
            return;
        }

        pedidoAtual.realizarPagamento(formas[f - 1]);
        System.out.println("Pagamento feito!");
        System.out.println(pedidoAtual);
    }
}
