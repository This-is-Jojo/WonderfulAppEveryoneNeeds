import {ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {AttributeService} from '../attribute.service';
import {ParametersService} from '../parameters.service';
import {MessageService} from '../message.service';
import {GenericObject} from '../generic-object';

@Component({
  selector: 'app-object-parameters',
  templateUrl: './object-parameters.component.html',
  styleUrls: ['./object-parameters.component.scss']
})
export class ObjectParametersComponent implements OnInit {

  @Input() currentObject: GenericObject;
  @Input() editingEnabled: boolean;

  parameters: any;

  attributesMap: Map<number, string>;

  constructor(
    private parametersService: ParametersService,
    private attributesService: AttributeService,
    private route: ActivatedRoute,
    private messageService: MessageService) {
  }

  ngOnInit() {
    this.loadAttributes();
  }

  loadAttributes(): void {
    this.attributesService.getAttributesMap(this.currentObject.objectTypeId).subscribe(map => {
      this.attributesMap = new Map<number, string>();
      Object.entries(map).map(entry => this.attributesMap.set(+entry[0], entry[1]));
      this.loadParameters();
    });
  }

  loadParameters(): void {
    const id = +this.route.snapshot.paramMap.get('objectId');
    this.parametersService.getParametersMap(id)
      .subscribe(map => {
        this.parameters = map;
      });
  }

  updateParameters(): void {
    this.parametersService.updateParametersMap(this.currentObject.objectId, this.parameters)
      .subscribe();
  }

  private log(message: string) {
    this.messageService.add(`ObjectParametersComponent: ${message}`);
  }

}
