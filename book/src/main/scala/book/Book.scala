package book
import acyclic.file

import scalatags.Text.tags2
import scalatags.Text.all._

/**
 * Created by haoyi on 10/26/14.
 */
object Book {
  val autoResources = Seq(
    "META-INF/resources/webjars/highlightjs/8.2-1/highlight.min.js",
    "META-INF/resources/webjars/highlightjs/8.2-1/styles/idea.min.css",
    "META-INF/resources/webjars/highlightjs/8.2-1/languages/scala.min.js",
    "META-INF/resources/webjars/highlightjs/8.2-1/languages/javascript.min.js",
    "META-INF/resources/webjars/highlightjs/8.2-1/languages/bash.min.js",
    "META-INF/resources/webjars/highlightjs/8.2-1/languages/diff.min.js",
    "META-INF/resources/webjars/highlightjs/8.2-1/languages/xml.min.js",
    "META-INF/resources/webjars/pure/0.5.0/pure-min.css",
    "META-INF/resources/webjars/pure/0.5.0/grids-responsive-min.css",
    "META-INF/resources/webjars/font-awesome/4.2.0/css/font-awesome.min.css",
    "META-INF/resources/webjars/font-awesome/4.2.0/fonts/FontAwesome.otf",
    "META-INF/resources/webjars/font-awesome/4.2.0/fonts/fontawesome-webfont.eot",
    "META-INF/resources/webjars/font-awesome/4.2.0/fonts/fontawesome-webfont.svg",
    "META-INF/resources/webjars/font-awesome/4.2.0/fonts/fontawesome-webfont.ttf",
    "META-INF/resources/webjars/font-awesome/4.2.0/fonts/fontawesome-webfont.woff",
    "css/side-menu.css",
    "example-fastopt.js",
    "webpage/weather.js",
    "favicon.svg",
    "favicon.png"
  )

  val manualResources = Seq(
    "images/javascript-the-good-parts-the-definitive-guide.jpg",
    "images/Hello World.png",
    "images/Hello World White.png",
    "images/Hello World Console.png",
    "images/IntelliJ Hello.png",
    "images/Dropdown.png",
    "images/Scalatags Downloads.png"
  )

  val includes = for(res <- autoResources) yield {
    if (res.endsWith(".js"))
      script(src:=res)
    else if (res.endsWith(".css"))
      link(rel:="stylesheet", href:=res)
    else
      raw("")
  }

  val txt = Index()
  val data = upickle.write(sect.structure)

  val site = Seq(
    raw("<!doctype html>"),
    html(
      head(
        meta(charset:="utf-8"),
        meta(name:="viewport", content:="width=device-width, initial-scale=1.0"),
        link(rel:="shortcut icon", `type`:="image/png", href:="favicon.png"),
        tags2.title("Hands-on Scala.js"),
        includes
      ),
      body(
        onload:=s"Controller().main($data)",
        div(id:="layout")(
          a(href:="#menu", id:="menuLink", cls:="menu-link")(
            span
          ),
          div(id:="menu")
        ),
        div(id:="main",
          div(id:="main-box")(
            txt
          )
        )
      )
    )
  ).render



}
