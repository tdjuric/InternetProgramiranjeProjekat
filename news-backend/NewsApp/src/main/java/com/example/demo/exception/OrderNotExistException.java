package com.example.demo.exception;

public class OrderNotExistException extends Exception {

  public OrderNotExistException(String message) {
    super(message);
  }
}
