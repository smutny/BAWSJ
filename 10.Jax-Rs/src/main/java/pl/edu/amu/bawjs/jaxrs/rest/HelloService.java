package pl.edu.amu.bawjs.jaxrs.rest;

public class HelloService {

    String createHelloMessage(String name) {
        return "Hello " + name + "!";
    }

}