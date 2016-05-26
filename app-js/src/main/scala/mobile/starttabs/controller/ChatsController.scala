package mobile.starttabs.controller

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.{AbstractController, injectable}
import mobile.starttabs.scalaservice.ChatsService
import mobile.stream.DataConsumer
import shared.models.{Signal, Chat}

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import concurrent.duration._
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable



@injectable("ChatsCtrl")
class ChatsController(scope: ChatsScopeType) extends AbstractController[ChatsScopeType](scope) {

  println("init ChatsCtrl")
  scope.chats = ChatsService.all().toJSArray
//   removing elements will only impact scope.chats array, not the underlying initial array defined in ChatsService.
  scope.remove = { chat: Chat => scope.chats.splice(scope.chats.indexOf(chat), 1) }

  val s1 = new DataConsumer(1.second, 1274028492832L, doBackPressure = true)
     .collect { case s: Signal => s }

    s1.foreach{ s =>
      scope.chats = ChatsService.add(s.value).toJSArray
      scope.$apply()
    }

    s1.subscribe()

}

@js.native
trait ChatsScopeType extends Scope {
  var chats: js.Array[Chat] = js.native
  var remove: js.Function1[Chat, _] = js.native
}
