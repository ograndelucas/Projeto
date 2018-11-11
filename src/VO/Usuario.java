package VO;

public class Usuario {

	public Usuario() {
		// TODO Auto-generated constructor stub
		
	}
	
	private int id;
	private String nome;
	private String email;
	private Boolean isAdmin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Usuario(int id, String nome, String email, Boolean isAdmin) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.isAdmin = isAdmin;
	}
	
	public Usuario (String nome, String email, String isAdmin) {
		super();
		this.nome = nome;
		this.email = email;
		this.isAdmin = Boolean.valueOf(isAdmin);
	}

}
