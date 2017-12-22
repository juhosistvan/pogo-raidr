package hu.stewemetal.pogoraidr;
//
//import com.github.messenger4j.Messenger;
//import com.github.messenger4j.MessengerPlatform;
//import com.github.messenger4j.send.MessengerSendClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.Bean;
//
///**
// * Entry point for the Spring Boot PoGORaidRApplication.
// * <p>
// * <p>
// * The Spring Context will be bootstrapped and the application will be configured properly.
// * In addition a {@code MessengerSendClient} will be exposed as a singleton Spring Bean, so it is injectable.
// * </p>
// *
// * @author Max Grabenhorst
// */
//@SpringBootApplication
//public class PoGORaidRApplication extends SpringBootServletInitializer {
//
//    private static final Logger pogoLogger = LoggerFactory.getLogger(PoGORaidRApplication.class);
//
//    public static void main(String[] args) {
//        SpringApplication.run(PoGORaidRApplication.class, args);
//    }
//
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(PoGORaidRApplication.class);
//    }
//
//    /**
//     * Initializes the {@code MessengerSendClient}.
//     *
//     * @param pageAccessToken the generated {@code Page Access Token}
//     */
//    @Bean
//    public MessengerSendClient messengerSendClient(@Value("${messenger4j.pageAccessToken}") String pageAccessToken) {
//        pogoLogger.debug("Initializing MessengerSendClient - pageAccessToken: {}", pageAccessToken);
//        return MessengerPlatform.newSendClientBuilder(pageAccessToken).build();
//    }
//
//    @Bean
//    protected Messenger messenger = Messenger.create()
//
//
//}
