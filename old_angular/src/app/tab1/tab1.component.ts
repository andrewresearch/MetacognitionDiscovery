import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {DataService} from '../shared/data.service';
import {ChartPieComponent} from '../shared/chart-pie/chart-pie.component';


@Component({
  //moduleId: module.id,
  selector: 'tab1',
    providers: [DataService],
  templateUrl: 'tab1.component.html',
  styleUrls: ['tab1.component.css'],
    encapsulation: ViewEncapsulation.None
})

export class Tab1Component implements OnInit {

  //constructor() {}
    constructor (private dataService: DataService) {}


    public detailsCollapsed = false;
    public analysed = false;
    public analysis = {};
    public category = "none";

    public reflection = {
        text: "",
        url: "",
        source: ""
    }

    public overall = {
        high: "This text has a HIGH level of evidence suggesting metacognitive activity",
        moderate: "This text has a MODERATE level of evidence suggesting metacognitive activity",
        low: "This text has a LOW level of evidence suggesting metacognitive activity"
    }



    public analyseReflection() {
        //alert("I'm working on this now. Give it a day or two and come back and try it out! Cheers, Andrew.");
        this.dataService.analyseText(this.reflection.text).subscribe(
            data => {
                this.analysis = data;
                //console.log("Analysed data: "+JSON.stringify(this.analysis));
                var sentenceCount = data.counts.sentenceCount;
                var mtSum = data.summary.metaTagSummary;
                var metaTagCount = mtSum.regulation + mtSum.experience + mtSum.knowledge;
                var score = sentenceCount / metaTagCount;
                if (score < 1.5 && mtSum.regulation >= 2 && mtSum.experience >= 2 && mtSum.knowledge >= 2) this.category = this.overall.high;
                    else if (score < 3 && mtSum.regulation >= 1 && mtSum.experience >= 1 && mtSum.knowledge >= 1) this.category = this.overall.moderate;
                    else this.category = this.overall.low;

                this.analysed = true;
            }
        );
    }

    public markupSentence(theTag) {
        return this.dataService.markupText(theTag);
    }

    public addText = function(type:String) {
        this.dataService.getText(type).subscribe(
            data => this.reflection = data
        );
    }

    clearText() {
        this.reflection = {
            text: "",
            url: "",
            source: ""
        }
    }


    reset() {
        this.analysed = false;
        //this.analysis = {};
    }

  ngOnInit() {
      
  }

}
