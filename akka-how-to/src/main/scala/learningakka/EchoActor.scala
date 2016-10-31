package learningakka

import akka.actor.Actor

class EchoActor extends Actor {
  def receive = {
    case msg =>
      sender() ! msg
  }
}
