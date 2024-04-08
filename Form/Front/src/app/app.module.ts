import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClient, HttpClientModule, withFetch} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,


  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,

  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
