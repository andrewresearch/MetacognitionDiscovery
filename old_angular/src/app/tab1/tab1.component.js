"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var dropdown_1 = require('ng2-bootstrap/components/dropdown');
var data_service_1 = require('../shared/data.service');
var chart_pie_component_1 = require('../shared/chart-pie/chart-pie.component');
var Tab1Component = (function () {
    //constructor() {}
    function Tab1Component(dataService) {
        this.dataService = dataService;
        this.detailsCollapsed = false;
        this.analysed = false;
        this.analysis = {};
        this.category = "none";
        this.reflection = {
            text: "",
            url: "",
            source: ""
        };
        this.overall = {
            high: "This text has a HIGH level of evidence suggesting metacognitive activity",
            moderate: "This text has a MODERATE level of evidence suggesting metacognitive activity",
            low: "This text has a LOW level of evidence suggesting metacognitive activity"
        };
        this.addText = function (type) {
            var _this = this;
            this.dataService.getText(type).subscribe(function (data) { return _this.reflection = data; });
        };
    }
    Tab1Component.prototype.analyseReflection = function () {
        var _this = this;
        //alert("I'm working on this now. Give it a day or two and come back and try it out! Cheers, Andrew.");
        this.dataService.analyseText(this.reflection.text).subscribe(function (data) {
            _this.analysis = data;
            //console.log("Analysed data: "+JSON.stringify(this.analysis));
            var sentenceCount = data.counts.sentenceCount;
            var mtSum = data.summary.metaTagSummary;
            var metaTagCount = mtSum.regulation + mtSum.experience + mtSum.knowledge;
            var score = sentenceCount / metaTagCount;
            if (score < 1.5 && mtSum.regulation >= 2 && mtSum.experience >= 2 && mtSum.knowledge >= 2)
                _this.category = _this.overall.high;
            else if (score < 3 && mtSum.regulation >= 1 && mtSum.experience >= 1 && mtSum.knowledge >= 1)
                _this.category = _this.overall.moderate;
            else
                _this.category = _this.overall.low;
            _this.analysed = true;
        });
    };
    Tab1Component.prototype.markupSentence = function (theTag) {
        return this.dataService.markupText(theTag);
    };
    Tab1Component.prototype.clearText = function () {
        this.reflection = {
            text: "",
            url: "",
            source: ""
        };
    };
    Tab1Component.prototype.reset = function () {
        this.analysed = false;
        //this.analysis = {};
    };
    Tab1Component.prototype.ngOnInit = function () {
    };
    Tab1Component = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'tab1',
            directives: [dropdown_1.DROPDOWN_DIRECTIVES, chart_pie_component_1.ChartPieComponent],
            providers: [data_service_1.DataService],
            templateUrl: 'tab1.component.html',
            styleUrls: ['tab1.component.css'],
            encapsulation: core_1.ViewEncapsulation.None
        })
    ], Tab1Component);
    return Tab1Component;
}());
exports.Tab1Component = Tab1Component;
//# sourceMappingURL=tab1.component.js.map