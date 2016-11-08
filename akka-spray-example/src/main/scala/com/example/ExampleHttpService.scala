package com.example

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef, ActorRefFactory, Props}
import spray.http.MediaTypes._
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport._

class HttpActor extends Actor with ExampleHttpService {

  override def actorRefFactory: ActorRefFactory = context

  implicit val system = context.system

  override def receive: Receive = runRoute(httpRoute())
}


trait ExampleHttpService extends HttpService with Unmarshallers {

  def httpRoute() = path(""){
    post{
      decompressRequest(){
        entity(as[EmailRequest]){ emailRequest =>
          //emailSender ! emailRequest
          detach(){
            complete {
              <html>
                <body>
                  <h1>s"Email request received from $email!"</h1>
                </body>
              </html>
            }
          }
        }
      }
    }~get {
      respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
        complete {
          <html>
            <body>
              <h1>Say hello to <i>spray akka service</i>! template</h1>
            </body>
          </html>
        }
      }
    }
  }


}


