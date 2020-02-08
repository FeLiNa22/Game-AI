from MaxMin import MaxMinAlg
class Move():
    def __init__(self,col):
        self.col = col
        
class Player():
    def __init__(self,name):
        self.name = name
    
    def setOpponent(self,opponent):
        self.opponent = opponent
    
class Game():
    def __init__(self,name,board,agent):
        self.name = name
        self.board = board
        self.agent = agent
        self.player = Player('X')
        self.opponent = Player('O')
        self.player.setOpponent(self.opponent)
        self.opponent.setOpponent(self.player)
        
    def play(self):
        print("You are playing " + self.name +" !!!\n")
        self.board.drawBoard()
        player = self.player
        while(self.board.status(player) == 999) :
            if(player.name == 'X'):
                col = int(input("col no. : "))
                move = Move(col-1)
            else :
                move = self.agent.Eval(self.board,player)
            self.board.makeMove(move,player)
            print("")
            print("Player "+player.name+" Has Moved")
            print("")
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
        self.width = 6
        self.height = 6
        self.board = [['.','.','.','.','.','.'],
                      ['.','.','.','.','.','.'],
                      ['.','.','.','.','.','.'],
                      ['.','.','.','.','.','.'],
                      ['.','.','.','.','.','.'],
                      ['.','.','.','.','.','.']]
        self.moves = []
        
    def undoMove(self):
        move = self.moves.pop()
        for y in range(self.height):
            if (self.board[y][move.col] != '.'):
                self.board[y][move.col] = '.'
                break;
    def validMove(self,move,player):
        if (move.col >= 0 and move.col < self.width) :
            #checks if col is full
            return (self.board[0][move.col] == '.')
        else:
            return False
        
    def getPossibleMoves(self,player):
        potentialMoves = [] 
        for col in range(self.width):
            m = Move(col)
            if(self.validMove(m,player)) :
                    potentialMoves.append(m)
        return potentialMoves
    
    def makeMove(self, move, player):
        if(self.validMove(move,player)):
            self.moves.append(move)
            for y in range(self.height):
                if (y == self.height-1):
                    self.board[self.height-1][move.col] = player.name
                if(self.board[y][move.col] == '.' and self.board[y+1][move.col] != '.'):
                    self.board[y][move.col] = player.name
                    break;
            
        else:
            print("Failed Move :"+str(move.col))
            print("Invalid Move bitch, u lose a go")
            
    def drawBoard(self):
        print("1   2   3   4   5   6  ")
        for i in range(self.height):
            for j in range(self.width):
                print('{} |'.format(self.board[i][j]), end=" ")
            print("")
        print("")
        print("------------------------------------")

            
    def status(self,player):

        def hasWon(player):
            
            # horizontal win
            for y in range(self.height):
                for x in range(self.width - 3):
                    if (self.board[y][x] == player.name
                    and self.board[y][x+1] == self.board[y][x] 
                    and self.board[y][x+2] == self.board[y][x] 
                    and self.board[y][x+3] == self.board[y][x]):
                        return True
        
            # vertical win
            for y in range(self.height-3):
                for x in range(self.width):
                    if (self.board[y][x] == player.name
                    and self.board[y+1][x] == self.board[y][x] 
                    and self.board[y+2][x] == self.board[y][x] 
                    and self.board[y+3][x] == self.board[y][x]):
                        return True

        
            # diagonal top left -> bottom right
            for y in range(self.height - 3):
                for x in range(self.width - 3):
                    if (self.board[y][x] == player.name 
                    and self.board[y+1][x+1] == self.board[y][x] 
                    and self.board[y+2][x+2] == self.board[y][x] 
                    and self.board[y+3][x+3] == self.board[y][x]):
                        return True

                    
            # diagonal top right -> bottom left        
            for y in range(self.height - 3):
                for x in range(3,self.width):
                    if (self.board[y][x] == player.name 
                    and self.board[y+1][x-1] == self.board[y][x] 
                    and self.board[y+2][x-2] == self.board[y][x] 
                    and self.board[y+3][x-3] == self.board[y][x]):
                        return True
            return False
        
        def hasLost(player):
            # The opponent has won
            return hasWon(player.opponent) == 1
                
        def isTie():
            # Is whole board full?
            for i in range(self.height):
                for j in range(self.width):
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




TicTacToeAI = Game("TICTACTOE",Board(),MaxMinAlg(maxDepth = 10, alpha = -10, beta = 10))
TicTacToeAI.play()
