package com.example.client

import akka.actor.ActorSystem
import org.specs2.concurrent.ExecutionEnv
import org.specs2.matcher.Matcher
import org.specs2.mutable.Specification
import spray.http.HttpResponse
import spray.testkit.Specs2RouteTest

import scala.concurrent.Future

class SprayHttpClientSpec extends Specification with Specs2RouteTest {

  val sut = new SprayHttpClient {
    override implicit val actorSystem: ActorSystem = system
  }

  "Spray HTTP client" should {

    "connect to site with basic authentication" in {implicit ee: ExecutionEnv =>
      val futureResponse: Future[HttpResponse] = sut.withAuthentication("https://auth-demo.aerobatic.io/")("aerobatic", "aerobatic")
      futureResponse must isSuccessfulResponse.await
    }

    "connect to site" in {implicit ee: ExecutionEnv =>
      val futureResponse: Future[HttpResponse] = sut.withSimpleRequest("http://www.bbc.co.uk/news")
      futureResponse must isSuccessfulResponse.await
    }
  }

  def isSuccessfulResponse: Matcher[HttpResponse] = { response: HttpResponse =>
    response.entity.asString != ""
    response.status.intValue == 200
  }
}
