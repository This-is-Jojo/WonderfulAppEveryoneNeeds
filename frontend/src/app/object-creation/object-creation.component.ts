import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {GenericObject} from '../generic-object';
import {ObjectsService} from '../objects.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-object-creation',
  templateUrl: './object-creation.component.html',
  styleUrls: ['./object-creation.component.scss']
})
export class ObjectCreationComponent implements OnInit {

  constructor(
    private location: Location,
    private objectService: ObjectsService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
  }

  createObject(name: string): void {
    const id = +this.route.snapshot.paramMap.get('id');
    name = name.trim();
    const newObject: GenericObject = new GenericObject(name, id);
    if (!name) { return; }
    this.objectService.createObject(newObject)
      .subscribe(_ => this.goBack());
    // this.goBack();
  }

  goBack(): void {
    this.location.back();
  }

}
