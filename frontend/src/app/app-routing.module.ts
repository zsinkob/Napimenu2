import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MenulistComponent } from './napimenu/menulist/menulist.component';

const routes: Routes = [
  {
    path: '',
    children: [{
      path: '',
      component: MenulistComponent
    }]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
