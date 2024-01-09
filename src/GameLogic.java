import java.sql.SQLOutput;
import java.util.ArrayList;


public class GameLogic implements PlayableLogic {
    private ConcretePlayer atck;
    private ConcretePlayer def;
    private boolean atck_turn = true;

    private Piece[][] Board = new Piece[11][11];

    public GameLogic() {
        create_players();
        reset();
    }

    @Override
    public boolean move(Position a, Position b) {
        Piece from = getPieceAtPosition(a);
        if (atck_turn) {
            if (from.getOwner() != this.atck) {
                return false;
            } else {
                boolean ans = logic_move(a, b);
                if (ans) {
                    atck_turn = false;
                }
                kill(b, atck);
                //   kill2(b,atck);
                return ans;

            }
        } else {
            if (from.getOwner() != this.def) {
                return false;
            } else {
                boolean ans = logic_move(a, b);
                if (ans) {
                    atck_turn = true;
                }
                kill(b, def);
                //  kill2(b,def);
                return ans;
            }
        }


    }

    public boolean kill(Position b, ConcretePlayer p) {
        // check for the first layers
        boolean ans=false;
        // check for the second layers
        // x== line zero heat spot zero
        Position kill_spot = new Position(b.getX(), 0);
        Piece kill_p = this.getPieceAtPosition(kill_spot);
        if (b.getY() == 1 && kill_p != null && kill_p.getOwner() != p) {
            this.Board[b.getX()][0] = null;
            ans=true;
        }
        // x==zero kill
        kill_spot = new Position(b.getX(), 10);
        kill_p = this.getPieceAtPosition(kill_spot);
        if (b.getY() == 9 && kill_p != null && kill_p.getOwner() != p) {
            this.Board[b.getX()][10] = null;
            ans=true;
        }
        kill_spot = new Position(0, b.getY());
        kill_p = this.getPieceAtPosition(kill_spot);
        if (b.getX() == 1 && kill_p != null && kill_p.getOwner() != p) {
            this.Board[0][b.getY()] = null;
            ans=true;
        }
        kill_spot = new Position(10, b.getY());
        kill_p = this.getPieceAtPosition(kill_spot);
        if (b.getX() == 9 && kill_p != null && kill_p.getOwner() != p) {
            this.Board[10][b.getY()] = null;
            ans=true;
        }
//         oponnet piece
         kill_spot=new Position(b.getX()-1,b.getY());
        Piece oppent_could_be_kill = this.getPieceAtPosition(kill_spot);
        if(oppent_could_be_kill != null&&checkbounds(b))
        if((oppent_could_be_kill.getOwner() != p))
        {
            //my team
            Position position_my_teamaete_to_kill=new Position(b.getX()-2,b.getY());
            Piece my_teamaete_to_kill = this.getPieceAtPosition(position_my_teamaete_to_kill);
            if (my_teamaete_to_kill==p)
            {
                this.Board[kill_spot.getX()][kill_spot.getY()]=null;
            }
        }

return ans;
    }
//    public boolean  kill2(Position b,ConcretePlayer p)
//    {
//
//
//
//
//    }
    public void SetBoard(Position a,Piece l){
        Board[a.getX()][a.getY()] = l;
    }
    public boolean checkbounds (Position b) {
        if (b.getY() != 0) {
            if (b.getY() + 1 > 10 || b.getY() - 1 < 0 || b.getY() + 2 > 10 || b.getY() - 2 > 0) {
                return false;
            }
        }
        if (b.getX() != 0) {
            {
                if (b.getX() + 1 > 10 || b.getX() - 1 < 0 || b.getX() + 2 > 10 || b.getX() - 2 > 0) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public Piece getPieceAtPosition(Position position) {
        return Board[position.getX()][position.getY()];
    }

    @Override
    public Player getFirstPlayer() {
        return atck;
    }

    @Override
    public Player getSecondPlayer() {
        return def;
    }

    @Override
    public boolean isGameFinished() {
        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return atck_turn;
    }

    @Override
    public void reset() {
        Board = new Piece[11][11];
        // player one setting
        create_players();


        // setting pieces locations and id
        for (int i = 3; i < 8; i++) {
            this.Board[i][0] = new Pawn((this.atck), "A"+(i - 2));
            this.Board[i][10] = new Pawn((this.atck), "A"+(i + 17));
        }
        Board[5][1] = new Pawn((this.atck),"A6");
        this.Board[0][3] = new Pawn((this.atck),"A7");
        this.Board[10][3] = new Pawn((this.atck),"A8");
        this.Board[0][4] = new Pawn((this.atck),"A9");
        this.Board[10][4] = new Pawn((this.atck),"A10");
        this.Board[0][5] = new Pawn((this.atck),"A11");
        this.Board[1][5] = new Pawn((this.atck),"A12");
        this.Board[9][5] = new Pawn((this.atck),"A13");
        this.Board[10][5] = new Pawn((this.atck),"A14");
        this.Board[0][6] = new Pawn((this.atck),"A15");
        this.Board[10][6] = new Pawn((this.atck),"A16");
        this.Board[0][7] = new Pawn((this.atck),"A17");
        this.Board[10][7] = new Pawn((this.atck),"A18");
        this.Board[5][9] = new Pawn((this.atck),"A19");
        this.Board[5][3] = new Pawn((this.def),"D1");
        this.Board[4][4] = new Pawn((this.def),"D2");
        this.Board[5][4] = new Pawn((this.def),"D3");
        this.Board[6][4] = new Pawn((this.def),"D4");
        this.Board[3][5] = new Pawn((this.def),"D5");
        this.Board[4][5] = new Pawn((this.def),"D6");
        this.Board[5][5] = new King((this.def),"K7");
        this.Board[6][5] = new Pawn((this.def),"D8");
        this.Board[7][5] = new Pawn((this.def),"D9");
        this.Board[4][6] = new Pawn((this.def),"D10");
        this.Board[5][6] = new Pawn((this.def),"D11");
        this.Board[6][6] = new Pawn((this.def),"D12");
        this.Board[5][7] = new Pawn((this.def),"D13");

    }


    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return Board.length;
    }

    //////////////////////////////////////////////PRIVATE METHODS//////////////////////////////////////////////
    private void create_players() {
        this.atck = new ConcretePlayer(false);
        this.def = new ConcretePlayer(true);

    }

    private boolean logic_move(Position a, Position b) {
        Piece from = getPieceAtPosition(a);
        Piece to = getPieceAtPosition(b);
        String s = from.getType();
        if( s.equals("♙") ){
            if ((b.getX()==0&&b.getY()==0)||(b.getX()==0&&b.getY()==10)||(b.getX()==10&&b.getY()==0)||(b.getX()==10&&b.getY()==10))
                return false;
        }
        if (a.getX() == b.getX()) {
            if (a.getY() - b.getY() < 0) {
                for (int i = a.getY()+1; i <= b.getY(); i++) {
                    if (getPieceAtPosition(new Position(a.getX(), i)) != null) {
                        //that was the orinal
                        //if (getPieceAtPosition(new Position(i, a.getX())) != null)
                        return false;
                    }
                }
                SetBoard(a, null);
                SetBoard(b, from);
                return true;

            }
            if (a.getY() - b.getY() >0) {
                for (int i = a.getY()-1; i >= b.getY(); i--) {
                    if (getPieceAtPosition(new Position(a.getX(), i)) != null) {
                        //that was the orinal
                        //if (getPieceAtPosition(new Position(i, a.getX())) != null)
                        return false;
                    }
                }
                SetBoard(a, null);
                SetBoard(b, from);
                return true;

            }

        }
        if (a.getY() == b.getY()) {
            if (a.getX() - b.getX() < 0) {
                for (int i = a.getX()+1; i <= b.getX(); i++) {
                    if (getPieceAtPosition(new Position(i, a.getY())) != null) {
                        return false;
                    }


                }
                SetBoard(a, null);
                SetBoard(b, from);
                return true;

            }
            if (a.getX() - b.getX() > 0) {   //a.x > b.x
                for (int i = a.getX()-1; i >= b.getX(); i--) {
                    if (getPieceAtPosition(new Position(i, a.getY())) != null) {
                        return false;
                    }
                }
                SetBoard(a, null);
                SetBoard(b, from);
                return true;

            }

        }

        return false;
    }


}