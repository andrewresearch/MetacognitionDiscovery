package io.nlytx

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.Event
import org.scalajs.dom.html.{Div, Table}

object Tab1_Results {

  @dom
  val detailsDisplay: Binding[Div] = {
    <div>
      <button type="button" class="close" onclick={ event: Event => DataService.toggleDetails }>
          <span>&times;&nbsp;</span>
      </button>
      <div>
        <div class="col-md-1">
          <div  class="btn btn-default" onclick={ event: Event => DataService.reset }>Edit text</div>
        </div>
        <div class="col-md-5">
          <div class="well well-sm">
            <ul>
              <li class="small">To load new text and re-run the analysis, click the <b>Edit text</b> button</li>
              <li class="small">Darker sentence numbers indicate sentences with more evidence of metacognition</li>
              <li class="small">Possible phrase tags are shown on the right. For more detail on these tags, see the <b>About</b> tab</li>
              <li class="small">Under the summary table (below right), the first chart indicates relative frequency of metacognitive tags, the second shows the relative frequency of phrase tags.</li>
              <li class="small">You can close this section by clicking the cross on the right, and reopen it by clicking the arrow that appears in it's place.</li>
            </ul>
          </div>
        </div>
        <div class="col-md-5">
          <div>
            <b class="small">Phrase Tags:</b>
            <div class="phraseTags">
              <span class="label selfReflexive">selfReflexive</span>
              <span class="label groupReflexive">groupReflexive</span>
              <span class="label selfPossessive">selfPossessive</span>
              <span class="label groupPossessive">groupPossessive</span>
              <span class="label othersPossessive">othersPossessive</span>
            </div>
            <div class="phraseTags">
              <span class="label temporal">temporal</span>
              <span class="label anticipate">anticipate</span>
              <span class="label possible">possible</span>
              <span class="label definite">definite</span>
              <span class="label outcome">outcome</span>
            </div>
            <div class="phraseTags">
              <span class="label pertains">pertains</span>
              <span class="label consider">consider</span>
              <span class="label compare">compare</span>
            </div>
            <div class="phraseTags">
              <span class="label emotive">emotive</span>
              <span class="label manner">manner</span>
            </div>
          </div>
        </div>
        <div class="col-md-1">&nbsp;</div>
      </div>
    </div>
  }

  @dom
  val sentencesTable: Binding[Table] = {
    <table class="table table-responsive table-striped table-bordered">
      <thead>
        <tr>
          <th class="col1">No.</th><th class="col2">Sentence</th><th class="col3">metaTags</th>
        </tr>
      </thead>
      <tbody>
        {
        for (sentence <- DataStore.tagsData) yield {
          <tr>
            <td><button type="button" class="'meta'+tag.metaTags.length+' btn btn-sm'">00</button></td>
            <td class="col2"><div class="sentence" >{sentence.sentence}</div></td>
            <td class="col3"><!--
              <div *ngIf=" tag && tag.metaTags && tag.metaTags.length > 0" class="tags">
                <div *ngFor="let name of tag.metaTags" [ngClass]="name+' label stack'">{{name}}</div>
              </div> -->
            </td>
          </tr>
        }

        }
      </tbody>
    </table>
  }

  @dom
  val summaryTable: Binding[Table] = {
    <table class="table table-responsive table-striped table-bordered">
      <thead>
        <tr>
          <th>Summary</th><th>&nbsp;</th>
        </tr>
      </thead>
      <tbody>
        <!-- <tr><td>Timestamp</td><td>{{analysis.counts.timestamp | date:'medium'}}</td></tr> -->
        <tr><td>Words</td><td>{DataStore.countsData.bind.wordCount.toString}</td></tr>
        <tr><td>Avg Word Length</td><td>{DataStore.countsData.bind.avgWordLength.toString} chars</td></tr>
        <tr><td>Sentences</td><td>{DataStore.countsData.bind.sentenceCount.toString}</td></tr>
        <tr><td>Avg Sentence Length</td><td>{DataStore.countsData.bind.avgSentenceLength.toString} words</td></tr>
      </tbody>
    </table>
  }

  @dom
  val charts: Binding[Div] = {
    <div class="charts"></div>
    //      <chart-pie *ngIf="analysed && analysis && analysis.summary" [chartdata]="analysis.summary.phraseTagSummary"></chart-pie>
    //      <hr />
    //      <chart-pie *ngIf="analysed && analysis && analysis.summary" [chartdata]="analysis.summary.metaTagSummary"></chart-pie>

  }

  val checked:Boolean = true

  @dom
  val dataDisplay: Binding[Div] ={
    <div>
      <hr />
      <p><input type="checkbox" data:aria-label="Toggle ngHide"></input>&nbsp;show details: </p>
        {
          if(checked) {
            <div class="check-element animate-show">
              <p class="small">{{analysis | json}}</p>
            </div>
          } else {
            <div></div>
          }
        }
    </div>
  }

  @dom
  val resultsDisplay: Binding[Div] = {
    <div>
      {
      if(DataStore.showDetails.bind) {
        <div class="row">
          { detailsDisplay.bind }
        </div>
      } else {
        <button type="button" class="close" onclick={ event: Event => DataService.toggleDetails }>
          <span>&lt;&nbsp;</span>
        </button>
      }
      }

    <div class="row">
      <div class="col-md-8">
        <div class="table-sroll">
          { sentencesTable.bind }
        </div>
        <div >
          <hr />
          <p><span class="glyphicon glyphicon-chevron-down" data:aria-hidden="true"></span>&nbsp;Scroll down for more&nbsp;</p>
        </div>
      </div>
      <div class="col-md-4">
        { summaryTable.bind }
        { charts.bind }
      </div>
    </div>
    </div>
  }
}

//
//
//{ dataDisplay.bind }