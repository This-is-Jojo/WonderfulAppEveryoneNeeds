import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AttributeService} from '../attribute.service';
import {ParametersService} from '../parameters.service';
import {MessageService} from '../message.service';
import {ObjectsService} from '../objects.service';

@Component({
  selector: 'app-object-parameters',
  templateUrl: './object-parameters.component.html',
  styleUrls: ['./object-parameters.component.scss']
})
export class ObjectParametersComponent implements OnInit {

  parametersMap: Map<number, string>;

  attributesMap: Map<number, string>;

  constructor(
    private parametersService: ParametersService,
    private attributesService: AttributeService,
    private objectService: ObjectsService,
    private route: ActivatedRoute,
    private messageService: MessageService) {
  }

  ngOnInit() {
    this.loadAttributes();
  }

  loadAttributes(): void {
    const id = +this.route.snapshot.paramMap.get('objectId');
    this.objectService.getObjectById(id)
      .subscribe(object => this.attributesService.getAttributesMap(object.objectTypeId).subscribe(map => {
        this.attributesMap = new Map<number, string>();
        Object.entries(map).map(entry => this.attributesMap.set(+entry[0], entry[1]));
        this.loadParameters();
      }));
  }

  loadParameters(): void {
    const id = +this.route.snapshot.paramMap.get('objectId');
    this.parametersService.getParametersMap(id)
      .subscribe(map => {
        this.parametersMap = new Map<number, string>();
        Object.entries(map).map(entry => this.parametersMap.set(+entry[0], entry[1]));
      });
  }

  private log(message: string) {
    this.messageService.add(`ObjectParametersComponent: ${message}`);
  }

}
