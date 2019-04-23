import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectTypeCreationComponent } from './object-type-creation.component';

describe('ObjectTypeCreationComponent', () => {
  let component: ObjectTypeCreationComponent;
  let fixture: ComponentFixture<ObjectTypeCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObjectTypeCreationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectTypeCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
