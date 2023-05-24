import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CStarterComponent } from './c-starter.component';

describe('CStarterComponent', () => {
  let component: CStarterComponent;
  let fixture: ComponentFixture<CStarterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CStarterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CStarterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
