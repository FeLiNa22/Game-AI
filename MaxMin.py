#in boARD CLASS
# Board
# |
# ---> .status(player) // returns status of game (loss/win/undefined) 
# ---> .getPossibleMoves(player) //returns array of moves which are possible from a player
# ---> .makeMove(move,player) // executes a move for a given player
# ---> .undoMove() // will undo the last move made
class MaxMinAlg():
    def __init__(self,maxDepth = 1,alpha = -99999,beta = 99999):
        self.maxDepth = maxDepth
        self.alpha = alpha
        self.beta = beta
    
    def Eval(self,board,player):
        self.player = player
        self.board = board
        (_,m) = self.Max(0,self.alpha,self.beta)
        return m
    
    def Max(self,depth,alpha,beta):
        maxv = -4-depth
        maxM = None
        result = self.board.status(self.player)

        # result will be loss -1 / win +1 / draw 0 / not finished 999
        if(result != 999):
            return (result, maxM)
        else:
            for move in self.board.getPossibleMoves(self.player): 
                self.board.makeMove(move,self.player)
                (m, _) = self.Min(depth+1,alpha,beta)
                self.board.undoMove()
                if m > maxv:
                    maxv = m
                    maxM = move
                alpha = max(maxv,alpha)
                if(beta <= alpha):
                    return(alpha,move)

            return (maxv, maxM)

    def Min(self,depth,alpha,beta):
        minv = 4+depth
        minM = None
        result = self.board.status(self.player)
        # result will be loss -1 / win +1 / draw 0 / not finished 999
        if(result != 999):
            return (result, minM)
        elif(depth >= self.maxDepth):
            return (minv,minM)
        else:
            for move in self.board.getPossibleMoves(self.player.opponent) : 
                self.board.makeMove(move,self.player.opponent)
                (m, _) = self.Max(depth+1,alpha,beta)
                self.board.undoMove()
                if m < minv:
                    minv = m
                    minM = move
                beta = min(beta,minv)
                if(beta <= alpha):
                    return(alpha,move)
            return (minv, minM)