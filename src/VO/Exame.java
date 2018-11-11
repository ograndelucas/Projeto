package VO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Exame {

	public Exame() {
		// TODO Auto-generated constructor stub
	}
	
	private int idExame;
	private String nomePaciente;
	private String descricao;
	private int idEmpresa;
	private Date dataRealiazacao;
	public int getIdExame() {
		return idExame;
	}
	public void setIdExame(int idExame) {
		this.idExame = idExame;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Date getDataRealiazacao() {
		return dataRealiazacao;
	}
	public void setDataRealiazacao(Date dataRealiazacao) {
		this.dataRealiazacao = dataRealiazacao;
	}
	public Exame(int idExame, String nomePaciente, String descricao, int idEmpresa, Date dataRealiazacao) {
		super();
		this.idExame = idExame;
		this.nomePaciente = nomePaciente;
		this.descricao = descricao;
		this.idEmpresa = idEmpresa;
		this.dataRealiazacao = dataRealiazacao;
	}
	
	public Exame(int idExame, String nomePaciente, String descricao, int idEmpresa, String dataRealizacao) {
		super();
		this.idExame = idExame;
		this.nomePaciente = nomePaciente;
		this.descricao = descricao;
		this.idEmpresa = idEmpresa;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 

		try { 
			this.dataRealiazacao= formatter.parse(dataRealizacao);
		} catch (Exception e) {
			System.err.println("Ops! Problema com a data: " + dataRealiazacao);
			e.printStackTrace();			
		} //try
	}

	public Exame(String nomePaciente, String descricao, String idEmpresa, String dataRealiazacao) {
		super();
		this.nomePaciente = nomePaciente;
		this.descricao = descricao;
		this.idEmpresa = Integer.parseInt(idEmpresa);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 

		try { 
			this.dataRealiazacao= formatter.parse(dataRealiazacao);
		} catch (Exception e) {
			System.err.println("Ops! Problema com a data: " + dataRealiazacao);
			e.printStackTrace();			
		} //try
	}
}
