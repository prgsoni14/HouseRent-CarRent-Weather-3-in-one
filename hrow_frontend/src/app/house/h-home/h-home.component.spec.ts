import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HHomeComponent } from './h-home.component';

describe('HHomeComponent', () => {
  let component: HHomeComponent;
  let fixture: ComponentFixture<HHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
