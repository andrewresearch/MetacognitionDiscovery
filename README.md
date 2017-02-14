# MetacognitionUI

This is a web application written by [Andrew Gibson](http://andrewresearch.net) to support research documented in the following paper:

Gibson, A., Kirsty, K., and Bruza, P. (2016) [Towards the Discovery of Learner Metacognition From Reflective Writing.](http://epress.lib.uts.edu.au/journals/index.php/JLA/article/view/4545) *Journal of Learning Analytics*, 3(2), 22-36. doi: http://dx.doi.org/10.18608/jla.2016.32.3

> **ABSTRACT:** Modern society demands renewed attention on the competencies required to best equip students for a dynamic and uncertain future. We present exploratory work based on the premise that metacognitive and reflective competencies are essential for this task. Bringing the concepts of metacognition and reflection together into a conceptual model within which we conceived of them as both a set of similar features, and as a spectrum ranging from the unconscious inner-self through to the conscious external social self. This model was used to guide exploratory computational analysis of 6090 instances of reflective writing authored by undergraduate students. We found the conceptual model to be useful in informing the computational analysis, which in turn showed potential for automating the discovery of metacognitive activity in reflective writing, an approach that holds promise for the generation of formative feedback for students as they work towards developing core 21st century competencies.

The client application is based on my [Tabbed_UI_Template]() and is written in TypeScript using Angular2 with [angular-cli](https://github.com/angular/angular-cli).

The main code for the live demo can be found in ```/src/app/tab1``` with data access and visualisation components located in ```/src/app/shared```.

**NOTE:** This version utilises CKEditor. Before running, download latest version of ckeditor and save as ```/src/assets/ckeditor```.

Run this demo locally with ```ng serve```


