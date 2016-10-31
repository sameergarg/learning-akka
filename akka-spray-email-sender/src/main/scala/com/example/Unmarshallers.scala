package com.example

import spray.json.DefaultJsonProtocol

case class EmailRequest(from: String, to: String, message: String)

trait Unmarshallers extends DefaultJsonProtocol {

  implicit val emailRequestFormat = jsonFormat3(EmailRequest.apply)
}
