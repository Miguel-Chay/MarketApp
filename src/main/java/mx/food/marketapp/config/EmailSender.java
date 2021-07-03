package mx.food.marketapp.config;
import org.springframework.scheduling.annotation.Async;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailSender {

    @Autowired
    JavaMailSender javaMailSender;

    private MailSender mailSender;

    @Autowired
    public EmailSender(MailSender mailSender){
        this.mailSender=mailSender;
    }
    @Async
    public void enviarCorreo(String texto, String email, String subject){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            System.out.println("Correo enviando....");
            mailMessage.setFrom("ejemplo-karina@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(texto);
            mailSender.send(mailMessage);
            System.out.println("Correo enviado exitosamente");
        }catch(Exception e){            
            System.out.println("Error al enviar el email de registro desde enviar correo  \n"+ e.getMessage());
        }
    }   
}
