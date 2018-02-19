package mobi.riemer.testing.greetingservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class GreetingserviceApplication

fun main(args: Array<String>) {
    SpringApplication.run(GreetingserviceApplication::class.java, *args)
}

@RestController
class WebserviceController {

    @GetMapping("/greeting")
    fun helloWorld(): String {
        return "Hello World!"
    }
}
