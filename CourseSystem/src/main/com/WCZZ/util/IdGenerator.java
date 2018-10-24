package main.com.WCZZ.util;

import main.com.WCZZ.entity.Student;

import java.util.Date;

public class IdGenerator {



    public static String generateId(){
       return String.valueOf(new Date().getTime());
    }

    public static String generatePassword(String id){
        return id.substring(id.length()-6,id.length());
    }
    public static void main(String[] args) {
        String id = generateId();
        System.out.println(id);
        System.out.println(generatePassword(id));
    }
}
