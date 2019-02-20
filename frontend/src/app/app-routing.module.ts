import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ObjectsComponent} from './objects/objects.component';

const routes: Routes = [
  { path: '', redirectTo: '/objects/10', pathMatch: 'full' },
  { path: 'objects/:id', component: ObjectsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
