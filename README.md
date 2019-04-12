Gallager
An arcade style game coded with java that is a fusion between a space shooter and an obstacle dodging game. The player is an avatar that shoots automatically but can only move in the spaces with platforms while static enemies shoot from the top of the screen. The objective is to eliminate all the enemies in the level while simultaneously dodging their bullets and staying on the moving platforms. The player can only shoot while it is on a moving platform. Any time that the player takes any form of damage, either from being shot or moving somewhere without a platform, the avatar and board will be reset to their initial positions, but the enemies killed previously will stay dead, and the player will have 1 less live out of the 10 they have. The player can die 10 times before receiving a “game over” screen. Killing all enemies before losing the 10 lives the player has results in a “you win” screen as well, however in both cases your score, and the high score will be shown.
<br>
Gallager will be located in https://github.com/Willplayer1999/Group-26-project in the main branch, make sure you download everything from the branch.
<br>
There will be 11 classes that need to be grabbed from github. They are: AvatarBullet, Bullet, EnemyBullet, Collidable, Avatar, Enemy, Platforms, Game, GUI, TextGame and Main. Do not skip out on downloading the images, the code will not work without them.
<br>
The 11 Classes are spread over 5 Packages namely Bullet, Collidable, Entities, Game and Main
<br>
First they need to be compiled using javac. (Then the path directory to the location)
After it successfully compiles type java Main and enjoy the game.

NOTE:
To run the test file. First make sure you have junit installed. If you are going through eclipse you can add the library (J-unit 4, or J-unit 5 will work). If you are strictly command prompt you will have to first download it (https://junit.org/junit5/) and then run java -jar junit-platform-console-standalone-<version>.jar <Options> through the command prompt, choosing what version you are using. Our test file will be called [INSERT TEST FILE NAME HERE] The test file will also need to be compiled.
If you need help with how to use Junit please look to 
https://junit.org/junit5/docs/current/user-guide/

