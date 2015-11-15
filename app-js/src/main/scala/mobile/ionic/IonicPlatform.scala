package mobile.ionic

import com.greencatsoft.angularjs.core.Promise
import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

@injectable("$ionicPlatform")
@js.native
trait IonicPlatform extends js.Object {
  def ready(callback: js.Function0[_]): Promise[js.Any] = js.native
}
