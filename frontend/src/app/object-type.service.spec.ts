import { TestBed } from '@angular/core/testing';

import { ObjectTypeService } from './object-type.service';

describe('ObjectTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ObjectTypeService = TestBed.get(ObjectTypeService);
    expect(service).toBeTruthy();
  });
});
