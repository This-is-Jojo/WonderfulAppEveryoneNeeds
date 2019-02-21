import {Component, Input, OnInit, Output} from '@angular/core';
import {GenericObject} from '../generic-object';
import {ObjectsService} from '../objects.service';
import {ActivatedRoute, Router} from '@angular/router';
import { Location } from '@angular/common';
import {Observable, of} from 'rxjs';

@Component({
  selector: 'app-objects',
  templateUrl: './objects.component.html',
  styleUrls: ['./objects.component.scss']
})
export class ObjectsComponent implements OnInit {
  currentObject: GenericObject;
  parentObject: GenericObject;
  childrenObjects: GenericObject[];

  constructor(
    private objectService: ObjectsService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit() {
    this.getObject();
    // of(this.currentObject).pipe().subscribe(object => this.parentObject = object.parentObject);
    // this.parentObject = this.currentObject.parentObject;
    // this.childrenObjects = this.objectService.getChildrenObjects(this.currentObject.id);
  }

  getObject(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.objectService.getObjectById(id)
      .subscribe(object => this.fillValues(object));
  }

  getChildren(parentObject: GenericObject): void {
    this.objectService.getChildrenObjects(parentObject)
      .subscribe(objects => this.childrenObjects = objects);
  }


  fillValues(object: GenericObject): void {
    this.currentObject = object;
    this.parentObject = object.parentObject;
    this.getChildren(object);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.objectService.updateObject(this.currentObject)
      .subscribe(() => this.goBack());
  }

  createObject(name: string): void {
    name = name.trim();
    const newObject: GenericObject = new GenericObject(name, this.parentObject);
    if (!name) { return; }
    this.objectService.createObject(newObject);
  }

  delete(object: GenericObject): void {
    this.objectService.deleteObject(object).subscribe();
  }

}
