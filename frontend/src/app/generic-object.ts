export class GenericObject {

  constructor( name: string, parentId?: number, id?: number) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
  }

  id: number;
  name: string;
  parentId: number;
}
