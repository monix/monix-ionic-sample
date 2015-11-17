package mobile.starttabs.controller

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.{AbstractController, injectable}
import shared.models.Friend
import mobile.starttabs.scalaservice.FriendsService

import scala.scalajs.js
import scala.scalajs.js.JSConverters._



@injectable("FriendsCtrl")
class FriendsController(scope: FriendsScope) extends AbstractController[FriendsScope](scope) {

  println("init FriendsCtrl")

  scope.friends = FriendsService.all().toJSArray
}

@js.native
trait FriendsScope extends Scope {
  var friends: js.Array[Friend] = js.native
}
