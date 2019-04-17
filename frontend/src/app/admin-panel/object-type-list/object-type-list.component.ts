import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ObjectType} from '../../object-type';
import {MessageService} from '../../message.service';
import {ObjectTypeService} from '../../object-type.service';

@Component({
  selector: 'app-object-type-list',
  templateUrl: './object-type-list.component.html',
  styleUrls: ['./object-type-list.component.scss']
})
export class ObjectTypeListComponent implements OnInit {

  objectTypeList: ObjectType[];

  currentObjectType: ObjectType;

  @Output() selectObjectType = new EventEmitter<ObjectType>();

  constructor(
    private messageService: MessageService,
    private objectTypeService: ObjectTypeService,
  ) {

  }

  ngOnInit() {
    this.loadObjectTypes();
  }

  loadObjectTypes() {
    this.objectTypeService.getObjectTypesList().subscribe(
      objectTypes => this.objectTypeList = objectTypes
    );
  }

  onSelect(objectType: ObjectType) {
    this.currentObjectType = objectType;
    this.selectObjectType.emit(objectType);
  }

}
