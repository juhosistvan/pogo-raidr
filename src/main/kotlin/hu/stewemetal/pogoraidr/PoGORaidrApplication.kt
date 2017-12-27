package hu.stewemetal.pogoraidr

import com.github.messenger4j.Messenger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class PoGORaidrApplication {

    private val pogoLogger = LoggerFactory.getLogger(PoGORaidrApplication::class.java)

    @Bean
    fun provideMessengerApi(@Value("\${messenger4j.pageAccessToken}") pageAccessToken: String,
                            @Value("\${messenger4j.appSecret}") appSecret: String,
                            @Value("\${messenger4j.verifyToken}") verifyToken: String): Messenger {
        return Messenger.create(pageAccessToken, appSecret, verifyToken)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(PoGORaidrApplication::class.java, *args)
        }
    }

}