export class GenericObject {

  constructor( name: string, parentObject: GenericObject, id?: number) {
    this.id = id;
    this.name = name;
    this.parentObject = parentObject;
    this.parentId = parentObject != null ?  parentObject.id : null;
  }

  id: number;
  name: string;
  parentObject: GenericObject;
  parentId: number;
}
