package Utilitario;

import java.awt.Component;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Diego D.
 * @version 1.1
 * @since 2021
 */
public class Mensajeria {

    /**
     *
     * @param destinatario
     * @param asunto
     * @param mensaje
     * @param componente
     * @throws java.lang.Exception
     */
    public static void EnviarCorreo(String destinatario, String asunto, String mensaje, Component componente) throws Exception {
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(propiedades);
        String emisor = "lagartostoresac@gmail.com";
        String contraseñaEmisor = "codigolagarto";

        MimeMessage mail = new MimeMessage(sesion);
        mail.setFrom(new InternetAddress(emisor));
        mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mail.setSubject(asunto);
        mail.setContent(mensaje, "text/html; charset=utf-8");

        Transport transporte = sesion.getTransport("smtp");
        transporte.connect(emisor, contraseñaEmisor);
        transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
        transporte.close();
    }
}
