# The Jungle Game
COMP 3222: Software Design Principles – Team Project

# 1. Overview
<img src="https://github.com/wongkaho2626/TheJungleGame/blob/master/resource/ChessBoard.png"/>
Jungle is a traditional Chinese board game. In this project, you will work in teams on the design of a Jungle game to be played in the command line console. Each team needs to come up with a design for the game, present its design, and review other teams’ designs during the semester.

# 2. The Jungle Game
The Jungle game is a traditional Chinese board game played between two players. A concise description of the game and its rules is available in Appendix A.
The game you need to design is to be played in the command line console in the following way:

• When the program is launched, a new game should start;

• At the beginning of a game, the two players A and B should be prompted to input their names. Then the initial board should be printed and player A should be prompted to make the first move;

• During a game, the two players should make moves in turn;

• Moves should be input in the form “FromPosition->ToPosition”, meaning “to move the
piece of the current player at FromPosition to ToPosition”.
For example, “C9->C2” means to move the piece at position “C9” to position “C2”.

• If a move is valid according to the rules described in Appendix A, it should be executed and the updated board should be printed. Afterwards, if the game is not over, the other player should be prompted to make the next move;

• If a move is invalid, it should not be executed, an error message should be shown, the board should be printed, and the same player should be prompted to input another move.
Continuing the previous example, if the player making the move has no piece at location “C9” or the piece is not allowed to move to “C2” in one step, the move is invalid and will not change the board. After showing an error message, the program should print the board and prompt the player to make another move.

• When either player has achieved the goal (Appendix A.5), the game is over and the program should exit after printing the name of the winner.

• Commands should be provided to allow a game to be saved to and loaded from the disk.

The requirements above have been left incomplete on purpose. You need to decide on the missing details when designing the program.

You are encouraged to think of one or two major features the program may need to support in the future and discuss in your reports how to design your program to be well prepared for such changes. For example, later we may want the program to have a graphical user interface (GUI) so that the board can be shown on the screen with better quality and players can move their pieces with a mouse; we may also want the program to be able to log the moves of the players during a game and replay the game later.

# Appendix A The Jungle Game
This section gives a concise introduction to the Jungle game based on the description at https://en.wikipedia.org/wiki/Jungle_(board_game).

A.1 Board
----------
A board of the Jungle game consists of seven columns and nine rows of squares (Figure 1). Pieces move on the square spaces as in international chess, not on the lines as in Chinese Chess. Pictures of eight animals and their names appear on each side of the board to indicate initial placement of the game pieces. After initial setup, these animal spaces have no special meaning in gameplay.

There are several special squares and areas on the Jungle board: The dens (Figure 2) are located in the center of the boundary rows of the board, and are labeled as such in Chinese. Traps (Figure 3) are located to each side and in front of the dens, and are also labeled in Chinese. Two water areas or rivers (Figure 4) are located in the center of the board: each comprises six squares in a 2×3 rectangle and is labeled with the Chinese characters for ”river”. There are single columns of ordinary land squares on the edges of the board, and down the middle between the rivers.

<img src="https://github.com/wongkaho2626/TheJungleGame/blob/master/resource/Figure%201.png"/>
Figure 1: A typical Jungle gameboard showing the location of starting squares, the den, rivers, and traps.

<img src="https://github.com/wongkaho2626/TheJungleGame/blob/master/resource/Figure%202.png"/>
Figure 2: The den highlighted in green.

<img src="https://github.com/wongkaho2626/TheJungleGame/blob/master/resource/Figure%203.png"/>
Figure 3: The traps highlighted in yellow.

<img src="https://github.com/wongkaho2626/TheJungleGame/blob/master/resource/Figure%204.png"/>
Figure 4: One of the rivers.

A.2 Pieces
----------
Each side has eight pieces representing different animals, each with a different rank. Higher ranking pieces can capture all pieces of identical or lower ranking. However, there is one exception: The rat may capture the elephant, while the elephant may not capture the rat. The animal ranking, from highest to lowest, is as shown in Table 1. Pieces are placed onto the corresponding pictures of the animals which are invariably shown on the board.

A.3 Movement
-------------
Players alternate moves. During their turn, a player must move. Each piece moves one square horizontally or vertically (not diagonally). A piece may not move to its own den. There are special rules related to the water squares: The rat is the only animal that is allowed to go onto a water square. The rat may not capture the elephant or another rat on land directly from a water square. Similarly, a rat on land may not attack a rat in the water. The rat may attack the opponent rat if both pieces are in the water. The lion and tiger pieces may jump over a river by moving horizontally or vertically. They move from a square on one edge of the river to the next non-water square on the other side. Such a move is not allowed if there is a rat (whether friendly or enemy) on any of the intervening water squares. The lion and tiger are allowed to capture enemy pieces by such jumping moves.

| Rank | Piece |
| --- | --- |
| 8 | Elephant |
| 7 | Lion |
| 6 | Tiger |
| 5 | Leopard |
| 4 | Wolf |
| 3 | Dog |
| 2 | Cat |
| 1 | Rat |

A.4 Capturing
-------------
Animals capture the opponent pieces by “eating” them. A piece can capture any enemy piece which has the same or lower rank, with the following exceptions: A rat may kill (capture) an elephant. As stated above, a rat may not capture an elephant from a water square. A player may capture any enemy piece in one of the player’s trap squares regardless of rank.

A.5 Objective
--------------
The goal of the game is to move a piece onto the den on the opponent’s side of the board, or capture all of the opponent’s pieces.
