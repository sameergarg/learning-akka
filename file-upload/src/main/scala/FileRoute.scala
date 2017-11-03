import java.io.File

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.{FileIO, Framing}
import akka.util.ByteString

import scala.concurrent.Future

trait FileRoute {

  val fileUploadRoute: Route = pathPrefix("file") {
    extractRequestContext { rctx =>
      implicit val mat = rctx.materializer
      implicit val ec = rctx.executionContext
      path("sum") {
        fileUpload("csv") {
          case (fileInfo, byteSource) =>
            val sumF = byteSource
              .via(Framing.delimiter(ByteString("\n"), 1024))
              .mapConcat(_.utf8String.split(",").toVector)
              .map(_.toInt)
              .runFold(0)(_ + _)

            onSuccess(sumF) { sum => complete(s"Sum: ${sum.toString}") }
        }
      } ~
        path("store") {
          fileUpload("csv") {
            case (fileInfo, byteSource) =>
              val f = new File("a.txt")
              byteSource
                .runWith(FileIO.toPath(f.toPath))
              complete(s"File saved at ${f.getAbsolutePath}")
          }
        } ~
        path("echo") {
          fileUpload("csv") {
            case (fileInfo, byteSource) =>
              val contentF: Future[String] = byteSource
              .runFold("")(_ + _.utf8String)
              onSuccess(contentF){content => complete(s"contents of ${fileInfo.fileName} are \n${content}")}
          }
        }
    }
  }
}

