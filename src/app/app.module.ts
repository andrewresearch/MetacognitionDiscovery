import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import {TabsModule,DropdownModule} from 'ng2-bootstrap';
import {Tab0Component} from './tab0/tab0.component';
import {Tab1Component} from './tab1/tab1.component';
import {Tab2Component} from './tab2/tab2.component';
import {ChartPieComponent} from './shared/chart-pie/chart-pie.component';

import { MetacognitionAppComponent } from './metacognitionApp.component';

@NgModule({
  declarations: [
    MetacognitionAppComponent,
      Tab0Component,
      Tab1Component,
      Tab2Component,
      ChartPieComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
      TabsModule.forRoot(),
      DropdownModule.forRoot()
  ],
  providers: [],
  bootstrap: [MetacognitionAppComponent]
})
export class AppModule { }
