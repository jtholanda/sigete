package br.portaldotenis.portal.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.simplemail.Mailer;
import br.portaldotenis.portal.model.Tenista;

public class EmailsParalelos implements Runnable {

	
	private Mailer mailer;
	private String assuntoDoEmail;
	private String mensagemDoEmail;
	private Tenista tenista;
	
	
	public EmailsParalelos(Mailer mailer, String assuntoDoEmail, String mensagemDoEmail, Tenista tenista) {
		super();
		this.mailer = mailer;
		this.assuntoDoEmail = assuntoDoEmail;
		this.mensagemDoEmail = mensagemDoEmail;
		this.tenista = tenista;
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			SimpleEmail email = new SimpleEmail();
			email.setSubject(assuntoDoEmail);
			email.setMsg(mensagemDoEmail);
			
			if (tenista != null) {
				email.addTo(tenista.getEmail());
			}else{
				email.addTo(Utils.EMAIL);
			}
			
			email.setCharset(EmailSender.UTF_8);
			email.updateContentType(EmailSender.TEXT_HTML);
			mailer.send(email);
			email = null;

			
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
