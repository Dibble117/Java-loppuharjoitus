package com.nikooy.userdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    private List<User> users = new ArrayList<>();

//addUser
    public void addUser(User user){
        users.add(user);
    }

//searchUser
    public void searchUser(String keyword){
        for (User user : users) {
            if(user.getName().equals(keyword)){
                System.out.println(user.getName() + " " + user.getId());
            }
        }
    }

//getUsers
    public List<User> getUsers(){
        return this.users;
    }

//getUsersByCourse
    public List<User> getUsersByCourse(String course){
        List<User> usersByCourse = new ArrayList<>();
        for (User user : users) {
            if(user.getCourse().equals(course)){
                usersByCourse.add(user);
            } else {
            }
        }
        return usersByCourse;
    }

//searchUserList
    public List<User> searchUserList(String keyword){
        List<User> usersByKeyword = new ArrayList<>();
        for (User user : users) {
            if(user.getName().equals(keyword)){
                usersByKeyword.add(user);
            } else {
            }
        }
        return usersByKeyword;
    }

//searchUserByName
    public User searchUserByName(String name){
        for (User user : users) {
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

//searchCourseList
    public List<User> searchCourseList(String keyword){
        List<User> usersByKeyword = new ArrayList<>();
        for (User user : users) {
            if(user.getCourse().equals(keyword)){
                usersByKeyword.add(user);
            } else {
            }
        }
        return usersByKeyword;
    }

//searchUserByCourse
    public User searchUserByCourse(String course){
        for (User user : users) {
            if(user.getCourse().equals(course)){
                return user;
            }
        }
        return null;
    }

//getUsersByName
    public List<User> getUsersByName(String name){
        List<User> usersByName = new ArrayList<>();
        for (User user : users) {
            if(user.getName().equals(name)){
                usersByName.add(user);
            } else {
            }
        }
        return usersByName;
    }

//deleteuserwithid
    public void deleteUser(int idInt){
        for (User user : users) {
            if(user.getId() == idInt){
                users.remove(user);
                break;
            }
        }
    }

//addUserToCourse
    public void addUserToCourse(User user){
        for (User u : users) {
            if(u.getId() == user.getId()){
                u.setCourse(user.getCourse());
            }
        }
    }

//addUserWithId
    public void addUserWithId(int idInt, User user){
        for (User u : users) {
            if(u.getId() == idInt){
                u.setName(user.getName());
                u.setCourse(user.getCourse());
            }
        }
    }

//addUserToCourseWithId
    public void addUserToCourseWithId(int idInt, User user){
        for (User u : users) {
            if(u.getId() == idInt){
                u.setCourse(user.getCourse());
            }
        }
    }

//updateUserToCourse
    public void updateUserToCourse(User user){
        for (User u : users) {
            if(u.getId() == user.getId()){
                u.setCourse(user.getCourse());
            }
        }
    }

//updateUserWithId
    public void updateUserWithId(int idInt, User user){
        for (User u : users) {
            if(u.getId() == idInt){
                u.setName(user.getName());
                u.setCourse(user.getCourse());
            }
        }
    }

//updateUser
    public void updateUser(User user){
        for (User u : users) {
            if(u.getId() == user.getId()){
                u.setName(user.getName());
                u.setCourse(user.getCourse());
            }
        }
    }

//getUserById
    public User getUserById(int id){
        for (User user : users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

/*
    public ResponseEntity<String> getErrorMessageResponse(){
        return new ResponseEntity<>("Hakusanalla ei löytynyt tulosta", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> getErrorMessageResponseBadRequest(){
        return new ResponseEntity<>("Hakusanalla ei löytynyt tulosta", HttpStatus.BAD_REQUEST);
    }
*/

}
