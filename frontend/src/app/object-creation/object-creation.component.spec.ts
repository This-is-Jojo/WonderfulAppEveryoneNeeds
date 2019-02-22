import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectCreationComponent } from './object-creation.component';

describe('ObjectCreationComponent', () => {
  let component: ObjectCreationComponent;
  let fixture: ComponentFixture<ObjectCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObjectCreationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
