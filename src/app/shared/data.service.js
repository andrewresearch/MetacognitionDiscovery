/**
 * Created by andrew on 6/06/2016.
 */
"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
require('rxjs/add/operator/map');
require('rxjs/add/operator/catch');
var DataService = (function () {
    function DataService(http) {
        this.http = http;
    }
    DataService.prototype.getText = function (type) {
        var url = '/2016/metacognition/assets/data/' + type + '.json';
        //console.log("Fetching file: "+url);
        return this.http.get(url).map(function (res) { return res.json(); });
    };
    DataService.prototype.analyseText = function (text) {
        var url = 'http://alasiapi.nlytx.io/reflection';
        //console.log("Analysing with: "+url);
        return this.http.post(url, text).map(function (res) { return res.json(); });
    };
    DataService.prototype.markupText = function (text) {
        if (text) {
            var phrases = text.phrases;
            //console.log("phrases: " + JSON.stringify(phrases));
            var markedUp = text.sentence;
            var escape = function (s) {
                return s.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
            };
            for (var _i = 0, phrases_1 = phrases; _i < phrases_1.length; _i++) {
                var phrase = phrases_1[_i];
                //console.log("phrase: " + JSON.stringify(phrase));
                var phraseParts = phrase.split('[');
                var phraseText = phraseParts[0];
                var phraseType = phraseParts[1].split(',')[0];
                markedUp = markedUp.replace(new RegExp(escape(phraseText), "ig"), "<span class=\"" + phraseType + "\">" + phraseText + "</span>");
            }
            return markedUp;
        }
        else {
            return "";
        }
    };
    DataService = __decorate([
        core_1.Injectable()
    ], DataService);
    return DataService;
}());
exports.DataService = DataService;
//# sourceMappingURL=data.service.js.map