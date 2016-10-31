package learningakka

import akka.actor.Props

class ActorLifeCycleHooksextendsSpec extends BaseActorSpec {
  "Actor lifecycle hook" should {
    "print lifecycle event" in {
      val actor = system.actorOf(Props())
    }
  }

}
