package mobile.html.template

import scalatags.Text.all._
import mobile.ionic.IonicHtmlTags._
import mobile.HtmlCompilable

object TabDash extends HtmlCompilable {
  override def filePath: String = "templates/tab-dash.html"

  override def output: String = {
    ionView(viewTitle := "Dashboard")(
      ionContent(cls := "padding")(
        div(cls := "list card")(
          div(cls := "item item-divider")("Recent update"),
          div(cls := "item item-body")(
            div("There is a fire in ", b("sector 3"))
          )
        ),
        div(cls := "list card")(
          div(cls := "item item-divider")("Health"),
          div(cls := "item item-body")(
            div("You ate an apple today")
          )
        ),
        div(cls := "list card")(
          div(cls := "item item-divider")("Upcoming"),
          div(cls := "item item-body")(
            div("You have", b("29"), "meetings on your calendar tomorrow")
          )
        )
      )
    ).toString()
  }
}