package com.ab.emailUtil;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	//inject the mail sender
	@Autowired
	private JavaMailSender sender;
	
	public boolean sendEmail(String to, String subject, String body) {
		boolean isSent=false;
		try {
				
			MimeMessage mimeMessage = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			sender.send(mimeMessage);
			
			isSent=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSent;
		
	}
	
	
}
