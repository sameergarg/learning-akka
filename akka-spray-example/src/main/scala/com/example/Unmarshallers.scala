package com.example

import spray.json.{DefaultJsonProtocol, JsObject, JsString, JsValue, JsonFormat}

import scala.xml.{Elem, Node, Text}

case class EmailRequest(from: String, to: String, message: String)

trait Unmarshallers extends DefaultJsonProtocol {

  implicit object NodeFormat extends JsonFormat[Node] {

    override def read(json: JsValue): Node = null //not required to implement for now

    override def write(node: Node): JsValue =
      if(node.child.count(_.isInstanceOf[Text]) == 1)
        JsString(node.text)
      else
        JsObject(node.child.collect{
          case e: Elem => e.label -> write(e)
        }: _*)
  }

  implicit val emailRequestFormat = jsonFormat3(EmailRequest.apply)
}
