import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ObjectsComponent} from './objects/objects.component';
import {ObjectCreationComponent} from './object-creation/object-creation.component';
import {AdminPanelComponent} from './admin-panel/admin-panel.component';
import {ObjectTypeCreationComponent} from './admin-panel/object-type-creation/object-type-creation.component';
import {AttributeCreationComponent} from './admin-panel/attribute-creation/attribute-creation.component';

const routes: Routes = [
  { path: '', redirectTo: '/objects/10', pathMatch: 'full' },
  { path: 'objects/:objectId', component: ObjectsComponent},
  { path: 'objects/:objectId/create', component: ObjectCreationComponent},
  { path: 'admin/objectType/create', component: ObjectTypeCreationComponent},
  { path: 'admin/attribute/:objectTypeId/create', component: AttributeCreationComponent},
  { path: 'admin', component: AdminPanelComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
