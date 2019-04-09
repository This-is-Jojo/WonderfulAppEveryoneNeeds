export class GenericObject {

  constructor( name: string, parentId?: number, objectTypeId?: number) {
    this.name = name;
    this.parentId = parentId;
    this.objectTypeId = objectTypeId;
  }

  objectId: number;
  name: string;
  parentId: number;
  objectTypeId: number;
}
