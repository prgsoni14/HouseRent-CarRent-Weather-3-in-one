import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HStarterComponent } from './h-starter.component';

describe('HStarterComponent', () => {
  let component: HStarterComponent;
  let fixture: ComponentFixture<HStarterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HStarterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HStarterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
