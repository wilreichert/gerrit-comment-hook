Description
===========

Listener plugin for gerrit to grab comments and pass some basic parameters to a script to do other interesting stuff.

works with 2.8.5, likely breaks with 2.9.x

I should really maven this.

I am not a real programmer.

Requirements
============

Attributes
==========

Usage
=====

compile and build with something like

```
$ cd src/main/java/
$ javac com/googlesource/gerrit/plugins/CommentListener.java -cp '../../../jars/*'
$ jar -cf gerrit-comment-hook.jar ../../../META-INF/MANIFEST.MF *
```
