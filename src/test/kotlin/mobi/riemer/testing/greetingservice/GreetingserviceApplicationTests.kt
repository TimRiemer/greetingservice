package mobi.riemer.testing.greetingservice

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingserviceApplicationTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun contextLoads() {
    }

    @Test
    fun `should return Hello World`() {
        val result = testRestTemplate.getForEntity("/greeting", String::class.java)

        assertThat(result?.body).isEqualTo("Hello World!")
    }
}
