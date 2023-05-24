import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WStarterComponent } from './w-starter.component';

describe('WStarterComponent', () => {
  let component: WStarterComponent;
  let fixture: ComponentFixture<WStarterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WStarterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WStarterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
