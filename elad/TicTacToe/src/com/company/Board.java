package com.company;

/**
 * Created by eladlavi on 27/04/2017.
 */
public class Board {

    private CellValue[] board;
    User x, o;
    private int moveCount;
    private boolean isXturn;

    public Board(User x, User o){
        this.x = x;
        this.o = o;
        board = new CellValue[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = CellValue.EMPTY;
        }
        moveCount = 0;
        isXturn = true;
    }

    public MoveResult makeMove(User user, int cell){
        if(cell < 0 || cell >= board.length)
            return MoveResult.INVALID_MOVE;
        if((user == x && isXturn) || ((user == o) && !isXturn)){
            if(board[cell] == CellValue.EMPTY){
                board[cell] = isXturn ? CellValue.X : CellValue.O;
                isXturn = !isXturn;
                moveCount++;
                return MoveResult.VALID_MOVE;
            }
        }
        return MoveResult.INVALID_MOVE;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            switch (board[i]){
                case O:
                    stringBuilder.append('2');
                    break;
                case X:
                    stringBuilder.append('1');
                    break;
                case EMPTY:
                    stringBuilder.append('0');
                    break;
            }
        }
        return stringBuilder.toString();
    }


    public enum CellValue{
        X, O, EMPTY;
    }
    public enum MoveResult{
        VALID_MOVE, INVALID_MOVE, VICTORY, DRAW
    }
}
