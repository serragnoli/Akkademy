package com.akkademy.messages

case class SetRequest(key: String, value: String)

case class GetRequest(key: String)

case class ReverseRequest(text: String)

case class SetIfNotExistsRequest(key: String, value: String)

case class KeyNotFoundException(key: String) extends Exception

case class UnknownReverseTextMessageException() extends Exception