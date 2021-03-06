# LPOO_60 - The Cursed Catacombs
#### (Codename: pacman)

[![gradlew test](https://github.com/FEUP-LPOO/lpoo-2020-g60/workflows/gradlew%20test/badge.svg)](https://feup-lpoo.github.io/lpoo-2020-g60/reports/tests/test/index.html)

You are a noble knight seeking to assist Her Majesty in cleansing the capital's catacombs from the many monsters that inhabit it. Your mission is to kill all monsters and collect as many coins as possible across multiple levels.

This project was developed for LPOO <sup>2019</sup>⁄<sub>20</sub> by:
- [Diogo Rodrigues](https://github.com/dmfrodrigues) ([dmfrodrigues2000@gmail.com](mailto:dmfrodrigues2000@gmail.com)/[up201806429@fe.up.pt](mailto:up201806429@fe.up.pt))
- [João Matos](https://github.com/MechJM) ([up201703884@fe.up.pt](mailto:up201703884@fe.up.pt))

## Table of contents
1. [Description](#description)
    1. [Controls](#controls)
    2. [Media](#media)
2. [Implemented features](#implemented-features)
3. [Planned features](#planned-features)
4. [Design](#design)
    1. [Lanterna facade](#lanterna-facade)
    2. [ElementView factory](#factory-elementview)
    3. [ViewFactory](#factory-view)
    4. [ArenaModelLoaderStream factory](#factory-arenamodelloaderstream)
    5. [Menus and games](#state-game)
    6. [ArenaController is the *God of Dynamics*](#command-composite-arenacontroller)
    7. [Movement strategies](#strategy-movement)
    8. [Composite Views](#composite-view)
5. [Code smells and refactoring suggestions](#code-smells)
6. [Testing](#testing)
7. [Self-evaluation](#self-evaluation)

<a name="description"><a/>
## Description

Her Majesty the Queen has requested the assistance of a noble knight to cleanse the catacombs of the Kingdom's capital of the terrible monsters that have been haunting her subjects for centuries. Unfortunately, because people are scared of the monsters that occasionally escape the catacombs, productivity is low and the Kingdom's coffers are empty (otherwise she would have hired a professional ghost-hunter team), but whoever answers Her Majesty's call will have rights over any treasure found in the catacombs.

Having accepted the challenge, your goal is to kill all monsters, while collecting as many coins as possible so at to guarantee an alternative to the rather under-financed Kingdom's Social Security system for yourself.

The catacombs are full of treasures from ancient times, as well as weapons from those who once ventured into the depths of the city but never returned.

- **Q: Do what?** A: Kill monsters and collect coins.
- **Q: With what?** A: You start with a knife, but you can catch other weapons and bullets in the catacombs.
- **Q: How do I leave the catacombs?** A: You may take a rest or leave at any time, but your mission is only over once you cleanse all the catacombs of the city.
- **Q: What if I die?** A: Her Majesty has been personally appointed by God, if you die on duty He will give you a second chance.

<a name="controls"><a/>
### Controls

- Everywhere:
    - `D` to enable/disable debug information (FPS), on the lower left corner.

- In menus:
    - `⬆️`/`⬇️` to navigate up/down the menu items.
    - `↵` (Enter) to select an item.
    - `ESC` to go to the previous menu.
    - `ESC`/`P` to unpause the game.

- In game:
    - `⬅`/`⬆️`/`➡`/`⬇️` to move hero west/north/east/south.
    - `⎵` (Space bar) to use melee attack.
    - `F` to shoot a bullet.
    - `ESC`/`P` to pause the game.

<a name="media"><a/>
### Media

<a name="gallery"><a/>
#### Gallery

| 2020-04-26 | 2020-05-02 |
|------------|------------|
|![2020-04-26-image](images/2020-04-26_21-31-01.png)|![2020-05-02-image](images/2020-05-02_04-43-23.png)|

| 2020-05-28 |
|------------|
| ![](images/2020-05-28_20-12-43.png) |

##### Elements

| Wall | Coin | Sword | Potion | Hero | Ghost | Ogre | Mummy | Guard |
|------|------|-------|---------------|------|-------|------|-------|-------|
| ![wall](images/wall.png) | ![coin](images/coin.png) | ![sword](images/sword.png) | ![potion](images/potion.png) | ![hero](images/hero.png) | ![ghost](images/ghost.png) | ![ogre](images/ogre.png) | ![mummy](images/mummy.png) | ![guard](images/guard.png) |

<a name="animations"><a/>
#### Animations

| 2020-04-28 | 2020-05-02 |
|------------|------------|
|![2020-04-28-animation](images/pacman-20200428-202310.gif)|![2020-04-26-image](images/pacman-20200502-042816.gif)|

| 2020-05-31 | 2020-05-31 |
|------------|------------|
| ![2020-04-26-image](images/pacman-20200531-192912.gif) | ![2020-04-26-image](images/pacman-20200531-193722.gif) |

<a name="implemented-features"><a/>
## Implemented features

- [x] Sprites being loaded from files
- [x] Dynamic elements can move
- [x] Control hero with keyboard arrows
- [x] Sprites change according to direction
- [x] Screen is centered in the Hero
- [x] Some enemies follow the hero

![2020-04-26-image](images/pacman-20200428-202310.gif)

- [x] Collisions
- [x] Dynamic elements have life
- [x] Information bar:
    - [x] Timer
    - [x] Life
    - [x] Money collected

![2020-04-26-image](images/pacman-20200502-042816.gif)

- [x] Enemies
    - [x] Ghost (weak melee, not vulnerable to bullets)
    - [x] Ogre (strong melee)
    - [x] Guard (walks a small path over and over)
    - [x] Mummy
- [x] Weaponize enemies
- [x] Weaponize hero
- [x] Collectibles
    - [x] Coin
    - [x] Health potion
    - [x] Sword
    - [x] Bullet

![2020-04-26-image](images/pacman-20200531-192912.gif)

- [x] Main menu
    - [x] Level selector
    - [x] Scoreboard
    - [x] Load and save game

![2020-04-26-image](images/pacman-20200531-193722.gif)

<a name="planned-features"><a/>
## Planned features

- [ ] Enemies
    - [ ] Mummy (not throwing anything yet)
- [ ] Swing compatibility

<a name="design"><a/>
## Design

<a name="facade-lanterna"><a/>
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

<a name="factory-elementview"><a/>

### ElementView factory

#### Problem in context

To draw an `Element`, an implementation of `ArenaView` had to:
1. [Load all sprites](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/21f450bd2218317b20a4ab332df9227c82dfe0b2/src/main/java/com/pacman/g60/View/TerminalArenaView.java#L27-L54) in `TerminalArenaView`'s constructor in a **very confusing** way.
2. [Store them in maps](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/21f450bd2218317b20a4ab332df9227c82dfe0b2/src/main/java/com/pacman/g60/View/TerminalArenaView.java#L22-L23).
3. [Get the correct sprite from the maps](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/21f450bd2218317b20a4ab332df9227c82dfe0b2/src/main/java/com/pacman/g60/View/TerminalArenaView.java#L71-L75), which in practice is **equivalent to** (and thus as bad as) **a large `switch` statement**, thus qualifying as a subtle form of a `switch` statement code smell.

<a name="factory-pattern"><a/>
#### The pattern
We applied the [Factory Method](https://refactoring.guru/design-patterns/factory-method) pattern, which allows a better organization of products, by returning an interface that is actually a concrete implementation of that interface, constructed according to the instructions provided to the factory method.

#### Implementation
The following figure shows how the pattern's roles were mapped to the application classes.

![](images/factory-elementview.svg)

All these classes can be found in file [TerminalArenaView.java](../src/main/java/com/pacman/g60/View/TerminalArenaView.java)


#### Consequences
This use of the Factory Method pattern has the following benefits:
- An `ArenaView` does not need to care how and where an element is drawn, or which class knows how to draw it; it simply pours it into a factory that will find out the suitable concrete `ElementView` to draw it.
- Sprite loading is encapsulated in an orderly fashion in each concrete `ElementView`

<a name="absfactory-view"><a/>

### ViewFactory
#### Problem in context

There will eventually be an interest on expanding our project to a graphical, [Swing-based](https://mabe02.github.io/lanterna/apidocs/3.0/com/googlecode/lanterna/terminal/swing/SwingTerminalFrame.html) environment, allowing more interesting renderings than those allowed by a terminal environment.

Initially, an object of class `ArenaViewTerminal` (now renamed to `TerminalArenaView`) was [directly instantiated](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/562bbb338aa20e71ef947c78af4f9c5d5a44de0a/src/main/java/Application.java#L17) in `Application`; if this pattern was not used, it was expected that additional views were going to be instantiated similarly, making it hard to program the possibility of choosing another family of `View`s.

#### The pattern
We applied the [Abstract Factory](https://refactoring.guru/design-patterns/abstract-factory) pattern, which allows a better organization and use of families of products, by:
1. Declaring interfaces for each distinct product in a family and have each variant implement the respective interface;
2. Declaring the abstract factory, which has methods for creating all products in a family;
3. Declare a concrete factory implementing the abstract factory for each family

#### Implementation

The following figure shows how the pattern's roles were mapped to the application classes.

![](images/absfactory-view.svg)

The classes can be found in the following files:

- [ArenaView](../src/main/java/com/pacman/g60/View/ArenaView.java)
- [ViewFactory](../src/main/java/com/pacman/g60/View/ViewFactory.java)
- [TerminalArenaView](../src/main/java/com/pacman/g60/View/TerminalArenaView.java)
- [TerminalFactory](../src/main/java/com/pacman/g60/View/TerminalFactory.java)

#### Consequences

This use of the Abstract Factory pattern has the following benefits:

- It will be easier to choose among several `View` families, once they are developed.
- The *products* (i.e. the different `View`s associated to a terminal, for instance) of a family can tightly cooperate and share some resources (several terminal `View`s can share the same terminal, although only one `View` can operate on it at each moment).

<a name="factory-arenamodelloaderstream"><a/>

### ArenaModelLoaderStream factory

#### Problem in context

We were using a [quite long `switch` statement](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/e777ea8c67b9296520d7d72a7ad919fc30e85143/src/main/java/com/pacman/g60/Model/ArenaModelLoaderStream.java#L18-L23) in `ArenaModelLoaderStream`, so we could distinguish characters and correctly instantiate the corresponding concrete `Element`. This is obviously a `switch` statement code smell.

#### The pattern
We applied the [Factory Method](https://refactoring.guru/design-patterns/factory-method) pattern, which has already been generically introduced [here](#factory-pattern).

#### Implementation
The following figure shows how the pattern's roles were mapped to the application classes.

![](images/factory-arenamodelloaderstream.svg)

All these classes can be found in file [ArenaModelLoaderStream.java](../src/main/java/com/pacman/g60/Model/ArenaModelLoaderStream.java).

#### Consequences
The most significant benefit is that now the `switch` statement is completely contained in a factory, which separates the responsibility of opening and iterating over the file from the responsibility to know what each symbol means.

<a name="state-game"><a/>

### Menus and game
#### Problem in context
It's all menus and games, until you reach the point you have to put everything together. The *maestro* of the whole game is `Game`, which will somehow have to handle the fact we might be in a menu, a scoreboard or in-game.

#### The pattern

We applied the [State](https://refactoring.guru/design-patterns/state) pattern, which consists of representing the general workings of the game as a deterministic finite automaton, where some states are only reachable from others, and with the added advantage that transitions between states are made explicit.

#### Implementation

The following state diagram describes the transitions of our implementation.

![](images/state-diagram.svg)

The following figure shows how the pattern's roles were mapped to the application classes.

![](images/state-uml.svg)

#### Consequences
This use of the State pattern has the benefit of changing the behavior of `Game` in an orderly fashion, according to its current state (to have the appropriate input/output possibilities for a menu, a scoreboard, in-game, etc.).

<a name="command-composite-arenacontroller"><a/>

### ArenaController is the *God of Dynamics*

#### Problem in context

To stay true to the MVC model without using any particular design pattern, `ArenaController` is the *God of Dynamics*, meaning it processes all dynamics and events (moving and collision handling, shooting projectiles, picking weapons, etc.). The issue is the excessive amount of responsibilities granted to `ArenaController` this way.

#### The pattern

We will use the [Command](https://refactoring.guru/design-patterns/command) pattern to reduce `ArenaController`'s knowledge and responsibility. Each command will still somehow be associated to a controller (given the results of a command are also part of a given set of rules), but each command will individually be responsible for handling a certain action. That will allow us to:
- Structure `ArenaController` around high-level commands built on primitive operations.

#### Implementation

Our implementation follows the diagram shown below.

![](images/command-diagram.svg)

#### Consequences

With this pattern, the ArenaController doesn't need to know how do everything and can just delegate tasks, reducing the amount of responsibility it has.

### The complexity of ArenaController's actions

#### Problem in context

Since some of the tasks the ArenaController needs to perform are quite complex, the commands used need to be able to support that complexity.

#### The pattern

With the [Composite](https://refactoring.guru/design-patterns/composite) pattern, we can use commands that are made up of other simpler commands.


#### Implementation

Our implementation will be guided by the following diagram.

![](images/composite-diagram.svg)

#### Consequences
This allows us to:
- Compose commands as chains of other commands.
- Encapsulate commands and lists of commands from whoever has contact with them.

<a name="strategy-movement"><a/>

### Movement strategies
#### Problem in context
We might decide to implement a different strategy for moving our followers: ghosts do not touch the ground, but if we decide to implement a swamp element where walking followers are slower they might want to choose a different path, using a different strategy.

#### The pattern
We applied the [Strategy](https://refactoring.guru/design-patterns/strategy) pattern, which separates different ways to make the same thing into several different classes, also called *concrete strategies*.

#### Implementation
The following figure shows how the pattern's roles were mapped to the application classes.

![](images/strategy-shortestpath.svg)

The classes can be found in the following files:
- [ShortestPathStrategy](../src/main/java/com/pacman/g60/Model/Path_Calculation/ShortestPathStrategy.java)
- [BFSShortestPathStrategy](../src/main/java/com/pacman/g60/Model/Path_Calculation/BFSShortestPathStrategy.java)

#### Consequences
This use of the Strategy pattern has the benefits of:
- Organizing algorithmic knowledge in a systematic way.
- Allowing reuse of an algorithm for different elements.
- Implement different algorithms and then choose whichever seems better.

<a name="composite-view"><a/>

### [Composite](https://refactoring.guru/design-patterns/composite) Views

#### Problem in context

We were having trouble making menu views with titles, so first we decided to create a [draw] function in MenuView, so MenuView could then be overloaded and the missing titles added.

#### The pattern

We applied the [Composite](https://refactoring.guru/design-patterns/composite), which allows a program to ignore the distinction between a simple and a composite object, as long as they can perform the same operations the composite is equivalent to the siimple object.

#### Implementation

The following figure shows how the pattern's roles were mapped to the application classes.

![](images/composite-views.svg)

#### Consequences 
This use of the Composite pattern has allowed us to:
- Work with more complex view structures
- Easily introduce new views as needed, without breaking the existing code, and also easily integrate with what was already implemented

### Movement [Strategy](https://refactoring.guru/design-patterns/strategy) for Guard

#### Problem in context

The guard enemy needs to be able to move horizontally or vertically.

#### The pattern

We decided to use the Strategy pattern to deal with this requirement.

#### Implementation

Our implementation uses the following diagram.

![](images/guard-strategy.svg)

#### Consequences 

This allows us to easily handle various movement strategies for the Guard class.

<a name="code-smells"><a/>
## Code smells and refactoring suggestions

### [Shotgun surgery](https://refactoring.guru/smells/shotgun-surgery)

The [Element](../src/main/java/com/pacman/g60/Model/Elements/) hierarchy, [TerminalArenaView.ElementViewFactory](../src/main/java/com/pacman/g60/View/TerminalArenaView.java) and [ArenaModelLoaderStream](../src/main/java/com/pacman/g60/Model/ArenaModelLoaderStream.java) suffer from shotgun surgery, since they are excessively coupled in a manner, and adding a class to the `Element` hierarchy would require creating new classes and making a lot of minor changes to the members of `TerminalArenaView` and `ArenaModelLoaderStream`. These smells are probably the result of a zealous application of the MVC architecture.

As for `TerminalArenaView.ElementViewFactory`, we have decided not to move responsibilities to a single class, since we considered it was more important to preserve the MVC and more easily allow creation of new Views for other frameworks, rather than try to refactor this smell.

As for `ArenaModelLoaderStream`, this is just a loader from an input stream, the `Element` hierarchy does not really care how an `ArenaModel` is loaded from a file since there can also be many ways to save it to a file. The `ArenaModelLoaderStream` can also be interpreted as a sort of application of the [Builder](https://refactoring.guru/design-patterns/builder) pattern, which is an attempt at decluttering a section of code that would otherwise be a constructor of `ArenaModel`.

### [Feature envy](https://refactoring.guru/smells/feature-envy)

A slight smell of feature envy can be identified in [LanternaGUI](../src/main/java/com/pacman/g60/View/LanternaGUI.java), since this class very often uses features of the Lanterna framework.

We will not refactor this class, since `LanternaGUI` is a facade for the Lanterna framework, and as such it is understandable that it often uses features of the Lanterna framework.

### [Duplicate code](https://refactoring.guru/smells/duplicate-code) in TerminalArenaView
There was [duplicate code](https://refactoring.guru/smells/duplicate-code) in [TerminalArenaView](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/2277651ca00c31e2b380eaf761bc7817e6698122/src/main/java/com/pacman/g60/View/Views/TerminalArenaView.java#L38-L47) and [TerminalSpriteView](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/2277651ca00c31e2b380eaf761bc7817e6698122/src/main/java/com/pacman/g60/View/Views/TerminalSpriteView.java#L43-L50).
Class [TerminalSpriteView](../src/main/java/com/pacman/g60/View/Views/TerminalSpriteView.java) was actually an effort to solve anticipated duplicate code smells, so it basically consists of the result of applying [Extract Class](https://refactoring.guru/extract-class).

We have not solved this smell completely, since there is still [duplicate code](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/398c47f6952d28ed05ad5c9741d325915e85d972/src/main/java/com/pacman/g60/View/Views/TerminalArenaView.java#L101-L115), but in this case we decided not to fix it since it allows drawing "half hearts" in case hero health is not an integer (e.g., 9.5).

#### In TerminalSpriteLoaderStream

The [main cycle](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/ddfd734be4099c6e1620befe1de3c61017e60275/src/main/java/com/pacman/g60/View/TerminalSpriteLoaderStream.java#L9-L33) of [TerminalSpriteLoaderStream](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/ddfd734be4099c6e1620befe1de3c61017e60275/src/main/java/com/pacman/g60/View/TerminalSpriteLoaderStream.java) 's constructor not only makes it a long method, but also has duplicate code for importing color matrices.

We could refactor this class by applying [Extract Method](https://refactoring.guru/extract-method) to extract a color-importing routine, and call it from all places where color matrices are imported. However, as both cycles immediately write the read data to the TerminalSprite to be returned, and given time is short, we decided not to refactor this class.

### [Switch Statements](https://refactoring.guru/smells/switch-statements)

This is a smell found in the ArenaController and MoveHeroCommand classes. In the first case, it may not be possible to get rid of the smell since the player input needs to be translated to actions in the code and conditional statements are the only way to do this. For the other situation, a possible solution would be to use the refactoring technique [Replace Conditional with Polymorphism](https://refactoring.guru/replace-conditional-with-polymorphism), although this may be a bit excessive. In the first case, the smell remains. In the second, the proposed refactoring was used.

#### In factories

There are also switch statements in [TerminalArenaView.ElementViewFactory](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/0eef3838556b58a878e4277710615d93091581e8/src/main/java/com/pacman/g60/View/TerminalArenaView.java#L243-L246) and [TerminalSpriteOrientable](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/0eef3838556b58a878e4277710615d93091581e8/src/main/java/com/pacman/g60/View/TerminalSpriteOrientable.java#L19-L32), but these are in factories exactly because this smell had already been identified, leading to the decision of at least isolating these statements inside factories.

The loader [ArenaModelLoaderStream](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/0eef3838556b58a878e4277710615d93091581e8/src/main/java/com/pacman/g60/Model/ArenaModelLoaderStream.java#L13-L20) also has a switch statement, but we have isolated it in a [factory method](https://refactoring.guru/design-patterns/factory-method).

### [Comments](https://refactoring.guru/smells/comments)

This smell can be found in the Game class and can be easily removed using the refactoring technique [Extract Method](https://refactoring.guru/extract-method) to place the sections separated by comments in other functions. This smell ended up being removed by the [Extract Class](https://refactoring.guru/extract-class) technique.

### Hard to read code

This isn't a particular smell and I guess it could be considered a nitpick but I believe the code in the UpdateAllEnemyPosCommand class is a bit hard to read. I think this situation could be improved using [Extract Method](https://refactoring.guru/extract-method) to separate parts of the setup function into smaller portions that are easier to understand. Also, some of the variables used could have better names. The proposed refactorings were applied in order to remove the "smell".

### [Long Parameter List](https://refactoring.guru/smells/long-parameter-list)

This smell is present in the constructor of the UpdateEnemyPosCommand class and could be eliminated using [Introduce Parameter Object](https://refactoring.guru/introduce-parameter-object). However, it could also be argued that having four parameters isn't a big problem and applying this technique would be excessive. It was concluded that this wasn't actually a smell.

### [Long Method](https://refactoring.guru/smells/long-method)

The updatePos function in the DynamicElement class is a bit too long and could be divided into smaller pieces using [Extract Method](https://refactoring.guru/extract-method). This refactoring was used to remove this smell.

### SRP/[Large class](https://refactoring.guru/smells/large-class) and time-tracking
The class [TerminalArenaView](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/17394b793d1e3a9e62708e2761d981fa3c6311b0/src/main/java/com/pacman/g60/View/Views/TerminalArenaView.java#L52-L53) violated the Single Responsibility Principle by keeping track of time when it should only know how to draw an ArenaModel; so we moved that responsibily to ArenaController. Evetually we realized ArenaController was also becoming a [Large Class](https://refactoring.guru/smells/large-class), so we applied [Extract Class](https://refactoring.guru/extract-class), having extracted the simple class [Stopwatch](../src/main/java/com/pacman/g60/Controller/Stopwatch.java) to keep track of time.

### Some excessive knowledge problem
There is a problem with [Game](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/97e24afb5f05fbb3dc49aee784a37b6c63cd7115/src/main/java/com/pacman/g60/Controller/Game.java#L1-L413) using terminal-specific [hearts](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/97e24afb5f05fbb3dc49aee784a37b6c63cd7115/src/main/java/com/pacman/g60/Controller/Game.java#L238-L239) and [coins](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/97e24afb5f05fbb3dc49aee784a37b6c63cd7115/src/main/java/com/pacman/g60/Controller/Game.java#L251-L252), which stems from the fact [SpriteModel](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/97e24afb5f05fbb3dc49aee784a37b6c63cd7115/src/main/java/com/pacman/g60/Model/Models/SpriteModel.java#L1-L22) knows too much about how to draw itself by having a [TerminalSprite member](https://github.com/FEUP-LPOO/lpoo-2020-g60/blob/97e24afb5f05fbb3dc49aee784a37b6c63cd7115/src/main/java/com/pacman/g60/Model/Models/SpriteModel.java#L11).

We could solve this smell by storing in SpriteModel an *intention* to be drawn as a certain type of sprite (e.g., instead of containing an actual TerminalSprite for drawing a coin, it might contain an `enum` with value `COIN`), and then apply the [Abstract Factory](https://refactoring.guru/design-patterns/abstract-factory) pattern, and the factory would sort out which Sprite to be returned based on the value of the SpriteModel's `enum` variable.

We have not corrected this problem, since it would take a considerable amount of time to go over other refactor options, plus having to implement it would be a quite laborious task. Also, it would create another problem, since everytime we would add a different sprite we would have to change the `enum`, thus making it poorly extensible.

<a name="testing"><a/>

## Testing

[![gradlew test](https://github.com/FEUP-LPOO/lpoo-2020-g60/workflows/gradlew%20test/badge.svg)](https://feup-lpoo.github.io/lpoo-2020-g60/reports/tests/test/index.html)

The most recent test report is available [here](https://feup-lpoo.github.io/lpoo-2020-g60/reports/tests/test/index.html).

The most recent coverage test report (obtained using the [JaCoCo](https://docs.gradle.org/current/userguide/jacoco_plugin.html) plugin) is available [here](https://feup-lpoo.github.io/lpoo-2020-g60/reports/jacoco/test/html/index.html).

The most recent mutation test report (obtained using the [PIT Mutation Testing](https://gradle-pitest-plugin.solidsoft.info/) plugin) is available [here](https://feup-lpoo.github.io/lpoo-2020-g60/reports/pitest/index.html).

### First delivery

Test report for the first delivery is available [here](https://feup-lpoo.github.io/lpoo-2020-g60/delivery1/reports/tests/test/index.html)

Coverage test report for the first delivery is available [here](https://feup-lpoo.github.io/lpoo-2020-g60/delivery1/reports/jacoco/test/html/index.html).

Mutation test report for the first delivery is available [here](https://feup-lpoo.github.io/lpoo-2020-g60/delivery1/reports/pitest/index.html).

### Second delivery

Test report for the second delivery is available [here](https://feup-lpoo.github.io/lpoo-2020-g60/delivery2/reports/tests/test/index.html)

Coverage test report for the second delivery is available [here](https://feup-lpoo.github.io/lpoo-2020-g60/delivery2/reports/jacoco/test/html/index.html).

Mutation test report for the second delivery is available [here](https://feup-lpoo.github.io/lpoo-2020-g60/delivery2/reports/pitest/index.html).

<a name="self-evaluation"><a/>

## Self-evaluation

- Diogo Rodrigues: 60%

- João Matos: 40%
