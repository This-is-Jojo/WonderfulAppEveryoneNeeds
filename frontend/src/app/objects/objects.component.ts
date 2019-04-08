import {Component, Input, OnInit, Output, ViewChild} from '@angular/core';
import {GenericObject} from '../generic-object';
import {ObjectsService} from '../objects.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {MessageService} from '../message.service';
import {ObjectParametersComponent} from '../object-parameters/object-parameters.component';

@Component({
  selector: 'app-objects',
  templateUrl: './objects.component.html',
  styleUrls: ['./objects.component.scss']
})
export class ObjectsComponent implements OnInit {
  currentObject: GenericObject;
  parentObject: GenericObject;
  childrenObjects: GenericObject[];
  editingEnabled: boolean;
  @ViewChild(ObjectParametersComponent) parametersComponent;

  constructor(
    private objectService: ObjectsService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router,
    private messageService: MessageService
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit() {
    this.editingEnabled = false;
    this.getObject();
  }

  getObject(): void {
    const id = +this.route.snapshot.paramMap.get('objectId');
    this.objectService.getObjectById(id)
      .subscribe(object => this.fillValues(object));
  }

  getParentObject(parentId: number): void {
    this.objectService.getObjectById(parentId)
      .subscribe(object => this.parentObject = object);
  }

  getChildren(parentObject: GenericObject): void {
    this.objectService.getChildrenObjects(parentObject)
      .subscribe(objects => this.childrenObjects = objects);
  }


  fillValues(object: GenericObject): void {
    this.currentObject = object;
    if (object.parentId) {
      this.getParentObject(object.parentId);
    }
    this.getChildren(object);
  }

  goBack(): void {
    this.location.back();
  }

  switchEditing(): void {
    this.editingEnabled = !this.editingEnabled;
  }

  save(): void {
    this.objectService.updateObject(this.currentObject)
      .subscribe();
    this.parametersComponent.updateParameters();
  }

  delete(object: GenericObject): void {
    this.objectService.deleteObject(object).subscribe(_ => this.childrenObjects.splice(this.childrenObjects.indexOf(object), 1));
  }

  private log(message: string) {
    this.messageService.add(`Object Component: ${message}`);
  }
}
