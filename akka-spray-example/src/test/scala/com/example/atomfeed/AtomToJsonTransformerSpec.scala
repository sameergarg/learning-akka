package com.example.atomfeed

import java.io.InputStream

import org.specs2.matcher.JsonMatchers

import scala.xml.XML

class AtomToJsonTransformerSpec extends org.specs2.mutable.Specification with JsonMatchers {

  val sut = new AtomToJsonTransformer{

  }
  "Atom to Json transformer" should {
    "transform xml to json" in  {
      val xmlStream: InputStream = getClass().getResourceAsStream("/sample-atom-feed.xml")

      val xml = XML.load(xmlStream)

      sut.transform(xml).toString() must */("title" -> "Atom-Powered Robots Run Amok")
    }
  }
}
