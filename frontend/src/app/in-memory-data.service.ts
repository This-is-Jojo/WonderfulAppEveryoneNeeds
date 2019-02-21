import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Injectable } from '@angular/core';
import {GenericObject} from './generic-object';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const objects: GenericObject[] = [];
    const top = new GenericObject( 'Top', null, 10);
    const documents = new GenericObject('Documents', top, 100);
    const inventory = new GenericObject( 'Inventory', top, 200);
    const document = new GenericObject( 'Document 1', documents, 110);
    const entity = new GenericObject( 'Entity', inventory, 210);
    const document2 = new GenericObject( 'Document 2', documents, 120);
    const document3 = new GenericObject( 'File', documents, 130);

    objects.push(top);
    objects.push(documents);
    objects.push(inventory);
    objects.push(document);
    objects.push(entity);
    objects.push(document2);
    objects.push(document3);

    return {objects};
  }

  // Overrides the genId method to ensure that a hero always has an id.
  // If the heroes array is empty,
  // the method below returns the initial number (11).
  // if the heroes array is not empty, the method below returns the highest
  // hero id + 1.
  genId(objects: GenericObject[]): number {
    return objects.length > 0 ? Math.max(...objects.map(object => object.id)) + 1 : 11;
  }
}
