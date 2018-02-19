package mobi.riemer.testing.greetingservice

import org.assertj.core.api.Assertions.assertThat
import org.junit.ClassRule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.containers.GenericContainer

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DockerImageTests {

    class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    companion object {
        var genericContainer: KGenericContainer = KGenericContainer("greetingservice:0.0.1-SNAPSHOT")

        @ClassRule
        @JvmField
        val resource: ExternalResource = object : ExternalResource() {

            override fun before() {
                this@Companion.genericContainer.start()
            }
        }
    }

    @Test
    fun testDockerImage() {
        val mappedPort = genericContainer.getMappedPort(8080)

        val entity = HttpEntity<String>(null, HttpHeaders())

        val response = testRestTemplate.exchange("http://localhost:${mappedPort}/greeting", HttpMethod.GET, entity, String::class.java)

        assertThat(response?.body).isEqualTo("Hello World!")
    }
}
