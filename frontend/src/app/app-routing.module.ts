import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ObjectsComponent} from './objects/objects.component';
import {ObjectCreationComponent} from './object-creation/object-creation.component';

const routes: Routes = [
  { path: '', redirectTo: '/objects/10', pathMatch: 'full' },
  { path: 'objects/:id', component: ObjectsComponent},
  { path: 'objects/:id/create', component: ObjectCreationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
