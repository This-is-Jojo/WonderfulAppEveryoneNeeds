import {Component, Input, OnInit} from '@angular/core';
import {ObjectType} from '../object-type';
import {ObjectTypeService} from '../object-type.service';
import {ActivatedRoute} from '@angular/router';
import {GenericObject} from '../generic-object';

@Component({
  selector: 'app-object-type',
  templateUrl: './object-type.component.html',
  styleUrls: ['./object-type.component.scss']
})
export class ObjectTypeComponent implements OnInit {

  @Input() currentObject: GenericObject;
  objectType: ObjectType;

  constructor(
    private objectTypeService: ObjectTypeService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.getObjectType();
  }

  getObjectType() {
    this.objectTypeService.getObjectTypeById(this.currentObject.objectTypeId).subscribe( objectType => this.objectType = objectType);
  }

}
