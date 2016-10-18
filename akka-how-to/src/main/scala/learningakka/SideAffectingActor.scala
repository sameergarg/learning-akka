package learningakka

import akka.actor.{ActorRef, Props}
import learningakka.SideAffectingActor.Greeting

class SideAffectingActor extends ActorWithLogging {

  override def receive: Receive = {
    case Greeting(message) => log.info(s"$message")
  }
}

object SideAffectingActor {

  def props = Props(new SideAffectingActor)

  case class Greeting(message: String)


}

class SideAffectingActorWithListener(listener: Option[ActorRef]) extends ActorWithLogging {

  override def receive: Receive = {
    case Greeting(message) =>
      log.info(s"$message")
      listener.foreach(_ ! message)
  }
}

object SideAffectingActorWithListener {

  def props(listener: Option[ActorRef] = None) = Props(new SideAffectingActorWithListener(listener))
}
