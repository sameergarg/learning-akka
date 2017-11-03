import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Future

object Main extends App with FileRoute{

  implicit val system = ActorSystem("file-uploader")
  implicit val materializer = ActorMaterializer()
  private val port = 9000

  implicit val ec = system.dispatcher

  val route = path("ping") {
    complete("pong")
  }
  private val futureBinding: Future[Http.ServerBinding] =
    Http()
    .bindAndHandle(route ~ fileUploadRoute, "localhost", port)

  println(s"Server running on port $port")

  scala.sys.addShutdownHook {
    futureBinding.flatMap(_.unbind).onComplete(_ => system.terminate())
  }

}
