package edu.greenriver.sdev.jokesapi;

import edu.greenriver.sdev.jokesapi.model.Joke;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JokesApiApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;
    @Test
    void contextLoads() {
    }

    @Test
    public void getAllJokes()
    {
        String endpoint = "http://localhost:" + port + "/jokes";

        //assemble request
        HttpEntity request = new HttpEntity(new HttpHeaders());
        //make request get response
        ResponseEntity<Joke[]> response = rest.exchange(endpoint, HttpMethod.GET, request, Joke[].class);
        //test it
        HttpStatusCode status = response.getStatusCode();
        Joke[] jokes = response.getBody();

        System.out.println("Hello");
        assertEquals(status, HttpStatus.OK);
        assertTrue(jokes.length > 0);

    }

}
