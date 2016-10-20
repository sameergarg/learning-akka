package learningakka

import learningakka.FilteringActor.NumberSequence

class FilteringActorSpec extends BaseActorSpec {
  "Filter actor" should {
    "filter even number sequence" in {
      val filteringActor = system.actorOf(FilteringActor.props(testActor))
      filteringActor ! NumberSequence(2)
      expectMsg(NumberSequence(2))
      filteringActor ! NumberSequence(1)
      expectNoMsg
    }
  }

}
