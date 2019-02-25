import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MessagesComponent } from './messages/messages.component';
import { ObjectsComponent } from './objects/objects.component';

import {HttpClientModule} from '@angular/common/http';
import { ObjectCreationComponent } from './object-creation/object-creation.component';

@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    ObjectsComponent,
    ObjectCreationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
