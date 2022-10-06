package ru.levelp.at.lesson0708.api.serialize.deserialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationApp {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("ser/file.txt")) {
            ObjectInputStream oin = new ObjectInputStream(fis);

            Person person = (Person) oin.readObject();

            System.out.println(person);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
