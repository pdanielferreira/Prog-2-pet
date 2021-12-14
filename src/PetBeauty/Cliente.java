package PetBeauty;

public class Cliente extends Utilizador{

    public Cliente(String username, String password, String nome, String apelido, String NIF, String nTel, String tipo) {
        super(username, password, nome, apelido, NIF, nTel, "0");
    }

    public void menuCliente(){
        System.out.println(System.lineSeparator().repeat(3));
        System.out.println("\t-------------- MENU CLIENTE --------------");
        System.out.println("\t\t1 - Adicionar ativos");
        System.out.println("\t\t2 - Alterar informação sobre os ativos");
        System.out.println("\t\t3 - Pesquisar por nome, tipo ou montante");
        System.out.println("\t\t4 - Listar os ativos ativados atualmente");
        System.out.println("\t\t5 - Remover ativos");
        System.out.println("\t\t6 - Relatório dos ativos financeiros ativos entre duas datas");
        System.out.println("\t\t7 - Relatório dos ativos financeiros ativos entre duas datas com o pagamento de impostos ao nível mensal");
        System.out.println("\t\t8 - Alterar dados \n");
        System.out.println("\t\t0 - SAIR");
    }
}
