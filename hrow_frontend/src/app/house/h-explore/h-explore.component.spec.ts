import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HExploreComponent } from './h-explore.component';

describe('HExploreComponent', () => {
  let component: HExploreComponent;
  let fixture: ComponentFixture<HExploreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HExploreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HExploreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
