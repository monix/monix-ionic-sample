package controllers

import play.api.libs.json._
import shared.models.{Signal, Chat}

trait JSONFormats {
  implicit private val chatFormat = Json.format[Chat]
  implicit private val defaultChatFormat = Json.format[Signal]

  implicit val pointFormat = new Format[Signal] {
    def reads(json: JsValue): JsResult[Signal] =
      (json \ "event").validate[String].flatMap {
        case "chat" =>
          defaultChatFormat.reads(json)
        case _ =>
          JsError(JsPath \ "event", s"Event is not `chat`")
      }

    def writes(o: Signal): JsValue =
      Json.obj("event" -> o.event) ++
        defaultChatFormat.writes(o).as[JsObject]
  }
}
