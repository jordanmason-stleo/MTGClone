package MTGClone.controller;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import MTGClone.SQLDriver;

@RestController
public class GameController {

    private final SQLDriver sqlDriver = new SQLDriver();
    private final Gson gson = new Gson();
    private final String apiPrefix = "/api/";

    @GetMapping(apiPrefix + "/allcards")
    String greet() {
        return gson.toJson(sqlDriver.getAllCards());
    }
}