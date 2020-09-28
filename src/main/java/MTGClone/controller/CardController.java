package MTGClone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import MTGClone.Card;
import MTGClone.SQLDriver;
import java.util.ArrayList;

@Controller
public class CardController {
    @GetMapping({"/card", "/"})
    public String hello(Model model) {
        ArrayList<Card> allCards = (new SQLDriver()).getAllCards();
        model.addAttribute("allcards", allCards);
        
        return "card";
    }
    
    @PostMapping({"/card"})
    public String greet(@RequestParam("cardname") String cardname, @RequestParam("manacost") int manacost, 
    @RequestParam("power") int power, @RequestParam("toughness") int toughness, @RequestParam("description") String description, @RequestParam("creaturetype") String creaturetype,
    ModelMap modelMap) {
      Card newCard = new Card(cardname, manacost, power, toughness, description, "", creaturetype);
      SQLDriver d = new SQLDriver();
      d.insertCard(newCard);
        return "card";
    }
    
}