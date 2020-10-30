package example.micronaut2;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import junit.framework.TestCase;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StatusControllerTest extends TestCase {

    private ApplicationContext context;
    private Component2 componentStub;
    private EmbeddedServer server;

    @Override
    public void setUp() {
        componentStub = new Component2() {
            @Override
            public Status getStatus() {
                return Status.FAIL;
            }
        };

        context = ApplicationContext.build().build();
        context.registerSingleton(Component2.class, componentStub);
        context.start();

        server = context.getBean(EmbeddedServer.class);
        server.start();
        RestAssured.port = server.getPort();
    }

    public void testWholeStatusIsFailWhenOneOutOf2ComponentsStatusIsFail() {
        when()
            .get("/_status")
            .then()
            .statusCode(200)
            .body("components.size()", is(2))
            .body("components[0].name", equalTo("Component1"))
            .body("components[0].status", equalTo("OK"))
            .body("components[1].name", equalTo("Component2"))
            .body("components[1].status", equalTo("FAIL"))
            .body("status", equalTo("FAIL"));
    }
}