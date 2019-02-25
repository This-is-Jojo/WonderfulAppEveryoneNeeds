export class GenericObject {

  constructor( name: string, parentId?: number, objectId?: number) {
    this.objectId = objectId;
    this.name = name;
    this.parentId = parentId;
  }

  objectId: number;
  name: string;
  parentId: number;
}
