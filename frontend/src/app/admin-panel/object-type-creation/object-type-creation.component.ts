import {Component, OnInit} from '@angular/core';
import {ObjectType} from '../../object-type';
import {Location} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {ObjectTypeService} from '../../object-type.service';

@Component({
  selector: 'app-object-type-creation',
  templateUrl: './object-type-creation.component.html',
  styleUrls: ['./object-type-creation.component.scss']
})
export class ObjectTypeCreationComponent implements OnInit {

  constructor(
    private location: Location,
    private route: ActivatedRoute,
    private objectTypeService: ObjectTypeService
  ) {
  }

  ngOnInit() {
  }

  createObjectType(name: string): void {
    name = name.trim();
    const newObject: ObjectType = new ObjectType(name);
    if (!name) {
      return;
    }

    this.objectTypeService.createObjectType(newObject)
      .subscribe(_ => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }

}
