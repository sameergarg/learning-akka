import net.manub.embeddedkafka.{EmbeddedKafka, EmbeddedKafkaConfig}
import org.scalatest.{Matchers, WordSpec}
import net.manub.embeddedkafka.Codecs._
import net.manub.embeddedkafka.ConsumerExtensions._

class EmbeddedKafkaZookeeperSpec extends WordSpec with EmbeddedKafka with Matchers {

  implicit val config = EmbeddedKafkaConfig(kafkaPort = 7000, zooKeeperPort = 7001)

  /**
    * In-memory Zookeeper and Kafka will be instantiated respectively on port 6000 and 6001 and
    * automatically shutdown at the end of the test.
    */

  "runs with embedded kafka" should {
    def testProducerAndConsumer(message: String): Unit = withRunningKafka {
      val topic = "test"

      publishToKafka(topic, message)

      val messageFromKafka: String = consumeFirstMessageFrom[String](topic)

      messageFromKafka shouldBe message
    }

    val message = "hello sameer"

    testProducerAndConsumer(message)
  }

  "my kafka stream" should {
    "be easy to test" in {
      val inputTopic = "input-topic"
      val outputTopic = "output-topic"
      // your code for building the stream goes here e.g.
      val streamBuilder = new KStreamBuilder
      streamBuilder.stream(inputTopic).to(outputTopic)
      // tell the stream test
      // 1. what topics need to be created before the stream starts
      // 2. the builder to be used for initializing and starting the stream
      runStreamsWithStringConsumer(
        topicsToCreate = Seq(inputTopic, outputTopic),
        builder = streamBuilder
      ){ consumer =>
        // your test code goes here
        publishToKafka(inputTopic, key = "hello", message = "world")
        consumer.consumeLazily(outputTopic).head should be ("hello" -> "world")
      }
    }
  }


}
