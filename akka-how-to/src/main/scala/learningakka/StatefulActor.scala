package learningakka

import akka.actor.{ActorRef, Props}

class StatefulActor (nextActor: ActorRef) extends ActorWithLogging {

  private var evenMessages: Vector[Int] = _

  override def receive: Receive = {
    case number: Int => if(!evenMessages.exists(_ == number) && number%2 == 0) {
      if(evenMessages.size >= 5)
        evenMessages = evenMessages.tail

      evenMessages:+number
      nextActor ! number

    }
  }
}

object StatefulActor {

  def props(nextActor: ActorRef) = Props(new StatefulActor(nextActor))
}

