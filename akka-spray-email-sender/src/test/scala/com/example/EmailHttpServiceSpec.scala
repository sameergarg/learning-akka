package com.example

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class EmailHttpServiceSpec extends Specification with Specs2RouteTest with EmailHttpService {
  def actorRefFactory = system

  def emailActor = system.actorOf(EmailSenderActor.props)
  
  "EmailHttpService" should {

    "return a greeting for GET requests to the root path" in {
      Get("/email") ~> emailRoute(emailActor) ~> check {
        responseAs[String] must contain("Say hello")
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> emailRoute(emailActor) ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put("/email") ~> sealRoute(emailRoute(emailActor)) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: POST, GET"
      }
    }
  }
}
