package learningakka

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{Matchers, WordSpecLike}

/**
  *
  */
abstract class BaseActorSpec extends TestKit(ActorSystem("testSystem")) with WordSpecLike with Matchers
