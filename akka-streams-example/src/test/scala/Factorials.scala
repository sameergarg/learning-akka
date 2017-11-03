import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source

object Factorials {

  val source: Source[Int, NotUsed] = Source(1 to 100)

  implicit val system = ActorSystem("Factorials")
  implicit val materializer = ActorMaterializer()

  source.runForeach(i => println(i))(materializer)



}
