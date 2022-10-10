package ru.levelp.at.lesson0709.api.serialize.deserialize;

import com.github.javafaker.Faker;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;

public class SerializationApp {

    public static void main(String[] args) {
        var faker = new Faker();
        var person = new Person(faker.name().firstName(), faker.name().lastName(), new SecureRandom().nextInt());
        System.out.println(person);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("ser/file.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(person);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
