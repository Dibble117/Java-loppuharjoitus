package com.nikooy.userdemo;

import java.util.List;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
public class UserRestController {

    private UserService uService = new UserService();

    //ALOITUSSIVU
    //----------------------------------
    @GetMapping("/")
    public String getBasicInfo(){

        return "<h1>Tervetuloa, aloita lisäämällä opiskelija kurssille käyttäen Postmania.</h1>\n"
        + " <h2>Voit hakea kaikki opiskelijat, tai hakea opiskelijoita pelkän kurssin, nimen tai id:n perusteella.</h2>"
        + " <h2>Voit myös päivittää opiskelijan kurssia tai poistaa opiskelijan id:n perusteella.</h2>";
    }
    //----------------------------------

    //OPISKELIJAN LISÄÄMINEN KURSSILLE
    //----------------------------------
    @PostMapping("/add/{name}/{course}")
    public ResponseEntity<String> addUserToCourse(@PathVariable String name, @PathVariable String course){
        if(name.isEmpty() || course.isEmpty()){
            return new ResponseEntity<>("Nimi tai kurssi puuttuu", HttpStatus.BAD_REQUEST);
        } else {
            User user = new User(0, name, course);
            uService.addUser(user);
            return new ResponseEntity<>(user.getName() + " lisätty kurssille " + user.getCourse(), HttpStatus.OK);
        }
    }
    //----------------------------------

    //HAE OPISKELIJA ID:LLÄ
    //----------------------------------
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        try {
            int idInt = Integer.parseInt(id);
            User user = uService.getUserById(idInt);
            if(user == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            } catch (NumberFormatException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }
    public ResponseEntity<String> getErrorMessageResponse(){
        return new ResponseEntity<>("Hakusanalla ei löytynyt tulosta", HttpStatus.NOT_FOUND);
    }
    //----------------------------------

    //HAE KAIKKI OPISKELIJAT
    //----------------------------------
    @GetMapping("/users")
    public List<User> getUsers(){
        return uService.getUsers();
    }
    //----------------------------------

    //PÄIVITÄ OPISKELIJAN KURSSI ID:LLÄ
    //----------------------------------
    @PostMapping("/update/{id}/{course}")
    public ResponseEntity<String> updateUserToCourse(@PathVariable String id, @PathVariable String course){
        if(id.isEmpty() || course.isEmpty() || uService.getUserById(Integer.parseInt(id)) == null){
            return new ResponseEntity<>("Väärä tai puuttuva ID tai kurssi puuttuu", HttpStatus.BAD_REQUEST);
        } else {
            User user = new User(Integer.parseInt(id), uService.getUserById(Integer.parseInt(id)).getName(), course);
            uService.updateUserWithId(Integer.parseInt(id), user);
            return new ResponseEntity<>(user.getName() + " päivitetty kurssille " + user.getCourse(), HttpStatus.OK);
        }
    }
    //----------------------------------

    //POISTA OPISKELIJA ID:LLÄ
    //----------------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        try {
            int idInt = Integer.parseInt(id);
            if(uService.getUserById(idInt) == null){
                return new ResponseEntity<>("Opiskelijaa ei löytynyt", HttpStatus.NOT_FOUND);
            } else {
                uService.deleteUser(idInt);
                return new ResponseEntity<>("Opiskelija poistettu", HttpStatus.OK);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Virheellinen id", HttpStatus.BAD_REQUEST);
        }
    }
    //----------------------------------

    //HAE OPISKELIJAT NIMELLÄ
    //----------------------------------
    @GetMapping("/search/name/{keyword}")
    public ResponseEntity<List<User>> searchUserByName(@PathVariable String keyword){
        List<User> users = uService.searchUserList(keyword);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
    //----------------------------------

    //HAE OPISKELIJAT KURSSIN PERUSTEELLA
    //----------------------------------
    @GetMapping("/search/course/{keyword}")
    public ResponseEntity<List<User>> searchUserByCourse(@PathVariable String keyword){
        List<User> users = uService.searchCourseList(keyword);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
    //----------------------------------

}
