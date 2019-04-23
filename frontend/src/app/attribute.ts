export class Attribute {
  attrId: number;
  objectTypeId: number;
  name: string;

  constructor(objectTypeId: number, name: string) {
    this.objectTypeId = objectTypeId;
    this.name = name;
  }

}
