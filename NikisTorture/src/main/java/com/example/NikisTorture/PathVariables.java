package com.example.NikisTorture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class PathVariables {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/{art}/{tier}")
    public String index2(@PathVariable String tier, @PathVariable String art) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<?, ?> map = mapper.readValue(Paths.get("data.json").toFile(), Map.class);
            Map<?, ?> sorte = (Map<?, ?>) map.get("sorte");
            Map<?, ?> arT = (Map<?, ?>) sorte.get(art);

            ObjectMapper mapper2 = new ObjectMapper();

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(arT.get(tier));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Error 1";
    }
}
