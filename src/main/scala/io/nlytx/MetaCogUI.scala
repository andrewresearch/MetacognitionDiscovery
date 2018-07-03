package io.nlytx

import com.thoughtworks.binding.dom
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("io.nlytx.MetaCogUI")
object MetaCogUI {

  println("Loaded MetaCogUI")

  @dom
  def myDiv = {
    <div>
      { myDivNested1.bind }
      { myDivNested2.bind }
    </div>
  }

  @dom
  def myDivNested1 = {
    <div>This is my 1st nested div!</div>
  }

  @dom
  def myDivNested2 = {
    <div>This is my 2nd nested div!</div>
  }

  @JSExport
  def mainContent() = {
    dom.render(document.body,myDiv)
  }

}
