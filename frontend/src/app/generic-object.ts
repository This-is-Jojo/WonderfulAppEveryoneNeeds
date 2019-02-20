export class GenericObject {

  constructor( name: string, parentObject: GenericObject, id?: number) {
    this.id = id;
    this.name = name;
    this.parentObject = parentObject;
  }

  id: number;
  name: string;
  parentObject: GenericObject;
}
