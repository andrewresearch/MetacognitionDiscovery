package io.nlytx

import com.thoughtworks.binding.dom
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("io.nlytx.MetaCogUI")
object MetaCogUI {

  println("Loaded MetaCogUI")

  @JSExport
  def mainContent() :Unit = {
    dom.render(document.body.firstElementChild,MainContainer.build)
  }

}
