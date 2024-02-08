package com.example.artforyourheart;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data is lombok import that handles getters & setters
@Data
//@NoArgsConstructor is setter injection
@NoArgsConstructor
public class User {
   private Integer age;
    private String name;
    private String location;
    private String gender;
    private String art;
   private String artPhotos;
    private String photos;

    private String height;
    List<String> interests;

    List<String> matches;
    List<String> yes;
    List<String> no;

    private String role;

    private String bio;

    public User(Integer age, String name, String location, String gender, String art, String artPhotos, String photos, String height, List<String> matches, List<String> yes, List<String> no, String role, String bio){
     this.age = age;
     this.name = name;
     this.location = gender;
     this.art = art;
     this.artPhotos = artPhotos;
     this.photos= photos;
     this.height=height;
     this.matches=matches;
     this.yes=yes;
     this.no=no;
     this.role=role;
     this.bio=bio;
    }


}
