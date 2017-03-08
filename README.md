## quick-start-base [![](https://jitpack.io/v/appreciated/quick-start-base.svg)](https://jitpack.io/#appreciated/quick-start-base) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/d06002d719e84a238f653e7143dd2641)](https://www.codacy.com/app/appreciated/quick-start-base?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=appreciated/quick-start-base&amp;utm_campaign=Badge_Grade)

This Project is still under construction! You can already use it for Experiments but be ready for extensive API changes even in minor versions.

### Description
>Plain Vaadin applications do not have normal web subpage navigation as they usually run on a single subpage, as all Ajax applications do. (https://vaadin.com/docs/-/part/framework/advanced/advanced-navigator.html)

This Library is for Webapplications which aim to look like modern Websites with Navigation.  
It comes with a component which allows to archive something similar to the Vaadin Navigator Component (https://vaadin.com/docs/-/part/framework/advanced/advanced-navigator.html). 

The purpose is to quickly create Applications which require one or multiple of the following functionalitiys:
- Multipage Website
- "Responsive" Navigation (Desktop and/or Mobile)
- Login
- Registration
- Access Management (A basic example is given which you can ofcourse replace by your own code)

The Library won't do any database "stuff" you will still need to write the user data back to the database.  

The Advantage is when using this Library you can strongly change the look of your application very easily by just switching the design class and the theme, while keeping all of your functionality in place. 
If the given Designs are not sufficent just fork one of mine and create your own, you might even consider to give me a link then I can list it here. 

This Library offers a API for the Designs which then decide on their own how to represent the data you give them on instanciation.
Also this Library comes with some basic functionality for the navigation (might be replaced by the Navigator).

Currently availible Designs:
* [Material Design (with Mobile Support)](https://github.com/appreciated/quick-start-material)

Additionally it comes with:  
- Component Printing (This function is limited to the visible html)
- Simplyfied (but more abstracted) Dialog (Window) Creation (with Negative / Postive Buttons)
- Component Pager (Might be replaced by https://vaadin.com/directory#!addon/carousel, because it offers mature especially swiping)
- Object to String Serialization (easy)

Also is comes with more abstracted versions of the following components
- Upload 
- Download
