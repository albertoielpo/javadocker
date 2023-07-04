package net.ielpo.javadocker.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ielpo.javadocker.model.Version;

/**
 * @author Alberto Ielpo
 */
@RestController
public class HelloController {

    @Value("${JAVA_DOCKER_REMOTE_URI:http://localhost:8080}")
    private String javaDockerRemoteUri;

    private static String remoteEndpoint = "/home";

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private Version version;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HttpClient httpClient;

    @GetMapping("/home")
    public ResponseEntity<Version> getHome() {
        logger.info("Home called...");
        return new ResponseEntity<Version>(this.version, HttpStatus.OK);
    }

    @GetMapping("/remote")
    public ResponseEntity<Version> getRemote() throws Exception {
        logger.info("Remote called...");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(String.format("%s%s", javaDockerRemoteUri, remoteEndpoint)))
                .timeout(Duration.ofSeconds(30))
                .GET().build();

        /* send sync ... */
        HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
        return new ResponseEntity<Version>(objectMapper.readValue(response.body(), Version.class), HttpStatus.OK);
    }

}
