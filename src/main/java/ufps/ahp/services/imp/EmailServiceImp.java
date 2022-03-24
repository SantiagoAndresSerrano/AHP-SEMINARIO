package ufps.ahp.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ufps.ahp.services.EmailSenderService;

@Service
public class EmailServiceImp{

    @Autowired
    private EmailSenderService emailSenderService;

    public void enviarEmail(String asunto, String mensaje, String destinatario){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(mensaje);
        simpleMailMessage.setSubject(asunto);
        simpleMailMessage.setTo(destinatario);
        emailSenderService.sendEmail(simpleMailMessage);
    }

}
