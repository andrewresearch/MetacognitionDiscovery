import {
  beforeEachProviders,
  describe,
  expect,
  it,
  inject
} from '@angular/core/testing';
import { JlaMetacognitionAppComponent } from '../app/jla-metacognition.component';

beforeEachProviders(() => [JlaMetacognitionAppComponent]);

describe('App: JlaMetacognition', () => {
  it('should create the app',
      inject([JlaMetacognitionAppComponent], (app: JlaMetacognitionAppComponent) => {
    expect(app).toBeTruthy();
  }));

  it('should have as title \'jla-metacognition works!\'',
      inject([JlaMetacognitionAppComponent], (app: JlaMetacognitionAppComponent) => {
    expect(app.title).toEqual('jla-metacognition works!');
  }));
});
