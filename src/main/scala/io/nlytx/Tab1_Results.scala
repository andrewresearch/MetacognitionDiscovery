package io.nlytx

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.Event
import org.scalajs.dom.html.{Button, Div, Table}

object Tab1_Results {

  @dom
  val resultsDisplay: Binding[Div] = {
    <div class="container-fluid">
      { infoSection.bind }
      { resultsSection.bind }
    </div>
  }

  //--------- INFO SECTION -------------------------//
  @dom
  lazy val infoSection: Binding[Div] = {
    <div class="row" id="info-section">
      <div class="col-1"> { editButton.bind }</div>
      <div class="col">{ if(DataStore.showDetails.bind) { instructions.bind } else {<div></div>}}</div>
      <div class="col">{ if(DataStore.showDetails.bind) { legend.bind } else {<div></div>}}</div>
      <div class="col-1"> { toggleButton.bind }</div>
    </div>
  }

  @dom
  lazy val editButton: Binding[Button] = {
    <button  class="btn btn-outline-dark" onclick={ event: Event => DataService.reset }>Edit text</button>
  }

  @dom
  lazy val instructions: Binding[Div] = {
    <div class="card bg-faded" id="instructions">
      <ul>
        <li class="small">To load new text and re-run the analysis, click the <b>Edit text</b> button</li>
        <li class="small">Darker sentence numbers indicate sentences with more evidence of metacognition</li>
        <li class="small">Possible phrase tags are shown on the right. For more detail on these tags, see the <b>About</b> tab</li>
        <li class="small">Under the summary table (below right), the first chart indicates relative frequency of metacognitive tags, the second shows the relative frequency of phrase tags.</li>
        <li class="small">You can close this section by clicking the cross on the right, and reopen it by clicking the arrow that appears in it's place.</li>
      </ul>
    </div>
  }

  @dom
  lazy val legend: Binding[Div] = {
    <div>
      <b class="small">Phrase Tags:</b>
      <div class="phraseTags">
        <span class="badge badge-pill selfReflexive">selfReflexive</span>
        <span class="badge badge-pill groupReflexive">groupReflexive</span>
        <span class="badge badge-pill selfPossessive">selfPossessive</span>
        <span class="badge badge-pill groupPossessive">groupPossessive</span>
        <span class="badge badge-pill othersPossessive">othersPossessive</span>
      </div>
      <div class="phraseTags">
        <span class="badge badge-pill temporal">temporal</span>
        <span class="badge badge-pill anticipate">anticipate</span>
        <span class="badge badge-pill possible">possible</span>
        <span class="badge badge-pill definite">definite</span>
        <span class="badge badge-pill outcome">outcome</span>
      </div>
      <div class="phraseTags">
        <span class="badge badge-pill pertains">pertains</span>
        <span class="badge badge-pill consider">consider</span>
        <span class="badge badge-pill compare">compare</span>
      </div>
      <div class="phraseTags">
        <span class="badge badge-pill emotive">emotive</span>
        <span class="badge badge-pill manner">manner</span>
      </div>
    </div>
  }

  @dom
  lazy val toggleButton: Binding[Button] = {
    <button type="button" class="close" onclick={ event: Event => DataService.toggleDetails }><span>&times;&nbsp;</span></button>
  }





  //--------- RESULTS SECTION -------------------------//
  @dom
  lazy val resultsSection: Binding[Div] = {
    <div class="row">
      <div class="col-8"> {sentencesScroll.bind } </div>
      <div class="col-4"> { summaryColumn.bind } </div>
    </div>
  }


  @dom
  lazy val sentencesScroll: Binding[Div] = {
    <div class="table-sroll">
      { sentencesTable.bind }
      <!--<hr />
      <p><span class="glyphicon glyphicon-chevron-down" data:aria-hidden="true"></span>&nbsp;Scroll down for more&nbsp;</p>-->
    </div>

  }

  @dom
  lazy val summaryColumn: Binding[Div] = {
    <div>
      { summaryTable.bind }
      { charts.bind }
      { dataDisplay.bind }
    </div>
  }



  @dom
  lazy val sentencesTable: Binding[Table] = {
    <table class="table table-striped table-bordered">
      <thead>
        <tr>
          <th data:scope="col" class="col1">No.</th><th data:scope="col" class="col2">Sentence</th><th data:scope="col" class="col3">metaTags</th>
        </tr>
      </thead>
      <tbody>
        {
        for (sentence <- DataStore.tagsData) yield {
          <tr>
            <th data:scope="row"><div class="index" innerHTML={ MarkupService.markupIndex(sentence) }></div></th>
            <td class="col2"><div class="sentence" innerHTML={ MarkupService.markupSentence(sentence) }></div></td>
            <td class="col3"><div class="tags" innerHTML={ MarkupService.markupMetaTags(sentence) }></div></td>
          </tr>
        }

        }
      </tbody>
    </table>
  }


  @dom
  lazy val summaryTable: Binding[Table] = {
    <table class="table table-striped table-bordered">
      <thead>
        <tr>
          <th data:scope="col">Summary</th><th data:scope="col">&nbsp;</th>
        </tr>
      </thead>
      <tbody>
        <!-- <tr><td>Timestamp</td><td>{{analysis.counts.timestamp | date:'medium'}}</td></tr> -->
        <tr><th data:scope="row">Word count</th><td>{ MarkupService.numFormat(DataStore.countsData.bind.wordCount) } words</td></tr>
        <tr><th data:scope="row">Avg Word Length</th><td>{ MarkupService.numFormat(DataStore.countsData.bind.avgWordLength) } characters</td></tr>
        <tr><th data:scope="row">Sentence count</th><td>{ MarkupService.numFormat(DataStore.countsData.bind.sentenceCount) } sentences</td></tr>
        <tr><th data:scope="row">Avg Sentence Length</th><td>{ MarkupService.numFormat(DataStore.countsData.bind.avgSentenceLength) } words</td></tr>
      </tbody>
    </table>
  }

  @dom
  lazy val charts: Binding[Div] = {
    <div class="charts">&nbsp;</div>
    //      <chart-pie *ngIf="analysed && analysis && analysis.summary" [chartdata]="analysis.summary.phraseTagSummary"></chart-pie>
    //      <hr />
    //      <chart-pie *ngIf="analysed && analysis && analysis.summary" [chartdata]="analysis.summary.metaTagSummary"></chart-pie>

  }


  @dom
  lazy val dataDisplay: Binding[Div] ={
    <div>
      { dataButton.bind }
      {
        if(DataStore.showData.bind) {
          <div class="card bg-faded check-element animate-show" id="raw-data">
            <p class="small"> {DataStore.queryData.bind.toString} </p>
            <p class="small"> {DataStore.countsData.bind.toString} </p>
            <p class="small"> {DataStore.summaryData.bind.toString} </p>
            <p class="small"> {DataStore.tagsData.bind.toString} </p>
          </div>
        } else {
          <div></div>
        }
      }
    </div>
  }

  @dom
  lazy val dataButton: Binding[Button] = {
    <button class="btn btn-outline-dark btn-sm" onclick={ event: Event => DataService.toggleData }>raw analytics</button>
  }

}