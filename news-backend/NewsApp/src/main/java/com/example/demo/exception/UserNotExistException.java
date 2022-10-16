package com.example.demo.exception;

public class UserNotExistException extends Exception {

  public UserNotExistException(String message) {
    super(message);
  }
}
