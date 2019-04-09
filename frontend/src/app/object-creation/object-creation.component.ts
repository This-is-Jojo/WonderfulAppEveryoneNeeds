import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {GenericObject} from '../generic-object';
import {ObjectsService} from '../objects.service';
import {ActivatedRoute} from '@angular/router';
import {ObjectType} from '../object-type';
import {ObjectTypeService} from '../object-type.service';

@Component({
  selector: 'app-object-creation',
  templateUrl: './object-creation.component.html',
  styleUrls: ['./object-creation.component.scss']
})
export class ObjectCreationComponent implements OnInit {

  objectTypes: ObjectType[];
  selectedObjectTypeId: number;

  constructor(
    private location: Location,
    private objectService: ObjectsService,
    private route: ActivatedRoute,
    private objectTypeService: ObjectTypeService
  ) {
  }

  ngOnInit() {
    this.loadObjectTypes();
  }

  loadObjectTypes(): void {
    this.objectTypeService.getObjectTypesList().subscribe(
      objectTypes => this.objectTypes = objectTypes
    );
  }

  selectObjectType(value): void {
    this.selectedObjectTypeId = value;
  }

  createObject(name: string): void {
    const id = +this.route.snapshot.paramMap.get('objectId');
    name = name.trim();
    const newObject: GenericObject = new GenericObject(name, id, this.selectedObjectTypeId);
    if (!name) {
      return;
    }
    this.objectService.createObject(newObject)
      .subscribe(_ => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }

}
