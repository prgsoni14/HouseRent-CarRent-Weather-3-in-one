import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CAdminComponent } from './c-admin.component';

describe('CAdminComponent', () => {
  let component: CAdminComponent;
  let fixture: ComponentFixture<CAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
