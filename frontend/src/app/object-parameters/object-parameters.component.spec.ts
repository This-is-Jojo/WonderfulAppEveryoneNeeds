import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectParametersComponent } from './object-parameters.component';

describe('ObjectParametersComponent', () => {
  let component: ObjectParametersComponent;
  let fixture: ComponentFixture<ObjectParametersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObjectParametersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectParametersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
