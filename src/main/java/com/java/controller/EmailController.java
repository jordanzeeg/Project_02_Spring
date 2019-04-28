package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.Messengering;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	//TODO add parameters here
	@PostMapping
	public ResponseEntity<?> sendEmail(@RequestBody Messengering messenger) {
		mailSender.send(new MimeMessagePreparator() { //sends email
			String link = new String();
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(
						mimeMessage, true, "UTF-8");
					messageHelper.setSubject("Password Reset");	
					messageHelper.setText("This is a reset code. copy the code below into the field asking "
							+ "for the reset code when you go to reset your password\n");
			}
		});
		Messengering mess = new Messengering(0,"success");
		return ResponseEntity.ok(mess);
	}
	
	
}
