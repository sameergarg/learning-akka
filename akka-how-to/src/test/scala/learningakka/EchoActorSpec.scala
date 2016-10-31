package learningakka

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{Matchers, WordSpec, WordSpecLike}

/**
  *
  */
class EchoActorSpec extends TestKit(ActorSystem("testsystem"))
  with WordSpecLike
  with ImplicitSender
  with StopSystemAfterAll {

  "Reply with the same message it receives without ask" in {
    val echo = system.actorOf(Props[EchoActor], "echo2")
    echo ! "some message"
    expectMsg("some message")
  }
}
