package io.nlytx

import com.thoughtworks.binding.dom
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("io.nlytx.MetaCogUI")
object MetaCogUI {

  val currentVersion = "2.0.6"
  val baseUrl = "http://hi2lab.io/metacognition" //The url where this app is hosted

  println(s"Loaded MetaCogUI version $currentVersion")

  @JSExport
  def mainContent() :Unit = {
    dom.render(document.body.firstElementChild,MainContainer.build)
  }

}
