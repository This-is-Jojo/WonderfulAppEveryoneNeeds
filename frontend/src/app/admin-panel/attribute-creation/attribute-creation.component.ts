import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {AttributeService} from '../../attribute.service';
import {Attribute} from '../../attribute';

@Component({
  selector: 'app-attribute-creation',
  templateUrl: './attribute-creation.component.html',
  styleUrls: ['./attribute-creation.component.scss']
})
export class AttributeCreationComponent implements OnInit {


  constructor(
    private location: Location,
    private route: ActivatedRoute,
    private attributeService: AttributeService
  ) {
  }

  ngOnInit() {
  }

  createAttribute(name: string): void {
    const objectTypeId = +this.route.snapshot.paramMap.get('objectTypeId');

    name = name.trim();
    const newAttr: Attribute = new Attribute(objectTypeId, name);
    if (!name) {
      return;
    }

    this.attributeService.createAttribute(newAttr)
      .subscribe(_ => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }


}
