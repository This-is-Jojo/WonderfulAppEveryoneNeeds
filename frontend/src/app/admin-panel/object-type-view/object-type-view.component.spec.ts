import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectTypeViewComponent } from './object-type-view.component';

describe('ObjectTypeViewComponent', () => {
  let component: ObjectTypeViewComponent;
  let fixture: ComponentFixture<ObjectTypeViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObjectTypeViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectTypeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
