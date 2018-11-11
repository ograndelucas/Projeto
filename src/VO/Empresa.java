package VO;

public class Empresa {

	public Empresa() {
		// TODO Auto-generated constructor stub
	}

	private int id;
	private String nomeFantasia;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public Empresa(int id, String nomeFantasia) {
		super();
		this.id = id;
		this.nomeFantasia = nomeFantasia;
	}
	public Empresa(String nomeFantasia) {
		super();
		this.nomeFantasia = nomeFantasia;
	}
	
}
