
public class ContaBancaria {
	
	private int saldo;
	private Cliente cliente;
	private int id;
	private static int contador = 0;
	
	public ContaBancaria(Cliente cliente) {
		this.cliente = cliente;
		this.id = ++contador;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public int getSaldo() {
		// TODO Auto-generated method stub
		return saldo;
	}
	
	public int depositar(int valor) {
		saldo += valor > 0 ? valor : 0;
		return saldo;
	}
	
	public int sacar(int valor) {
		saldo -= (valor > 0 && valor <= saldo) ? valor : 0;
		return saldo;
	}
	
	public int getId() {
		return id;
	}

}
