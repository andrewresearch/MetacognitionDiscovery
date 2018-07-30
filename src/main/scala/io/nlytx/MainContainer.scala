package io.nlytx

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.Div
object MainContainer {

  val title = "Towards the Discovery of Learner Metacognition From Reflective Writing"
  val footer = s"\u24D2 Andrew Gibson 2016-2018, verions: ${MetaCogUI.currentVersion}"

  @dom
  def build: Binding[Div] = {
    <div class="container-fluid">
      <div>
        <h3 class="text-muted">{title}</h3>
      </div>

      <div class="row">
        <div class="col">
          <div>

            <ul class="nav nav-tabs" id="myTab" data:role="tablist">
              <li class="nav-item">
                <a class="nav-link active" id="home-tab" data:data-toggle="tab" href="#home" data:role="tab" data:aria-controls="home" data:aria-selected="true">{Tab0.name}</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="demo-tab" data:data-toggle="tab" href="#demo" data:role="tab" data:aria-controls="demo" data:aria-selected="false">{Tab1.name}</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="info-tab" data:data-toggle="tab" href="#info" data:role="tab" data:aria-controls="info" data:aria-selected="false">{Tab2.name}</a>
              </li>
            </ul>
            <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active" id="home" data:role="tabpanel" data:aria-labelledby="home-tab">{Tab0.content.bind}</div>
              <div class="tab-pane fade" id="demo" data:role="tabpanel" data:aria-labelledby="demo-tab">{Tab1.content.bind}</div>
              <div class="tab-pane fade" id="info" data:role="tabpanel" data:aria-labelledby="info-tab">{Tab2.content.bind}</div>
            </div>

          </div>
        </div>
      </div>
      <footer class="footer">
        <hr/>
        <p>{footer}</p>
      </footer>
    </div>

  }
}
