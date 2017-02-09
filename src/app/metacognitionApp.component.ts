import { Component } from '@angular/core';


@Component({
  moduleId: module.id,
  selector: 'metacognition-app',
    //directives: [TabsModule,Tab0Component,Tab1Component,Tab2Component],
  templateUrl: 'metacognitionApp.component.html',
  styleUrls: ['metacognitionApp.component.css'] // styles: ['./app/app.component.css','./assets/css/doc-styles.css']
})

export class MetacognitionAppComponent {
  title = 'Towards the Discovery of Learner Metacognition From Reflective Writing';
  tabNames = {
    tab0: 'About',
    tab1: 'Live Demo',
    tab2: 'Tech Info'
  }
  footer = "\u24D2 Andrew Gibson 2017";
}


