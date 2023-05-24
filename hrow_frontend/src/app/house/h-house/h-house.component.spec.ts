import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HHouseComponent } from './h-house.component';

describe('HHouseComponent', () => {
  let component: HHouseComponent;
  let fixture: ComponentFixture<HHouseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HHouseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HHouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
