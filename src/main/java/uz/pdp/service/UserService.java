
package uz.pdp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import uz.pdp.model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String PATH = "file\\users.xml";

    private List<User> users;

    public UserService() {
        users = readUser();
        if (users == null) {
            users = new ArrayList<>();
        }
    }

    public User addUser(User user){
        if (hasUser(user.getUsername())){
            return null;
        }
        users.add(user);
        writeUser(users);
        return user;
    }

    private boolean hasUser(String username){
        for (User user:users){
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public User login(String username,String password){
        for (User user:users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    private void writeUser(List<User> users){
        XmlMapper xmlMapper = new XmlMapper();
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            fileWriter.write("<users>\n");
            for (User user : users) {
                String s = xmlMapper.writeValueAsString(user);
                fileWriter.write(s + "\n");
            }
            fileWriter.write("</users>");
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> readUser() {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            File file = new File(PATH);
            return xmlMapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}