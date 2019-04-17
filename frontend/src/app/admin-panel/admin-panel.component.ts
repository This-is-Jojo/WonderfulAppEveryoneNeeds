import {Component, OnInit, ViewChild} from '@angular/core';
import {ObjectType} from '../object-type';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit {

  currentObjectType: ObjectType;

  constructor() {
  }

  ngOnInit() {
  }

  onSelectObjectType(objectType: ObjectType) {
    this.currentObjectType = objectType;
  }

}
