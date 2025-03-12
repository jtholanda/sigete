package br.portaldotenis.portal.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
 

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.InputStreamDownload;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.portaldotenis.portal.model.Dupla;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.Torneio;
import br.portaldotenis.portal.model.TipoDeChave;
@RequestScoped
public class Utils {

	public static final String EMPRESA = "Portal do Tênis Paraibano";
	public static final String EMAIL = "portaldotenispb@gmail.com";
	public static final String SITE = "www.portaldotenispb.com.br";
	public static final String CONTEXTO = "sigete";
	public static final String TELEFONE = "(83) 9.8888-8321";
	public static final String ESPORTISTA = "tenista";

	public static String formataDataPadrao(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}

	/**
	 * Método que retorna o último dia válido para se inscrever em um torneio
	 * 
	 * 
	 */
	public static Date obtemOntem() {

		Calendar ultimoDiaInscricao = Calendar.getInstance();

		int ano = ultimoDiaInscricao.get(Calendar.YEAR);
		int mes = ultimoDiaInscricao.get(Calendar.MONTH);
		int dia = ultimoDiaInscricao.get(Calendar.DATE) - 1;

		ultimoDiaInscricao.set(ano, mes, dia);
		Date dataLimite = ultimoDiaInscricao.getTime();
		return dataLimite;
	}

	public Properties getProp() throws IOException {
		
		Properties props = new Properties();
		InputStream file = getClass().getResourceAsStream("/dados.properties"); 
		props.load(file);
		return props;
	}
	
	public static String semAcento(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

	public static String getNomePadronizado(String in){
        StringBuilder out = new StringBuilder();
        for ( String s : in.toLowerCase().trim().split(" ") ) {
            if ( !( s.equals("de") ||
                s.equals("da") ||
                s.equals("do") ||
                s.equals("dos") ||
                s.equals("das") ) ) {
	            	if(!s.isEmpty()){
		                out.append( Character.toUpperCase( s.charAt( 0 ) ) );
		                out.append( s.substring( 1, s.length() ) );
	            	}
            } else {
                out.append( s );
            }
            out.append( " " );
        }
        return (out.toString().trim());
	}

	public static int calculaTempo(Calendar dataAtualizacao) {
		
		if(dataAtualizacao != null){
			int MILLIS_IN_DAY = 86400000;
			Calendar c1 = Calendar.getInstance();
			c1.set(Calendar.MILLISECOND, 0);
			c1.set(Calendar.SECOND, 0);
			c1.set(Calendar.MINUTE, 0);
			c1.set(Calendar.HOUR_OF_DAY, 0);
			
			Calendar c2 = dataAtualizacao;
			c2.set(Calendar.MILLISECOND, 0);
			c2.set(Calendar.SECOND, 0);
			c2.set(Calendar.MINUTE, 0);
			c2.set(Calendar.HOUR_OF_DAY, 0);
			return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / MILLIS_IN_DAY);
		}
		return -1;

	}
	/**
	 * @param midiaTorneio
	 * @param arquivo
	 * @param path
	 * @param novoNomeArquivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void enviarArquivo(UploadedFile arquivo, String path, String novoNomeArquivo)
			throws FileNotFoundException, IOException {


		try {
			IOUtils.copy(arquivo.getFile(), new FileOutputStream(new File(path, novoNomeArquivo)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FileNotFoundException("Arquivo não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Não foi possível enviar o arquivo!");
		}
	}
	/**
	 * @param path
	 * @param novoNomeArquivo
	 * @throws IOException
	 */
	public static boolean verificaDimensoesImagem(UploadedFile arquivo, int alturaMaxima, int larguraMaxima, int alturaMinima, int larguraMinima) {

		try{
		BufferedImage bufferedImage = ImageIO.read(arquivo.getFile());
		//File imagem = new File(path+"/"+novoNomeArquivo);
		//BufferedImage img = ImageIO.read(imagem);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		
		if(w > larguraMaxima || h > alturaMaxima || w < larguraMinima || h < alturaMinima){
			return false;
		}else{
			return true;
		}
		}catch(Exception e){
			return false;
		}
	}
	
	public static Download gerarChaveamentoPDF(List<Tenista> tenistas, int quantidadeDeParticipantes, Torneio torneio, Nivel nivel,
			List<Dupla> duplas) throws Exception {

		// Class.forName("com.postgresql.Driver");
		// Connection mySQLConnection =
		// DriverManager.getConnection(DAO.CONEXAO,DAO.USUARIO, DAO.SENHA);

		
		// lê o jasper
		String nomeDoJasper;
		if(torneio.isMisto2Estagios() && quantidadeDeParticipantes < 7){
			nomeDoJasper = "Chaveamento-"+ TipoDeChave.ELIMINATORIO.getNome() + "-" + quantidadeDeParticipantes + ".jasper";
			
		}else{
			nomeDoJasper = "Chaveamento-" + torneio.getTipoDeChave().getNome() + "-" + quantidadeDeParticipantes + ".jasper";
		}
		nomeDoJasper = semAcento(nomeDoJasper);
		System.out.println(nomeDoJasper);
        InputStream relatorio = Utils.class.getResourceAsStream(nomeDoJasper);
		// String relatorio= request.getHeader("Host") + "/sigete/torneio/" +
		// nomeRelatorioChaves + ".jasper";
        System.out.println(relatorio);
		// parametros que serão enviados
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		parameters.put("nomeTorneio", torneio.getNome());
		parameters.put("nivelTecnico", nivel.getDescricao());
		
		if(duplas == null){
			for (int i = 0; i < tenistas.size(); i++) {
				parameters.put("tenista" + (i + 1), tenistas.get(i).getNomeRanking());
			}
		}
		else if(tenistas == null){
			for (int i = 0; i < duplas.size(); i++) {
				parameters.put("tenista" + (i + 1), duplas.get(i).getTenista().getNomeRanking() + "/" + duplas.get(i).getTenistaParceiro().getNomeRanking());
			}
			
		}

		// obtem o objeto de impressão
		// JasperPrint jp = JasperFillManager.fillReport(relatorio, parameters,
		// mySQLConnection);

		ByteArrayOutputStream exported = new ByteArrayOutputStream();
		JRExporter exporter = new JRPdfExporter();

		// objeto de exportação com arquivo de entrada e saida
		// JRExporter exporter = new JRPdfExporter();
		// exporter.setParameter(JRExporterParameter.JASPER_PRINT,jp);
		// exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
		// "chaveamento-" + torneio.getNome() + "-" + nivel.getDescricao());

		byte[] bytes = null;

		JasperPrint print = JasperFillManager.fillReport(relatorio, parameters, new JREmptyDataSource());
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, exported);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.exportReport();
		bytes = exported.toByteArray();

		// exportando
		// System.out.println("exportando...");
		// exporter.exportReport();

		return new InputStreamDownload(new ByteArrayInputStream(bytes), "application/pdf", "chaveamento-" + torneio.getNome() + "-"
				+ nivel.getDescricao() + ".pdf", false, bytes.length);

	}

	public static Integer obterFatorial(Integer numero){
		/* Progama para calcular o valor de 5 Fatorial */

		Integer f = numero; 
		numero--;
		
		while (numero > 1){
			
		  f = f *(numero); 
		  numero--;
		  
         }
		
		return f;
	}
	
	

}
