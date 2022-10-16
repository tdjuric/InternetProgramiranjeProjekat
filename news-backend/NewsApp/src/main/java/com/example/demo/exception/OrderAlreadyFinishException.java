package com.example.demo.exception;

public class OrderAlreadyFinishException extends Exception {

  public OrderAlreadyFinishException(String message) {
    super(message);
  }
}
