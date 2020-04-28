# LPOO-2020-G60: Pacman (or maybe not)

<p align="left">
  <img src="https://github.com/FEUP-LPOO/lpoo-2020-g60/workflows/gradlew%20test/badge.svg">
</p>

## Description

Her Majesty the Queen has requested the assistance of a noble knight to cleanse the catacombs of the Kingdom's capital of the terrible monsters that have been haunting her subjects for centuries. Unfortunately, because people are scared of the monsters that occasionally escape the catacombs, productivity is low and the Kingdom's coffers are empty (otherwise she would have hired a professional ghost-hunter team), but whoever answers Her Majesty's call will have rights over any treasure found in the catacombs.

Having accepted the challenge, your goal is to kill all monsters, while collecting as many coins as possible so at to guarantee an alternative to the rather underfinanced Kingdom's Social Security system for yourself.

The catacombs are full of treasures from ancient times, as well as weapons from those who once ventured into the depths of the city but never returned.

- **Q: Do what?** A: Kill monsters and collect coins.
- **Q: With what?** A: You start with a knife, but you can catch other weapons and bullets in the catacombs.
- **Q: How do I leave the catacombs?** A: You may take a rest or leave at any time, but your mission is only over once you cleanse all the catacombs of the city.
- **Q: What if I die?** A: Her Majesty has been personally appointed by God, if you die on duty He will give you a second chance. 

### State of the Art

<p align="center">
  <img src="https://drive.google.com/uc?id=1WstrLPkEo1YTzjKws6ZUzi35vcV6Ieky">
</p>

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

## Features

- [ ] Main menu
    - [ ] Scoreboard
    - [ ] Level selector
    - [ ] Level editor (?)
- [ ] Score system
- [ ] Current game state can be saved and loaded
- [ ] Hero is always in the center of the screen
- [ ] Weaponize enemies
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
- [ ] Swing compatibility

## UML

The UML class diagram of the project is available [here](https://drive.google.com/file/d/1Ql_kmMo6nBzLH933LEEZQvu8vgUm2lDC/view?usp=sharing).

## Design

### Complex frameworks require simple interfaces (Facade)
To draw an `Element`, an implementation of `ArenaView` does not need to care how and where it is drawn; it simply pours it into another class that will figure out how to draw it using a certain framework.

To this end, we use the [Facade](https://refactoring.guru/design-patterns/facade) pattern, which allows us to:
- Have a limited but straightforward interface to a complex framework: draw me this, and let me know when the user does something (but on the clients' terms, not the framework terms).

### To be or not to be a terminal (Abstract Factory)
The above note will allow us to further organize our code.

We want our interface between game and framework (our `ArenaView`) to actually support several frameworks, not just a dull terminal.

To this end, we use the [Abstract Factory](https://refactoring.guru/design-patterns/abstract-factory) pattern, which will allow us to:
- Generally ignore how *products* are made (where a product might be a `View` for a specific `Element`)
- Have several families of products (e.g., one for a Lanterna terminal and another for a Swing window)
- Make `View`s from the same family work in tight cooperation if needed.

For instance, we can name our abstract factory `ViewFactory` (produces `MenuView` and `GameView`), and a concrete factory `LanternaFactory` (produces `LanternaMenuView implements MenuView` and `LanternaGameView implements GameView`). 

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

For that we will use the [Strategy](https://refactoring.guru/design-patterns/strategy) pattern, allowing us to:
- Use the same algorithm for different classes
- Implement different algorithms and then choose whichever seems better.

## Code smells

## Testing
