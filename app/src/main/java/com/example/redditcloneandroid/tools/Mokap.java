package com.example.redditcloneandroid.tools;

import com.example.redditcloneandroid.R;
import com.example.redditcloneandroid.model.Post;
import com.example.redditcloneandroid.model.User;

import java.util.ArrayList;
import java.util.List;

public class Mokap {

    public static List<Post> getPosts(){
        ArrayList<Post> posts = new ArrayList<Post>();
        Post p1 = new Post("jedna zajednica", "Objava1", "Ovo je teskt objave", -1);
        Post p2 = new Post("druga zajednica", "Objava2", "Ovo je tekst objave", R.drawable.about);
        Post p3 = new Post("treca zajednica", "Objava3", "Ovo je tekst objave", -1);

        posts.add(p1);
        posts.add(p2);
        posts.add(p3);

        return posts;
    }

    public static List<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        User u1 = new User("Pera", "Peric", "pera@gmail.com");
        User u2 = new User("Mika", "Mikic", "mika@gmail.com");
        User u3 = new User("Zika", "Zikic", "zika@gmail.com");

        users.add(u1);
        users.add(u2);
        users.add(u3);

        return users;
    }
}
