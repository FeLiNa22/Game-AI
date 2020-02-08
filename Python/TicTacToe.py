from MaxMin import MaxMinAlg
class Move():
    def __init__(self,x,y):
        self.x = x
        self.y = y
        
class Player():
    def __init__(self,name):
        self.name = name
    
    def setOpponent(self,opponent):
        self.opponent = opponent
        
    
class Game():
    def __init__(self,name,board):
        self.name = name
        self.board = board
        
        self.player = Player('X')
        self.opponent = Player('O')
        self.player.setOpponent(self.opponent)
        self.opponent.setOpponent(self.player)
        
        self.maxminAlg = MaxMinAlg(100)
        
    def play(self):
        print("You are playing " + self.name +" !!!\n")
        self.board.drawBoard()
        player = self.player
        while(self.board.status(player) == 999) :
            if(player.name == 'X'):
                x = int(input("X co-ord : "))
                y = int(input("Y co-ord : "))
                move = Move(x-1,y-1)
            else :
                move = self.maxminAlg.Eval(self.board,player)
            self.board.makeMove(move,player)
            print()
            print("Player "+player.name+" Has Moved")
            print()
            self.board.drawBoard()
            player = player.opponent
            
        if(self.board.status(self.player) == 1):
            print("THE WINNER IS "+self.player.name + " !!!!")
        elif(self.board.status(self.player) == -1):
            print("THE WINNER IS "+self.opponent.name + " !!!!")
        else : 
            print("DAYUMN IT'S A TIE !")
            
    
class Board():
    def __init__(self):
        self.width = 3
        self.height = 3
        self.board = [['.','.','.'],
                      ['.','.','.'],
                      ['.','.','.']]
        self.moves = []
        
    def undoMove(self):
        move = self.moves.pop()
        self.board[move.y][move.x] = '.'
    
    def validMove(self,move,player):
        if move.x < 0 or move.x > 2 or move.y < 0 or move.y > 2:
            return False
        elif self.board[move.y][move.x] != '.':
            return False
        else:
            return True
        
    def getPossibleMoves(self,player):
        potentialMoves = [] 
        for y in range(self.height):
            for x in range(self.width):
                if(self.board[y][x] == ".") :
                        potentialMoves.append(Move(x,y))
        return potentialMoves
    
    def makeMove(self, move, player):
        if(self.validMove(move,player)):
            self.board[move.y][move.x] = player.name
            self.moves.append(move)
        else:
            print("tRYNA MOVE to x:"+str(move.x) +" Y:"+str(move.y))
            print("Invalid Move bitch, u lose a go")
            
    def drawBoard(self):
        print ("     1   2   3")
        print()
        for i in range(0, 3):
            row = ""
            for j in range(0, 3):
                row += ' {} |'.format(self.board[i][j], end=" ")
            print(" " + str(i+1) + " |" + row)
            print("")
        print("")
        print("------------------------------------")

            
    def status(self,player):

        def hasWon(player):
            # Vertical win
            for i in range(0, 3):
                if (self.board[0][i] == player.name 
                    and self.board[1][i] == self.board[0][i] 
                    and self.board[2][i] == self.board[0][i]):
                    return True
        
            # Horizontal win
            for i in range(0, 3):
                if (self.board[i] == [player.name, player.name, player.name]):
                    return True
        
            # Main diagonal win
            if (self.board[0][0] == player.name and
                self.board[0][0] == self.board[1][1] and
                self.board[0][0] == self.board[2][2]):
                    return True
        
            # Second diagonal win
            if (self.board[0][2] == player.name and
                self.board[0][2] == self.board[1][1] and
                self.board[0][2] == self.board[2][0]):
                    return True
            return False
            
        def hasLost(player):
            # The opponent has won
            if(hasWon(player.opponent)==1):
                return True
            
        def isTie():
            # Is whole board full?
            for i in range(0, 3):
                for j in range(0, 3):
                    # There's an empty field, we continue the game
                    if (self.board[i][j] == '.'):
                        return False
            return True
                
        if(hasWon(player)):
            return 1
        elif(hasLost(player)):
            return -1
        elif(isTie()):
            return 0
        else:
            return 999




TicTacToeAI = Game("TICTACTOE",Board())
TicTacToeAI.play()
