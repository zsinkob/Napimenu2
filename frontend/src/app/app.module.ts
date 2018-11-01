import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { MenulistComponent } from './napimenu/menulist/menulist.component';
import { MenuComponent } from './napimenu/menu/menu.component';
import { MenuService } from './menu.service';
import { HttpClientModule } from '@angular/common/http';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCardModule, MatToolbarModule, MatProgressSpinnerModule} from '@angular/material';
import { FooterComponent } from './napimenu/footer/footer.component';


@NgModule({
  declarations: [
    AppComponent,
    MenulistComponent,
    MenuComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatProgressSpinnerModule
  ],
  providers: [MenuService],
  bootstrap: [AppComponent]
})
export class AppModule { }
