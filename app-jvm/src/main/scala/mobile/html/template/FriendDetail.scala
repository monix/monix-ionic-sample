package mobile.html.template

import scalatags.Text.all._
import mobile.ionic.IonicHtmlTags._
import mobile.HtmlCompilable

/**
 * This template loads for the 'tab.friend-detail' state (see StateConfig)
 * 'friend' is a $scope variable created in the FriendsCtrl controller (see #FriendsController)
 * The FriendsCtrl pulls data from a scala service FriendsService (not to be confused with angular services).
 * The Friends service returns an array of friend data
 */
object FriendDetail extends HtmlCompilable {
  override def filePath: String = "templates/friend-detail.html"

  override def output: String = {
    ionView(viewTitle := "{{friend.name}}")(
      ionContent(cls := "padding")(
        img(ngSrc := "{{friend.face}}", style := "width:64px; height:64px"),
        h3("Notes"),
        p("{{friend.notes}}")
      )
    ).toString()
  }
}