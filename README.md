# Slender Game Demo

## Project

This project is a toy project of a very simple 2D slender game.
You control slender with WASD and use SPACE to kill other people near you.

There is no goal at all and this isn't a proper game. It was just a toy project to practice application and game creation.
It was made back in the days I started programming.

Most of the app architecture was made to look like Unity3D's architecture but the features arn't quite as powerfull.

## Architecture

The app is divided in two parts:

 * One engine part that is game agnostic
 * One game part that implement the real gameplay

The engine part does all the heavy lifting over raw OpenGL apis.
It solves object and memory management problems, Input calculation and timing issues (to help make framerate independant gameplay code).
It also feature a very simple GUI framework (only button inputs supported yet).

## Afterthought

The engine components are simple and easy to understand.
The game is implemented by adding functionnality on top of the engine base functionnality.

This is great because most of the code is reusable from game to game.

Also the engine take care of entities and memory management with is great and make it easy to write gameplay code.

Anyway, the static approach I've taken (like it's done in unity) doesn't make it really extensible for future uses.
In fact, the engine is really monolitic and not quite easy to extends apart from where it was designed to be.
The components are too tied together and make it hard to change existing engine functionnality
(well in fact, unity got the same problem: you can't plug your own physics engine for example).

I havn't used an Injection Container because of performances issues, but it may have improved engine extensibility.

## Conclusion

It was a pet project, and I've learned some interesting things about game engines internal workings.
Feel free to use it for education purposes, but I don't recommend to use it in any production app.