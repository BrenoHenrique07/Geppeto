package web.trabalhofinal.model;

public enum Areas {
	ENSINO("Ensino"),
	PESQUISA("Pesquisa"),
	EXTENSAO("Extensao");
	
	private String descricao;
	
	private Areas(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
