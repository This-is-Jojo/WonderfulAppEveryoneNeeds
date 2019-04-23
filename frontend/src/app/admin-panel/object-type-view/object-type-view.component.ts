import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ObjectType} from '../../object-type';
import {AttributeService} from '../../attribute.service';
import {Attribute} from '../../attribute';
import {GenericObject} from '../../generic-object';

@Component({
  selector: 'app-object-type-view',
  templateUrl: './object-type-view.component.html',
  styleUrls: ['./object-type-view.component.scss']
})
export class ObjectTypeViewComponent implements OnInit, OnChanges {

  @Input() currentObjectType: ObjectType;
  attributes: Attribute[];
  currentAttribute: Attribute;

  constructor(
    private attributeService: AttributeService
  ) {
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if (this.currentObjectType) {
      this.loadAttributes();
    }
  }

  onSelect(attr: Attribute) {
    this.currentAttribute = attr;
  }

  delete(attribute: Attribute): void {
    this.attributeService.deleteAttribute(attribute.attrId).subscribe(_ => this.attributes.splice(this.attributes.indexOf(attribute), 1));
  }

  loadAttributes() {
    this.attributeService.getAttributesByObjectType(this.currentObjectType.objectTypeId).subscribe(
      output => this.attributes = output
    );
  }

}
