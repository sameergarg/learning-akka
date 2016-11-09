import akka.stream.ActorMaterializer
import akka.actor.ActorSystem
import play.api.libs.ws.WSClient

import scala.concurrent.Future


class PlayWSStandAlone {
  def execute(wsClient: WSClient)(implicit system: ActorSystem): Future[String] =
    wsClient.url("http://example.com").get().map {
      resp => resp.body
    }(system.dispatcher)
}
