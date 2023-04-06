package nvt.doan.config;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailjetConfig {
    @Value("${mailjet.apikey}")
    private String apiKey;

    @Value("${mailjet.secretkey}")
    private String secretKey;

    @Bean
    public MailjetClient mailjetClient() {
        return new MailjetClient(apiKey, secretKey, new ClientOptions("v3.1"));
    }
}
