import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UseruiComponent } from './userui.component';

describe('UseruiComponent', () => {
  let component: UseruiComponent;
  let fixture: ComponentFixture<UseruiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UseruiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UseruiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
