/**
 * Created by andrew on 6/06/2016.
 */

import { Injectable }     from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class DataService {
   constructor (private http: Http) {}

    getText (type:string) {
        //let url = '/2016/metacognition/assets/data/'+type+'.json';
        let url = './assets/data/'+type+'.json';
        //console.log("Fetching file: "+url);
        return this.http.get(url).map((res:Response) => res.json());
    }

    analyseText(text:string) {
        let url = 'http://alasiapi.nlytx.io/reflection';
        //console.log("Analysing with: "+url);
        return this.http.post(url,text).map((res:Response) => res.json());
    }

    markupText(text) {
        if(text) {
            let phrases = text.phrases;
            //console.log("phrases: " + JSON.stringify(phrases));
            let markedUp = text.sentence;

            let escape = function(s) {
                return s.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
            };

            for (let phrase of phrases) {
                //console.log("phrase: " + JSON.stringify(phrase));
                let phraseParts = phrase.split('[');
                let phraseText = phraseParts[0];
                let phraseType = phraseParts[1].split(',')[0];
                markedUp = markedUp.replace(new RegExp(escape(phraseText), "ig"), "<span class=\"" + phraseType + "\">" + phraseText + "</span>");
                //console.log("markedUp: " + markedUp);
            }
            return markedUp;
        } else {
            return "";
        }
    }
    
}

