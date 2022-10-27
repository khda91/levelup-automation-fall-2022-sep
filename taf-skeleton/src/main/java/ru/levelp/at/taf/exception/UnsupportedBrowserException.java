package ru.levelp.at.taf.exception;

public class UnsupportedBrowserException extends IllegalArgumentException {

    public UnsupportedBrowserException(String s) {
        super(s + " браузер не поддерживается!");
    }
}
