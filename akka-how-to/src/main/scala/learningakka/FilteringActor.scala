package learningakka
import akka.actor.{ActorRef, Props}
import learningakka.FilteringActor.NumberSequence

/**
  * The actor forwards some messages it receives and discards others.
  */
class FilteringActor(nextActor: ActorRef) extends ActorWithLogging {

  override def receive: Receive = {
    case message@NumberSequence(index) => if(index%2 == 0) nextActor ! message
  }
}

object FilteringActor {
  case class NumberSequence(index: Int)

  def props(nextActor: ActorRef) = Props(new FilteringActor(nextActor))
}
