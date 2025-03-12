package br.portaldotenis.portal.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Gabriel Soares
 * @author Thiago Holanda
 */
@Entity
public class Nivel {
	
	
	public static final int INFANTIL = 7;
	public static final int INICIANTE = 6;
	public static final int QUINTA_CLASSE = 5;
	public static final int INICIANTE_FEMININO = 3;
	public static final int CLASSEB_FEMININO = 2;
	public static final int SOMA3_DUPLAS = 6;
	public static final int SOMA4_DUPLAS = 8;
	public static final int SOMA5_DUPLAS = 10;
	public static final int SOMA6_DUPLAS = 12;
	public static final int SOMA7_DUPLAS = 14;
	public static final int SOMA8_DUPLAS = 16;
	public static final int SOMA9_DUPLAS = 18;
	public static final int SOMA10_DUPLAS = 20;
	public static final String QUINTA_CLASSE_DESCRICAO = "5º Classe";



	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoNivel tipoNivel;
	
	private Integer codigo;
	
	private Integer quantidadeDeTenistas;
	
	@Enumerated(EnumType.STRING)
	private TipoEsporte tipoEsporte;
	
	public Nivel() {
	}
	public Nivel(String descricao, TipoNivel tipoNivel) {
		this.descricao = descricao;
		this.tipoNivel = tipoNivel;
	}

	public TipoDupla getTipoDeDupla(){
	
		
		if(this.isDuplas()){
			if(this.getCodigo() <= 6){
				return TipoDupla.CLASSE;
			}else{
				return TipoDupla.SOMA;
			}
		}else{
			return null;
		}
	}
	
	public Boolean isMista(){
		if (this.getDescricao().contains("Mista")){
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean isMasculino(){
		if (this.getDescricao().contains("Masculino")){
			return true;
		}else{
			return false;
		}
	}

	public Boolean isFeminino(){
		if (this.getDescricao().contains("Feminino")){
			return true;
		}else{
			return false;
		}
	}

	public Boolean isSimples(){
		if (quantidadeDeTenistas == 1){
			return true;
		}else{
			return false;
		}
	}

	public Boolean isDuplas(){
		if (quantidadeDeTenistas == 2){
			return true;
		}else{
			return false;
		}
	}
	public Sexo getSexo(){
		if(this.isMasculino()){
			return Sexo.MASCULINO;
		}else if (this.isFeminino()){
			return Sexo.FEMININO;
		}else{
			return null;
		}
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoNivel getTipoNivel() {
		return tipoNivel;
	}

	public void setTipoNivel(TipoNivel tipoNivel) {
		this.tipoNivel = tipoNivel;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
	
	public Integer getQuantidadeDeTenistas() {
		return quantidadeDeTenistas;
	}
	public void setQuantidadeDeTenistas(Integer quantidadeDeTenista) {
		this.quantidadeDeTenistas = quantidadeDeTenista;
	}

	public TipoEsporte getTipoEsporte() {
		return tipoEsporte;
	}

	public void setTipoEsporte(TipoEsporte tipoEsporte) {
		this.tipoEsporte = tipoEsporte;
	}

}
