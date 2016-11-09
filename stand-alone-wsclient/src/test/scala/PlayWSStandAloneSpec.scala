import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.testkit.TestKit
import org.scalatest.compatible.Assertion
import org.scalatest.{AsyncWordSpecLike, Matchers, MustMatchers, WordSpec}
import play.api.libs.ws.ahc.AhcWSClient

import scala.concurrent.Future

/**
  *
  */
class PlayWSStandAloneSpec extends TestKit(ActorSystem("testsystem"))
  with MustMatchers
  with AsyncWordSpecLike {

  "Stand alone client" should {
    "connect to external site without any error" in {

      implicit val materializer =  ActorMaterializer()

      val wsClient = AhcWSClient()

      val wSStandAlone: PlayWSStandAlone = new PlayWSStandAlone()

      wSStandAlone.execute(wsClient).map { res =>
        res must not be("")
      }

      //TODO provide a cleanup to shut down system and close wsclient

    }
  }
}
