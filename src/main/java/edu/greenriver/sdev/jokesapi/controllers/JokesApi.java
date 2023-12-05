package edu.greenriver.sdev.jokesapi.controllers;

import edu.greenriver.sdev.jokesapi.model.Joke;
import edu.greenriver.sdev.jokesapi.services.JokeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins="*")
public class JokesApi
{
    private JokeService service;

    public JokesApi(JokeService service)
    {
        this.service = service;
    }

    @GetMapping("jokes")
    public ResponseEntity<List<Joke>> allJokes()
    {
        return new ResponseEntity<>(service.getAllJokes(), HttpStatus.OK);
    }

    @GetMapping("jokes/random")
    public Joke randomJoke()
    {
        return service.random();
    }

    @GetMapping("jokes/{id}")
    public Joke getJokeById(@PathVariable int id)
    {
        return service.getJokeById(id);
    }

    //pass in a new joke object through the request body
    @PostMapping("jokes")
    public ResponseEntity<Joke> addJoke(@RequestBody Joke joke)
    {
        return new ResponseEntity<>(service.addJoke(joke), HttpStatus.CREATED);
    }

    @PostMapping("jokes/query")
    public ResponseEntity<Joke> addJoke(@RequestParam String author, @RequestParam String text)
    {
        Joke newJoke = new Joke(author + ": " + text);
        return new ResponseEntity<>(service.addJoke(newJoke), HttpStatus.CREATED);
    }

    @PutMapping("jokes")
    public Joke editJoke(@RequestBody Joke joke)
    {
        return service.updateJoke(joke);
    }

    @DeleteMapping("jokes")
    public void deleteJoke(@RequestBody Joke joke)
    {
        service.deleteJoke(joke.getId());
    }
}
