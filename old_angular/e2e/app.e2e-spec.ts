import { MetacognitionUIPage } from './app.po';

describe('metacognition-ui App', function() {
  let page: MetacognitionUIPage;

  beforeEach(() => {
    page = new MetacognitionUIPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
