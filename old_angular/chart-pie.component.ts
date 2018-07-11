import { Component, OnInit,Input,ElementRef,ViewEncapsulation } from '@angular/core';
import * as d3 from 'd3';

@Component({
  selector: 'chart-pie',
  templateUrl: './chart-pie.component.html',
  styleUrls: ['./chart-pie.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ChartPieComponent implements OnInit {

  @Input('chartdata') chartdata;
  //chartData = { "generalPreposition": 7, "manner": 5, "consider": 21, "outcome": 15, "selfPossessive": 32, "emotive": 4, "temporal": 2, "definite": 23, "generalPronounVerb": 116, "pertains": 90, "selfReflexive": 14, "none": 7, "possible": 8, "compare": 2, "groupPossessive": 6, "anticipate": 6, "othersPossessive": 4 };

  constructor(private elRef: ElementRef) { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.buildChart(this.elRef);
    //console.log("chartdata is: "+this.chartdata);
  }

  public buildChart(elRef: ElementRef) {

    var element = elRef.nativeElement;
    //var chart = d3.select(element.nativeElement);
    var svg = null;
    var data = null;
    //console.log("chartdata: ",this.chartdata);

    var origData =  this.chartdata; //JSON.parse(this.chartData);
    if (origData instanceof Array) {
      data = origData;
    }  else {
      // Transform the object into an array which is what d3 needs...
      data = new Array<WordCount>();

      for (var p in origData) {
        if( origData.hasOwnProperty(p)) {
          var wc = new WordCount(p,origData[p]);
          if(!wc.word.toLowerCase().includes("generalpro")) { data.push(wc); }
        }
      }
      data = data.sort(function(a,b) {
        if (a.count < b.count) return 1;
        if (a.count > b.count) return -1;
        return 0;
      });
    }
    //console.log("data: ",data);
    var boundingRect = element.parentNode.getBoundingClientRect();
    console.log("boundingRect " + JSON.stringify(boundingRect));
    var width = element.getBoundingClientRect().width;
    console.log("width: "+width);
    width = 380
    var height = width/1.6;
    var pad = width*0.18;
    var margin = {top: pad, right: pad, bottom: pad, left: pad};
    var w = width - margin.left - margin.right;
    var h = height - margin.top - margin.bottom;
    var r = w/2.5; //Math.min(w, h) / 2; //outer radius
    var ir = r *0.6;
    var labelr = r -20; // radius for label anchor
    var color = d3.schemeCategory20; //.scale.category20();
    var donut = d3.pie().value(function(d,i) { return data[i].count; });
    var arc = d3.arc().innerRadius(ir).outerRadius(r);

    svg = d3.select(element)
        .data([data])
        .append("svg:svg")
        .attr("width", w + margin.left + margin.right)
        .attr("height", h + margin.top + margin.bottom);

    var arcs = svg.selectAll("g.arc")
        .data(donut)
        .enter().append("svg:g")
        .attr("class", "arc")
        .attr("transform", "translate(" + (r + 30) + "," + r + ")");

    arcs.append("svg:path")
    //.attr("fill", function(d, i) { return color(i); })
        .attr("class", function(d,i) { return data[i].word; })
        .attr("d", arc);

    var legend = svg.selectAll(".legend")
        .data(data)
        .enter().append("g")
        .attr("class", "legend")
        .attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });

    var legendx = width - (18+(pad/2));
    legend.append("rect")
        .attr("x", legendx)
        .attr("width", 18)
        .attr("height", 14)
        .attr("class", function(d) { return d.word; }); //for CSS colors

    legend.append("text")
        .attr("x", legendx - 10)
        .attr("y", 8)
        .attr("dy", ".30em")
        .attr("class","legend-text")
        .style("text-anchor", "end")
        .text(function(d) {return d.word; });

    // Arc Labels
    var totalCount = d3.sum(data, function(d: WordCount) { return d.count;});
    var    toPercent = d3.format("0.1%");

    arcs.append("svg:text") //add a label to each slice
        .attr("transform", function(d) {
          d.innerRadius = 0;
          d.outerRadius = r;
          return "translate(" + arc.centroid(d) + ")";
        })
        .attr("text-anchor", "middle")
        .attr("class","pie-text")
        .text(function(d, i) {
          var pc = (data[i].count / totalCount);
          var result = null;
          //console.log("pc: "+pc);
          if (pc > 0.03) result = toPercent(pc);
          return result;
        });

  }

  public getSVG() {
    //return '<svg height="100" width="100"><circle cx="50" cy="50" r="40" fill="white" stroke="blue" stroke-width="1" /></svg>';
    return 'pie chart';
  }

}

class WordCount {
  word: string;
  count: number;
  constructor(w:string,c:number) {
    this.word = w;
    this.count = c;
  }
}
