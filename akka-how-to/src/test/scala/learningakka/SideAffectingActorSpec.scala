package learningakka

import akka.actor.ActorSystem
import akka.testkit.{CallingThreadDispatcher, EventFilter}
import com.typesafe.config.ConfigFactory
import learningakka.SideAffectingActor.Greeting

class SideAffectingActorSpec extends BaseActorSpec {

  "Side affecting actor" must {
    "say Hello world sameer" when {
      "sameer is passed" in {
        val dispatcherId: String = CallingThreadDispatcher.Id
        val props = SideAffectingActor.props.withDispatcher(dispatcherId)
        val actor = system.actorOf(props)
        EventFilter.info(message = "Hello World Sameer")
          .intercept(
            actor ! Greeting("Hello World Sameer Garg")
          )
      }
    }
  }

  "Side affecting actor with listener" must {
    "say Hello world sameer" when {
      "sameer is passed" in {
        val props = SideAffectingActorWithListener.props(Some(testActor))
        val actor = system.actorOf(props)
        actor ! Greeting("Hello World")
        expectMsg("Hello World")
      }
    }
  }


}

object SideAffectingActorSpec {
  val testSystem = {
    val config = ConfigFactory.parseString(
      """
        akka.loggers = [akka.testkit.TestEventListener]
      """)
    ActorSystem("testSystem", config)
  }
}
