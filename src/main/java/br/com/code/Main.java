package br.com.code;

import twitter4j.TwitterException;

import java.io.IOException;
import java.io.PrintStream;

public class Main {
    private static PrintStream printer;

    public static void main(String[] args) throws TwitterException, IOException {
    Logic twitterPoster = new Logic (printer);
    }
}

