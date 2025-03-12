/**
 * 
 */
package br.portaldotenis.portal.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thiago
 * 
 */
@Entity
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	protected Tenista tenistaUm;

	@ManyToOne
	protected Tenista tenistaDois;

	@Temporal(TemporalType.DATE)
	private Calendar data;

	private String hora;
	
	@ManyToOne
	private Local local;
	
	private boolean wo;
	private String obsTenistaUm;
	private String obsTenistaDois;
	private Integer setTenistaUm;
	private Integer setTenistaDois;
	private Integer gamesSet1T1;
	private Integer gamesSet1T2;
	private Integer gamesSet2T1;
	private Integer gamesSet2T2;
	private Integer gamesSet3T1;
	private Integer gamesSet3T2;
	private Integer superTieBreakT1;
	private Integer superTieBreakT2;

	// Verifica quem foi o tenista vencedor
	private Tenista tenistaVencedor;
	private Tenista tenistaPerderdor;
	
	/** retorna o tenista vencedor da partida*/
	
	public Tenista getVencedor(){
		
		if(getSetTenistaUm() > getSetTenistaDois()){
			tenistaVencedor = getTenistaUm();
		}else if (getSetTenistaDois() > getSetTenistaUm()){
			tenistaVencedor = getTenistaDois();
		}

		return tenistaVencedor;

	}
	

	
	/** retorna o tenista perdedor da partida*/
	
	public Tenista getPerdedor(){
		
		if(getSetTenistaUm() > getSetTenistaDois()){
			tenistaPerderdor = getTenistaDois();
		}else if (getSetTenistaDois() > getSetTenistaUm()){
			tenistaPerderdor = getTenistaUm();
		}

		return tenistaPerderdor;

	}
	

	
	/**
	 * Verifica se o jogo já ocorreu 
	 * */
	public boolean getOcorreu(){
		
		if(getSetTenistaUm() != null && getSetTenistaDois() != null){
			if(gamesSet1T1 != null && gamesSet1T2 !=null){
				if(gamesSet1T1 >= 0 && gamesSet1T2 >=0){
					return true;
				}
			}
		}
		
		return false;
		
		
	}

	/**
	 * Verifica se o jogo já ocorreu e o resultado foi colocado 
	 * */
	public boolean getTemResultadoDoJogo(){
		
		if(gamesSet1T1 != null && gamesSet1T2 !=null){
			if(gamesSet1T1 >= 0 && gamesSet1T2 >=0){
				return true;
			}
		}
		
		return false;
		
		
	}

	
	/**
	 * Formata o resultado
	 * */
	public String getResultado(){
		
		String resultado = (String) 
				(gamesSet1T1!=null?gamesSet1T1 + "-":"") + 
				(gamesSet1T2!=null?gamesSet1T2:"") + 
				(gamesSet2T1!=null?" / "+ gamesSet2T1 + "-":"") + 
				(gamesSet2T2!=null?gamesSet2T2:"") + 
				(gamesSet3T1!=null?" / "+ gamesSet3T1 + "-":"") + 
				(gamesSet3T2!=null?gamesSet3T2:"") + 
				(superTieBreakT1!=null?" / "+ superTieBreakT1 + "-":"") + 
				(superTieBreakT2!=null?superTieBreakT2:"");  
		if(wo){
			resultado += " WO";
		}
		
		return resultado;
		
	}
	
	/**
	 * @return id do objeto
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            defini id do objeto
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return tenistaUm do objeto
	 */
	public Tenista getTenistaUm() {
		return tenistaUm;
	}

	/**
	 * @param tenistaUm
	 *            defini tenistaUm do objeto
	 */
	public void setTenistaUm(Tenista tenistaUm) {
		this.tenistaUm = tenistaUm;
	}

	/**
	 * @return tenistaDois do objeto
	 */
	public Tenista getTenistaDois() {
		return tenistaDois;
	}

	/**
	 * @param tenistaDois
	 *            defini tenistaDois do objeto
	 */
	public void setTenistaDois(Tenista tenistaDois) {
		this.tenistaDois = tenistaDois;
	}

	
	/**
	 * @return local do objeto
	 */
	public Local getLocal() {
		return local;
	}

	/**
	 * @param local defini local do objeto
	 */
	public void setLocal(Local local) {
		this.local = local;
	}

	/**
	 * @return data do objeto
	 */
	public Calendar getData() {
		return data;
	}

	/**
	 * @param data
	 *            defini data do objeto
	 */
	public void setData(Calendar data) {
		this.data = data;
	}

	/**
	 * @return hora do objeto
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora
	 *            defini hora do objeto
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * @param superTieBreakT1
	 *            defini superTieBreakT1 do objeto
	 */
	public void setSuperTieBreakT1(Integer superTieBreakT1) {
		this.superTieBreakT1 = superTieBreakT1;
	}

	/**
	 * @return setTenistaUm do objeto
	 */
	public Integer getSetTenistaUm() {
		return setTenistaUm;
	}

	/**
	 * @param setTenistaUm
	 *            defini setTenistaUm do objeto
	 */
	public void setSetTenistaUm(Integer setTenistaUm) {
		this.setTenistaUm = setTenistaUm;
	}

	/**
	 * @return setTenistaDois do objeto
	 */
	public Integer getSetTenistaDois() {
		return setTenistaDois;
	}

	/**
	 * @param setTenistaDois
	 *            defini setTenistaDois do objeto
	 */
	public void setSetTenistaDois(Integer setTenistaDois) {
		this.setTenistaDois = setTenistaDois;
	}

	/**
	 * @return gamesSet1T1 do objeto
	 */
	public Integer getGamesSet1T1() {
		return gamesSet1T1;
	}

	/**
	 * @param gamesSet1T1
	 *            defini gamesSet1T1 do objeto
	 */
	public void setGamesSet1T1(Integer gamesSet1T1) {
		this.gamesSet1T1 = gamesSet1T1;
	}

	/**
	 * @return gamesSet1T2 do objeto
	 */
	public Integer getGamesSet1T2() {
		return gamesSet1T2;
	}

	/**
	 * @param gamesSet1T2
	 *            defini gamesSet1T2 do objeto
	 */
	public void setGamesSet1T2(Integer gamesSet1T2) {
		this.gamesSet1T2 = gamesSet1T2;
	}

	/**
	 * @return gamesSet2T1 do objeto
	 */
	public Integer getGamesSet2T1() {
		return gamesSet2T1;
	}

	/**
	 * @param gamesSet2T1
	 *            defini gamesSet2T1 do objeto
	 */
	public void setGamesSet2T1(Integer gamesSet2T1) {
		this.gamesSet2T1 = gamesSet2T1;
	}

	/**
	 * @return gamesSet2T2 do objeto
	 */
	public Integer getGamesSet2T2() {
		return gamesSet2T2;
	}

	/**
	 * @param gamesSet2T2
	 *            defini gamesSet2T2 do objeto
	 */
	public void setGamesSet2T2(Integer gamesSet2T2) {
		this.gamesSet2T2 = gamesSet2T2;
	}

	/**
	 * @return gamesSet3T1 do objeto
	 */
	public Integer getGamesSet3T1() {
		return gamesSet3T1;
	}

	/**
	 * @param gamesSet3T1
	 *            defini gamesSet3T1 do objeto
	 */
	public void setGamesSet3T1(Integer gamesSet3T1) {
		this.gamesSet3T1 = gamesSet3T1;
	}

	/**
	 * @return gamesSet3T2 do objeto
	 */
	public Integer getGamesSet3T2() {
		return gamesSet3T2;
	}

	/**
	 * @param gamesSet3T2
	 *            defini gamesSet3T2 do objeto
	 */
	public void setGamesSet3T2(Integer gamesSet3T2) {
		this.gamesSet3T2 = gamesSet3T2;
	}

	/**
	 * @return superTieBreak1 do objeto
	 */
	public Integer getSuperTieBreakT1() {
		return superTieBreakT1;
	}

	/**
	 * @param superTieBreak1
	 *            defini superTieBreak1 do objeto
	 */
	public void setSuperTieBreak1(Integer superTieBreakT1) {
		this.superTieBreakT1 = superTieBreakT1;
	}

	/**
	 * @return superTieBreak2 do objeto
	 */
	public Integer getSuperTieBreakT2() {
		return superTieBreakT2;
	}

	/**
	 * @param superTieBreak2
	 *            defini superTieBreak2 do objeto
	 */
	public void setSuperTieBreakT2(Integer superTieBreakT2) {
		this.superTieBreakT2 = superTieBreakT2;
	}
	/**
	 * @return obsTenistaUm do objeto
	 */
	public String getObsTenistaUm() {
		return obsTenistaUm;
	}

	/**
	 * @param obsTenistaUm defini obsTenistaUm do objeto
	 */
	public void setObsTenistaUm(String obsTenistaUm) {
		this.obsTenistaUm = obsTenistaUm;
	}

	/**
	 * @return obsTenistaDois do objeto
	 */
	public String getObsTenistaDois() {
		return obsTenistaDois;
	}

	/**
	 * @param obsTenistaDois defini obsTenistaDois do objeto
	 */
	public void setObsTenistaDois(String obsTenistaDois) {
		this.obsTenistaDois = obsTenistaDois;
	}

	/**
	 * @return wo do objeto
	 */
	public boolean isWo() {
		return wo;
	}

	/**
	 * @param wo defini wo do objeto
	 */
	public void setWo(boolean wo) {
		this.wo = wo;
	}


	
}
