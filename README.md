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
|![2020-04-26-image](docs/images/2020-04-26_21-31-01.png)|![2020-05-02-image](docs/images/2020-05-02_04-43-23.png)|

| 2020-05-28 |
|------------|
| ![](docs/images/2020-05-28_20-12-43.png) |

##### Elements

| Wall | Coin | Sword | Potion | Hero | Ghost | Ogre | Mummy | Guard |
|------|------|-------|---------------|------|-------|------|-------|-------|
| ![wall](docs/images/wall.png) | ![coin](docs/images/coin.png) | ![sword](docs/images/sword.png) | ![potion](docs/images/potion.png) | ![hero](docs/images/hero.png) | ![ghost](docs/images/ghost.png) | ![ogre](docs/images/ogre.png) | ![mummy](docs/images/mummy.png) | ![guard](docs/images/guard.png) |

<a name="animations"><a/>
#### Animations

| 2020-04-28 | 2020-05-02 |
|------------|------------|
|![2020-04-28-animation](docs/images/pacman-20200428-202310.gif)|![2020-04-26-image](docs/images/pacman-20200502-042816.gif)|

| 2020-05-31 | 2020-05-31 |
|------------|------------|
| ![2020-04-26-image](docs/images/pacman-20200531-192912.gif) | ![2020-04-26-image](docs/images/pacman-20200531-193722.gif) |