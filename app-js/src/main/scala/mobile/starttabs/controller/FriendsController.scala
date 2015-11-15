package mobile.starttabs.controller

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.{AbstractController, injectable}
// import mobile.starttabs.dto.Friend
import shared.models.Friend
import mobile.starttabs.scalaservice.FriendsService

import scala.scalajs.js
import scala.scalajs.js.JSConverters.array2JSRichGenTrav

import mobile.stream._
import shared.models.Signal
import monifu.concurrent.Implicits.globalScheduler
import monifu.reactive.Observable
import shared.models.Signal
import scala.scalajs.js
import concurrent.duration._

import monifu.reactive.Ack.Continue
import monifu.reactive.{Ack, Observer}
import scala.concurrent.Future

@injectable("FriendsCtrl")
class FriendsController(scope: FriendsScope) extends AbstractController[FriendsScope](scope) {

  println("init FriendsCtrl")

  val s1 = new DataConsumer(200.millis, 1274028492832L, doBackPressure = false)
   .collect { case s: Signal => s }

  s1.foreach{ s =>
    println(s.value)
    FriendsService.replace(0,s.value)
    scope.friends = FriendsService.all().toJSArray
    scope.$apply()
  }

  s1.subscribe()

  scope.friends = FriendsService.all().toJSArray

}

@js.native
trait FriendsScope extends Scope {
  var friends: js.Array[Friend] = js.native
}
