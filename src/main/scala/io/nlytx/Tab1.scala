package io.nlytx

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.Div


object Tab1 {

  val name = "Live Demo"



  @dom
  val content: Binding[Div] =  {
    <div>
      <div class="container-fluid">
        {
          if(DataStore.analysed.bind) {
            Tab1_Results.resultsDisplay.bind
          } else {
            Tab1_Entry.dataEntry().bind
          }
        }
      </div>
    </div>
  }








}



