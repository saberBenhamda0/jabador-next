package com.jabador.experience_service.exception;

public class RessourceNotFoundException extends RuntimeException {
    public RessourceNotFoundException(long id) {
        super("sorry there is not experience wit the id : " + id + " , please entre a valid id");
    }
}
