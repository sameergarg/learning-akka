package com.example

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class ExampleHttpServiceSpec extends Specification with Specs2RouteTest with ExampleHttpService {
  def actorRefFactory = system


  "EmailHttpService" should {

    "return a greeting for GET requests to the root path" in {
      Get("/") ~> httpRoute() ~> check {
        responseAs[String] must contain("Say hello")
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> httpRoute() ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put("/") ~> sealRoute(httpRoute()) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: POST, GET"
      }
    }
  }
}
