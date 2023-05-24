package net.ielpo.javadocker.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    // private static String remoteUrl = "http://172.50.0.10:8080/home";
    private static String remoteUrl = "http://java-docker-1:8080/home";

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private Version version;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/home")
    public ResponseEntity<Version> getHome() {
        logger.info("Home called...");
        return new ResponseEntity<Version>(this.version, HttpStatus.OK);
    }

    @GetMapping("/remote")
    public ResponseEntity<Version> getRemote() throws Exception {
        logger.info("Remote called...");
        Builder builder = HttpRequest.newBuilder().uri(new URI(remoteUrl))
                .timeout(Duration.ofSeconds(30));

        HttpRequest request = builder.GET().build();

        HttpResponse<byte[]> response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofByteArray());

        return new ResponseEntity<Version>(objectMapper.readValue(response.body(), Version.class), HttpStatus.OK);

    }

}
