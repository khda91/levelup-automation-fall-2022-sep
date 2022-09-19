package ru.levelp.at.lesson0304.unit.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListConverter {

    // написать метод, который на вход принимает список строк
    // и удаляет все буквы s из слов, и возвращает новый список
    public List<String> removeLetterS(List<String> list) {
        if (list == null) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();

        for (String item : list) {
            result.add(item.replaceAll("s|S", ""));
        }

        return result;
    }
}
