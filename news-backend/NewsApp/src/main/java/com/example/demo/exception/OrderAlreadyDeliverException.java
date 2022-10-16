package com.example.demo.exception;

public class OrderAlreadyDeliverException extends Exception {

  public OrderAlreadyDeliverException(String message) {
    super(message);
  }
}
