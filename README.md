## quick-start-base [![](https://jitpack.io/v/appreciated/quick-start-base.svg)](https://jitpack.io/#appreciated/quick-start-base) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/d06002d719e84a238f653e7143dd2641)](https://www.codacy.com/app/appreciated/quick-start-base?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=appreciated/quick-start-base&amp;utm_campaign=Badge_Grade) [![Coverage Status](https://coveralls.io/repos/github/appreciated/quick-start-base/badge.svg?branch=master)](https://coveralls.io/github/appreciated/quick-start-base?branch=master)

This Project is still under construction! You can already use it for Experiments but be ready for extensive API changes even in minor versions.

### Description
>Plain Vaadin applications do not have normal web page navigation as they usually run on a single page, as all Ajax applications do. (https://vaadin.com/docs/-/part/framework/advanced/advanced-navigator.html)

This Library is for Webapplications which aim to look like modern Websites with Navigation.  
It comes with a component which allows to archive something similar to the Vaadin Navigator Component (https://vaadin.com/docs/-/part/framework/advanced/advanced-navigator.html). 

The purpose is to create faster Applications which require one or multiple of the following functionalities:
- Multipage Website
- Seperate apperance for mobile and desktop
- Login (and Registration)
- Seperate Login Page or on Menubar 
- (TODO) Access Management (A basic example is given which you can ofcourse replace by your own code)
- (TODO) HTML5 History API

The Library won't do any database "stuff" you will still need to write the data back to the database.  

Antoher Advantage of this Library due to a loose coupling of design and your application logic the look of your application can be changed very easily by just switching the "Design". If the given Designs are not sufficent just fork one and fit them to your needs, you might even consider to give me a link then I can list them here. 

This Library offers a API for the Designs which then decide on their own how to represent the data you give them on instanciation.
Also this Library comes with some basic functionality for the navigation (might be replaced by the Navigator).

Currently available Designs:
* [Material Design (with Mobile Support)](https://github.com/appreciated/quick-start-material)

Additionally this Library comes with:  
- component printing (This function is limited to the visible html)
- simplyfied (but more abstracted) Dialog (Window) Creation (with Negative / Postive Buttons)
- component pager (Might be replaced by https://vaadin.com/directory#!addon/carousel, because it offers swiping)
- progress stepper
- abstracted versions of Upload and Download
