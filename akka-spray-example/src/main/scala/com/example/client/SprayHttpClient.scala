package com.example.client


import com.example.Boot
import spray.http.{BasicHttpCredentials, HttpRequest, HttpResponse}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import spray.client.pipelining._
import akka.actor.ActorSystem

trait SprayHttpClient {

  // we need an ActorSystem to host our application in
  implicit def actorSystem: ActorSystem


  def withSimpleRequest(url: String) = {
    val pipeline: HttpRequest => Future[HttpResponse] = sendReceive
    pipeline(Get(url))
  }

  def withAuthentication(url:String)(userName: String, password: String) = {
    val pipeline: HttpRequest => Future[HttpResponse] = (
        addHeader("X-My-Special-Header", "fancy-value")
        ~> addCredentials(BasicHttpCredentials(userName, password))
        ~> sendReceive
      )
    pipeline(Get(url))
  }
}

