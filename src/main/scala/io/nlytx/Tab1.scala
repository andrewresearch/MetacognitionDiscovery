package io.nlytx

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.Div

object Tab1 {

  val name = "Live Demo"

  @dom
  val content: Binding[Div] =  {
    <div>
    </div>
  }
}
