package com.example

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef, ActorRefFactory, Props}
import courier.{Envelope, Mailer, Multipart, Text}
import spray.http.MediaTypes._
import spray.routing.HttpService§
import spray.httpx.SprayJsonSupport._

class EmailActor extends Actor with EmailService {

  override def actorRefFactory: ActorRefFactory = context

  implicit val system = context.system

  val emailSender = system.actorOf(EmailSenderActor.props)

  override def receive: Receive = runRoute(emailRoute(emailSender))
}

object EmailActor {


}

class EmailSenderActor extends Actor {
  import courier._, Defaults._

  val mailer = Mailer("localhost", 1025)
    //run mail catcher in background to test it or use details of your personal smtp server
    .auth(true)
    .as("you@gmail.com", "p@$$w3rd")
    .startTtls(true)()

  override def receive: Receive = {
    case emailRequest: EmailRequest =>
      println(s"Email request received from ${emailRequest.from}")
      mailer(Envelope.from("you" `@` "gmail.com")
        .to("mom" `@` "gmail.com")
        .cc("dad" `@` "gmail.com")
        .subject("miss you")
        .content(Text("hi mom"))).onSuccess {
        case _ => println("message delivered")
      }
  }


}

object EmailSenderActor {
  def props = Props(new EmailSenderActor())

}

trait EmailService extends HttpService with Unmarshallers {

  def emailRoute(emailSender: ActorRef) = path("email"){
    post{
      decompressRequest(){
        entity(as[EmailRequest]){ emailRequest =>
          emailSender ! emailRequest
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


