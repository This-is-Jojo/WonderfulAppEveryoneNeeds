import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {AttributeService} from '../attribute.service';
import {ParametersService} from '../parameters.service';
import {MessageService} from '../message.service';

@Component({
  selector: 'app-object-parameters',
  templateUrl: './object-parameters.component.html',
  styleUrls: ['./object-parameters.component.scss']
})
export class ObjectParametersComponent implements OnInit {

  parametersMap: Map<number, string>;
  parametersNamesToValuesMap: Map<string, string>;

  constructor(
    private parametersService: ParametersService,
    private attributesService: AttributeService,
    private route: ActivatedRoute,
    private messageService: MessageService) {
  }

  ngOnInit() {
    this.loadParameters();
  }

  loadParameters(): void {
    const id = +this.route.snapshot.paramMap.get('objectId');
    this.parametersService.getParametersMap(id)
      .subscribe(map => {
        this.parametersMap = map;
        this.loadAttrNames(map);
      });
  }

  loadAttrNames(parameters: Map<number, string>): void {
    this.parametersNamesToValuesMap = new Map<string, string>();
    Object.entries(parameters).forEach(([attrId, value]) =>
      this.attributesService.getAttributeName(+attrId)
        .subscribe(attrName => this.parametersNamesToValuesMap.set(attrName.response, value)));
  }

  private log(message: string) {
    this.messageService.add(`ObjectParametersComponent: ${message}`);
  }

}
