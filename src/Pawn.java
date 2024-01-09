public class Pawn extends ConcretePiece {
    private int kill ;
    public Pawn(ConcretePlayer p,String name) {
        super(p,name);
        this.kill=0;
    }
    @Override
    public Player getOwner() {
        return super.getOwner();
    }

    @Override
    public String getType() {
        return "♙";
    }

    public int getKill() {
        return kill;
    }
    public void inc_kill()
    {
        this.kill=kill+1;
    }
}
