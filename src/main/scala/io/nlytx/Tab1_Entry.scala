package io.nlytx

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.Event
import org.scalajs.dom.html.Div

object Tab1_Entry {

  //var currentReflection = Reflection("","","")

  private def addText(textType:String):Unit = textType match {
    case tt:String =>
      println(s"Clicked on: $tt")
      DataService.getText(tt)
    case _ => println("No matching text type")
  }


  @dom
  def dataEntry() :Binding[Div] = {
    <div>
      <div class = "row">
        <div class="col">
          <p>
            This tool analyses reflective writing for key reflective phrases and categorises them for aspects of metagocognitive process.
          </p>
          <ol>
            <li>Use the down arrow on the <em>Analyse</em> button to select an example reflection <b>OR</b> type or paste your own reflection into the text box</li>
            <li>Click <b>Analyse</b></li>
          </ol>
        </div>
      </div>
      <div class="row">
        <div class="col-2">
          <div class="btn-group">
            <button class="btn btn-outline-dark" type="button" onclick={ event: Event => DataService.analyseText()}>
              analyse
            </button>
            <button class="btn btn-outline-dark dropdown-toggle" type="button" id="dropdownMenuButton" data:data-toggle="dropdown" data:aria-haspopup="true" data:aria-expanded="false">
              <span class="sr-only">Toggle Dropdown</span>
            </button>
            <div class="dropdown-menu" data:aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#" onclick={ event: Event => addText("learning")}>Learning reflection example</a>
              <a class="dropdown-item" href="#" onclick={ event: Event => addText("learning2")}>Learning reflection short example</a>
              <a class="dropdown-item" href="#" onclick={ event: Event => addText("personal")}>Personal reflection example</a>
              <a class="dropdown-item" href="#" onclick={ event: Event => addText("personal2")}>Personal reflection short example</a>
              <a class="dropdown-item" href="#" onclick={ event: Event => addText("descriptive")}>Descriptive (not reflection)</a>
              <a class="dropdown-item" href="#" onclick={ event: Event => addText("")}>Clear text</a>
            </div>
          </div>
        </div>
        <div class="col">
          <div>
            { sourceDisplay.bind }
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-1">&nbsp;</div>
        <div class="col">
          <div>
              <textarea name="text" id="editor">{DataStore.reflectionData.bind.text}</textarea>
          </div>
        </div>
      </div>
          <!--
          <script src="https://cdn.ckeditor.com/ckeditor5/10.1.0/classic/ckeditor.js"></script>
          <script>
            {"""
            $(window).bind("load", function() {
              ClassicEditor
                .create( document.querySelector( '#editor' ))
                .then( editor => {
                  console.log(editor);
                } )
                .catch( error => console.error( error ));
              });
            """}
          </script>
          -->

      </div>

  }

  @dom
  def sourceDisplay: Binding[Div] = if(DataStore.reflectionData.bind.text!="") {
    <div class="col">
      <p class="small"><b>SOURCE:&nbsp;</b>{DataStore.reflectionData.bind.source} - <a href="{DataService.reflectionData.bind.url}">{DataStore.reflectionData.bind.url}</a></p>
    </div>
  } else {
    <div></div>
  }

}
