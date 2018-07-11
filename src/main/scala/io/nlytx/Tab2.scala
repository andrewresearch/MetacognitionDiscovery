package io.nlytx

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html._

object Tab2 {

  val name = "Tech Info"

  @dom
  val content: Binding[Div] =  {
    <div>
      <div class="container-fluid">
        <div class = "row">
          <div class="col">
            <p>
              This app is written by <a href="http://andrewresearch.net">Andrew Gibson</a> in Scala and uses Scala.js and Binding.scala to produce a client side application served from AWS S3.
            </p>
            <p>
              The actual analysis is completed by the Text Analytics Pipeline (TAP) which is hosted on AWS ECS.
            </p>
            <p>
              This web application (and TAP) are both open source licensed under the Apache 2.0 Licence. Please see my <a href="https://github.com/andrewresearch">GitHub (AndrewResearch) page</a> for links to these and other software projects.
            </p>
            <p>
              My home page is <a href="http://andrewresearch.net">AndrewResearch.net</a> and my natural language analytics apps are hosted at <a href="http://nlytx.io">nlytx.io</a>
            </p>
          </div>
         </div>
      </div>
    </div>
  }
}
