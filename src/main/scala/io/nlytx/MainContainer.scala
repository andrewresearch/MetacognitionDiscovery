package io.nlytx

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.Div
object MainContainer {

  val title = "Towards the Discovery of Learner Metacognition From Reflective Writing"
  val footer = "\u24D2 Andrew Gibson 2016-2018"

  @dom
  def build: Binding[Div] = {
    <div class="container">
      <div>
        <h3 class="text-muted">{title}</h3>
      </div>

      <div class="row">
        <div class="col-lg-12">
          <div>

            <ul class="nav nav-tabs" id="myTab" data:role="tablist">
              <li class="nav-item">
                <a class="nav-link active" id="home-tab" data:data-toggle="tab" href="#home" data:role="tab" data:aria-controls="home" data:aria-selected="true">{Tab0.name}</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="profile-tab" data:data-toggle="tab" href="#profile" data:role="tab" data:aria-controls="profile" data:aria-selected="false">Profile</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="contact-tab" data:data-toggle="tab" href="#contact" data:role="tab" data:aria-controls="contact" data:aria-selected="false">Contact</a>
              </li>
            </ul>
            <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active" id="home" data:role="tabpanel" data:aria-labelledby="home-tab">{Tab0.content.bind}</div>
              <div class="tab-pane fade" id="profile" data:role="tabpanel" data:aria-labelledby="profile-tab">{Tab1.content.bind}</div>
              <div class="tab-pane fade" id="contact" data:role="tabpanel" data:aria-labelledby="contact-tab">{Tab2.content.bind}</div>
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
