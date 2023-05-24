import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HAdminComponent } from './h-admin.component';

describe('HAdminComponent', () => {
  let component: HAdminComponent;
  let fixture: ComponentFixture<HAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
