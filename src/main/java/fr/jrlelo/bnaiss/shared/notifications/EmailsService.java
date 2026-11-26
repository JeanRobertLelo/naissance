package fr.jrlelo.bnaiss.shared.notifications;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailsService {

    private final MailPitClient mailpitClient;

    public void sendActivationEmail(Map<String, String> params) {
        String appName = "MS Naissance";

        String subject = String.format("Activez votre compte %s", appName);

        Map<String, String> fromEmail = Map.of(
                "Email", "jrlelo@tech.naissance",
                "Name", "JR Lelo"
        );

        List<Map<String, String>> toEmail = List.of(Map.of(
                "Email", params.get("email"),
                "Name", params.get("name")
        ));

        String contentEmail = String.format("Votre code d’activation est :  %s", params.get("code"));

        Map<String, Object> contenu = Map.of(
                "Subject", subject,
                "From", fromEmail,
                "To", toEmail,
                "Text", contentEmail,
                "HTML", contentEmail

        );

        this.mailpitClient.send(contenu);
    }
}
