package mobile.starttabs.controller

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.{AbstractController, injectable}
import mobile.starttabs.dto.IPAddress
import mobile.starttabs.service.BetterHttpService

import scala.scalajs.js
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue


@injectable("AccountCtrl")
class AccountController(scope: AccountScope, betterHttp: BetterHttpService) extends AbstractController[AccountScope](scope) {

  println("init AccountController")
  scope.ip = IPAddress("Unknown")
  val ipAddressFut = betterHttp.getJsonAndUnpickle[IPAddress]("http://www.telize.com/jsonip")
  ipAddressFut.onSuccess {
    case ip => println(s"response from server IP = $ip"); scope.ip = ip
  }

}

@js.native
trait AccountScope extends Scope {
  var ip: IPAddress = js.native
}
