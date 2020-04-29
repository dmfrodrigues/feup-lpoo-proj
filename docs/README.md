# LPOO_60 - The Cursed Catacombs
#### (Codename: pacman)

<p>
  <img src="https://github.com/FEUP-LPOO/lpoo-2020-g60/workflows/gradlew%20test/badge.svg" alt="gradlew test">
</p>

You are a noble knight seeking to assist Her Majesty in cleansing the capital's catacombs from the many monsters that inhabit it. Your mission is to kill all monsters and collect as many coins as possible across multiple levels.

This project was developed for LPOO <sup>2019</sup>⁄<sub>20</sub> by:
- [Diogo Rodrigues](https://github.com/dmfrodrigues) ([dmfrodrigues2000@gmail.com](mailto:dmfrodrigues2000@gmail.com)/[up201806429@fe.up.pt](mailto:up201806429@fe.up.pt))
- [João Matos](https://github.com/MechJM) ([up201703884@fe.up.pt](mailto:up201703884@fe.up.pt))

## Description

Her Majesty the Queen has requested the assistance of a noble knight to cleanse the catacombs of the Kingdom's capital of the terrible monsters that have been haunting her subjects for centuries. Unfortunately, because people are scared of the monsters that occasionally escape the catacombs, productivity is low and the Kingdom's coffers are empty (otherwise she would have hired a professional ghost-hunter team), but whoever answers Her Majesty's call will have rights over any treasure found in the catacombs.

Having accepted the challenge, your goal is to kill all monsters, while collecting as many coins as possible so at to guarantee an alternative to the rather underfinanced Kingdom's Social Security system for yourself.

The catacombs are full of treasures from ancient times, as well as weapons from those who once ventured into the depths of the city but never returned.

- **Q: Do what?** A: Kill monsters and collect coins.
- **Q: With what?** A: You start with a knife, but you can catch other weapons and bullets in the catacombs.
- **Q: How do I leave the catacombs?** A: You may take a rest or leave at any time, but your mission is only over once you cleanse all the catacombs of the city.
- **Q: What if I die?** A: Her Majesty has been personally appointed by God, if you die on duty He will give you a second chance. 

### Media

#### Gallery

| 2020-04-26 |
|------------|
|![2020-04-26-image](images/2020-04-26_21-31-01.png)|

#### Animations

| 2020-04-28 |
|------------|
|![2020-04-28-animation](images/pacman-20200428-202310.gif)|

## Specifications

### Elements

#### Abstract classes

- **Tall element**: no other elements can move over it.
- **Obstacle element**: two obstacles cannot be in the same position.
- **Movable element**: can be moved, one tile at a time, or stay still.
- **Dynamic element**: can perform various actions.
    - **Autonomous element**: its actions are autonomous.
        - **Follower element**: follows the hero.
    - **Melee element**: can attack on a short range.
    - **Ranged element**: can attack on a certain range.
- **Oriented element**: has a direction.
- **Living element**: has a life.
- **Shooter element**: can shoot stuff.
- **Projectile element**: can be shot.

#### Concrete classes

- **Hero**: is obstacle, movable, melee, ranged, oriented, living.
- **Enemy**: is autonomous and living.
    - **Guard**: is movable, melee and oriented. Has melee attack.
    - **Ghost**: is movable, follower, melee and oriented. Has melee attack.
    - **Spider**: is movable, follower, melee and oriented. Has melee stun.
    - **Mummy**: is movable, follower, melee, ranged and oriented. Has ranged stun and melee attack.
- **Wall**: is tall.
- **Coin**: nothing in particular.
- **Weapon**: nothing in particular.
    - **Sword**: nothing in particular.
    - **Bullet**: nothing in particular.

## Implemented features

- [x] Dynamic elements can move
- [x] Sprites change according to direction
- [x] Screen is centered in the Hero
- [x] Some enemies follow the hero
- [ ] Collisions
- [ ] Dynamic elements have life
- [ ] Weaponize enemies

![2020-04-26-image](images/pacman-20200428-202310.gif)

## Planned features

- [ ] Main menu
    - [ ] Scoreboard
    - [ ] Level selector
    - [ ] Level editor (?)
- [ ] Score system
- [ ] Current game state can be saved and loaded
- [ ] Weaponize hero
- [ ] Information bar:
    - [ ] Timer
    - [ ] Score
    - [ ] Level number
    - [ ] Life
    - [ ] Money collected
    - [ ] Restart level
    - [ ] Go to main menu
- [ ] Enemies
    - [ ] Guard
    - [ ] Ghost
    - [ ] Spider
    - [ ] Mummy
- [ ] Collectables
    - [ ] Coin
    - [ ] Sword
    - [ ] Bullet
- [ ] Swing compatibility

## Design

### Lanterna facade

#### Problem in context
Frameworks are complex (and sometimes unfortunately ever-changing) systems, having many more features than those that will actually be used in a project. This is the case of the [Lanterna](https://code.google.com/archive/p/lanterna/) framework we are using, from which we practically only need a few simple features like drawing a character with a certain foreground and background colors, and receive user input.

Should we use Lanterna's methods directly, the code would be:

- Complex, since there are some extra steps we need to take before we allow drawing.
- Tightly coupled with the framework.

Right at the beginning we arrived at the conclusion we would have to develop this module taking into account the following pattern, so there is little code that can be presented as the pre-refactoring code.

#### The pattern
We applied the [**Facade**](https://refactoring.guru/design-patterns/facade) pattern, which consists of creating a facade class that exposes only a few methods of a complex framework, including only the features the client really cares about.

#### Implementation
The following figure shows how the pattern's roles were mapped to the application classes.

![](images/facade-lanterna.svg)

Our facade `LanternaGUI` represents a single terminal window.

The classes can be found in the following files:
- [GUI](../src/main/java/com/pacman/g60/View/GUI.java)
- [TerminalGUI](../src/main/java/com/pacman/g60/View/TerminalGUI.java)
- [LanternaGUI](../src/main/java/com/pacman/g60/View/LanternaGUI.java)
 
#### Consequences
This use of the Facade pattern allows the following benefits:
- We have a limited but straightforward interface to a complex framework.
- Knowledge about the framework is restricted to the facade class.

### ElementView factory
#### Problem in context
To draw an `Element`, an implementation of `ArenaView` had to:
1. [Load all sprites](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/21f450bd2218317b20a4ab332df9227c82dfe0b2/src/main/java/com/pacman/g60/View/TerminalArenaView.java#L27-L54) in `TerminalArenaView`'s constructor in a **very confusing** way.
2. [Store them in maps](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/21f450bd2218317b20a4ab332df9227c82dfe0b2/src/main/java/com/pacman/g60/View/TerminalArenaView.java#L22-L23).
3. [Get the correct sprite from the maps](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/21f450bd2218317b20a4ab332df9227c82dfe0b2/src/main/java/com/pacman/g60/View/TerminalArenaView.java#L71-L75), which in practice is **equivalent to** (and thus as bad as) **a large `switch` statement**, thus qualifying as a subtle form of a `switch` statement code smell.

#### The pattern
We applied the [Factory Method](https://refactoring.guru/design-patterns/factory-method) pattern, which allows a better organization of products, by returning an interface that is actually a concrete implementation of that interface, constructed according to the instructions provided to the factory method.

#### Implementation
The following figure shows how the pattern's roles were mapped to the application classes.

![](images/factory-elementview.svg)

All these classes can be found in file [TerminalArenaView.java](../src/main/java/com/pacman/g60/View/TerminalArenaView.java)


#### Consequences
This use of the Abstract Factory pattern allows us the following benefits:
- An `ArenaView` does not need to care how and where an element is drawn, or which class knows how to draw it; it simply pours it into a factory that will find out the suitable concrete `ElementView` to draw it.
- Sprite loading is encapsulated in an orderly fashion in each concrete `ElementView`

### ViewFactory and TerminalFactory (abstract factory)
#### The pattern
We applied the [Abstract Factory](https://refactoring.guru/design-patterns/abstract-factory) pattern, which allows a better organization and use of families of products, by:
1. Declaring interfaces for each distinct product in a family and have each variant implement the respective interface;
2. Declaring the abstract factory, which has methods for creating all products in a family;
3. Declare a concrete factory implementing the abstract factory for each family

### ArenaModel loader factory (Factory Method)

To encapsulate the `switch` statement in `ArenaModelLoaderStream` to choose the concrete `Element` to create based on the character in the map file, we used the [Factory Method](https://refactoring.guru/design-patterns/factory-method) pattern

### Menus and game (State)
It's all menus and games, until you reach the point you have to put everything together. The *maestro* of the whole game is (you guessed it) `Game`, which will somehow have to handle the fact we might be in a menu, a scoreboard or in-game.

We will use the [State](https://refactoring.guru/design-patterns/state) pattern to better organize our `Game` class, allowing us to:
- Represent the general workings of the game as a deterministic finite automata, where some states are only reachable from others.
- Transitions between states are rather explicit.
- Change the behaviour of `Game` according to its current state (to have the appropriate input/output possibilities for a menu, a scoreboard, in-game, etc.)

### ArenaController is the *God of Dynamics* (Command & Composite)
To stay true to the MVC model without using any particular design pattern, `ArenaController` is the *God of Dynamics*, meaning it processes all dynamics and events (moving and collision handling, shooting projectiles, picking weapons, etc.). This gives the ArenaController an excessive amount of responsibility.

We will use the [Command](https://refactoring.guru/design-patterns/command) pattern to reduce `ArenaController`'s knowledge and responsibility. Each command will still somehow be associated to a controller (given the results of a command are also part of a given set of rules), but each command will individually be responsible for handling a certain action. That will allow us to:
- Structure `ArenaController` around high-level commands built on primitive operations.

We will also use the [Composite](https://refactoring.guru/design-patterns/composite), which will allow us to:
- Compose commands as chains of other commands.
- Encapsulate commands and lists of commands from whoever has contact with them.

### Movement strategies (Strategy)
We might decide to implement a different strategy for moving our followers: instead of going all straight horizontally and then vertically, we might want followers to describe a sort of a diagonal, such that if the tile above and to the left are the same distance from the destination we might want to choose that which makes the movement resemble more of a diagonal (i.e., the one that minimizes the distance in a straight line).

For that we use the [Strategy](https://refactoring.guru/design-patterns/strategy) pattern with `ShortestPathStrategy`, allowing us to:
- Use the same algorithm for different classes
- Implement different algorithms and then choose whichever seems better.

## Code smells

## Testing
