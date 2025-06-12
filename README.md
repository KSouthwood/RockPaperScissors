# [Rock-Paper-Scissors](https://hyperskill.org/projects/314)

## About

If you've ever wanted to create games, this project will get you started! In this project, you will code a
Rock-Paper-Scissors-Lizard-Spock game, a more advanced version of Rock-Paper-Scissors and play against the
computer.

## Learning Outcomes

A playable Rock-Paper-Scissors game, with a Player vs. Computer mode. Practice using arrays, the `Random` class,
formatted strings and algorithms.

### Stage 1: [Unfair computer](https://hyperskill.org/projects/314/stages/1764/implement)

#### _Summary_

Before learning how to play the game properly, let's learn how to cheat! Using conditional statements, you'll write
a program that always defeats the human player in the Rock-Paper-Scissors game.

#### _Description_

Rock-Paper-Scissors is a popular hand game. Two players simultaneously form one of three shapes with their hands, and
then, depending on the shapes, one player wins—rock beats scissors, paper wins over rock, scissors defeats paper.
The game is well-known for fair win-conditions between equal options.

Write a program that reads input that states which option users have chosen. After this, your program must make
users lose! That is, your program should always select an option that defeats the one picked by users. Once it finds
this option, output the line `Sorry, but the computer chose <option>`, where `<option>` is the name of the option
that the program's picked depending on the user's input. (i.e., if the user entered `rock`, the program should print
`Sorry, but the computer chose paper` and so on.)

### Stage 2: [Equalizing chances](https://hyperskill.org/projects/314/stages/1765/implement)

#### _Summary_

Let's not mess with the player too much, though: from now on, we're going to play fair. You'll be able to do this
with the help of the random module, while string formatting will assist you in announcing the result of the game.

##### _Description_

Let's do something more tangible. Nobody wants to play the game where you always lose. We can use the power of the
`Random` class to make the game a bit more challenging. Change the program to read input from the user, choose a
random option, and then say who won: the user or the computer. Use one of the following messages to display the
result (`<option>` is what was chosen by the computer).

- Loss: `Sorry, but the computer chose <option>`
- Draw: `There is a draw  (<option>)`
- Win: `Well done. The computer chose <option> and failed`

### Stage 3: [Endless game](https://hyperskill.org/projects/314/stages/1766/implement)

#### _Summary_

Can't stop, won't stop! A single game is never enough. Learn about loops in Java and apply them to make multiple
game rounds possible.

##### _Description_

We came up with a really cool idea in the previous stage. But the game is really short. Nobody plays a single shot of
rock paper scissors. We need to find a way to run the game forever. Not literally, though—let's implement a way to
stop your program.

Improve the program so it will take an unlimited number of inputs until the user enters `!exit`. After entering `!exit`,
the program should print `Bye!` and terminate. Also, let's try to handle invalid inputs: your program should
be ready to handle typos in user input, or when there's a mishmash instead of a normal command. So, if the input
doesn't correspond to any known command (an option or `!exit`), your program should output the following line:
`Invalid input`.

### Stage 4: [Scoring the game](https://hyperskill.org/projects/314/stages/1767/implement)

#### _Summary_

It's time to find out who the best Rock-Paper-Scissors player is. You are going to need the basics of file handling
to read the records of the results of previous games, as well as tally the user's score in the current game.

##### _Description_

People love to see their results as they're advancing to their goals. So, let's learn how to show the scoreboard!
When the game starts, users must be able to enter their names. After that, the program should greet users and read a
file named `rating.txt`. It is the file that contains the current scores for different players. The file format is
lines that contain a username and their score, divided by a single space.

Take the current user score from the file and use it as a basis for counting the score during the game. If a user
inputs a name that is not in the file, the program should count their score from 0. Print the user score with the `!
rating` command. Add 50 points for every draw, 100 for every win and 0 for losing.

### Stage 5: [More options](https://hyperskill.org/projects/314/stages/1768/implement)

#### _Summary_

Let's raise the stakes, shall we? In the final stage of the project, your program will let the player choose what
options will be used in the game and how many of them will be there. This will require some more advanced knowledge
about lists in Java.

##### _Description_

How about new game rules? The original game has a fairly small choice of options. The program should now accept
alternative lists of options, like `Rock`, `Paper`, `Scissors`, `Lizard`, `Spock` and so on.

For this stage, before the game starts, users can choose the options. After entering their name, they should provide
a list of the options separated by a comma. (i.e., `rock,paper,scissors,lizard,spock`). If they enter an empty line,
start the game with default options `rock`, `paper`, and `scissors`. Once the game options are defined, output
`Okay, let's start`.

Regardless of the chosen options, the program should identify which option beats which. The general algorithm to 
determine which options are stronger or weaker is to take the list of options provided by the user and pick the 
option that you want to know the relationships of. Then create a list of the remaining options starting with the 
option directly after the chosen one, looping back to the start of the list when you reach the end until you've 
gathered all but the chosen option. Now, you have another list of options that don't include the user's option with a 
different order of elements inside. First are the options that follow the chosen one in the original list; then, 
there are the ones that precede it. So, in this "new" list, the first half of the options defeat the "chosen" option,
and the second half is beaten by it.

For example, the user's list of options is `rock,paper,scissors,lizard,spock`. You want to know what options are 
weaker than `lizard`. By looking at the list `spock,rock,paper,scissors` you realize that `spock` and `rock` beat 
`lizard`. `Paper` and `scissors` are defeated by it. For `spock`, it'll be almost the same, but it'll get beaten by 
`rock` and `paper`, and prevail over `scissors` and `lizard`.
