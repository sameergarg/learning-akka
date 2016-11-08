package com.example.atomfeed

import com.example.Unmarshallers

import scala.xml.Elem

trait AtomToJsonTransformer extends Unmarshallers {
  import spray.json._

  def transform(xmlFeed: Elem) =
    (xmlFeed \\ "entry").toSeq.toJson
}

object AtomToJsonTransformer
