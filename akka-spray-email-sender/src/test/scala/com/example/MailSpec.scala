package com.example

import org.specs2.mutable.Specification
import courier._
import org.jvnet.mock_javamail.Mailbox
import org.specs2.time.NoTimeConversions

import scala.concurrent.Await
import scala.concurrent.duration._

// Need NoTimeConversions to prevent conflict with scala.concurrent.duration._
class MailSpec extends Specification with NoTimeConversions {
  val mailer = courier.Mailer("localhost", 25)

  "the mailer" should {
    "send an email" in {

      val future = mailer(Envelope.from("you@gmail.com")
        .to("mom@gmail.com")
        .cc("dad@gmail.com")
        .subject("miss you")
        .content(Text("hi mom")))

      Await.ready(future, 5.seconds)
      val momsInbox = Mailbox.get("mom@gmail.com")
      momsInbox.size === 1
      val momsMsg = momsInbox.get(0)
      momsMsg.getContent === "hi mom"
      momsMsg.getSubject === "miss you"
    }
  }
}


