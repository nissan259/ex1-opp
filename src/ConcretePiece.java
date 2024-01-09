public class ConcretePiece  implements Piece{
    private ConcretePlayer onwer;
    private String name;
    private  int num_step;
    public ConcretePiece(ConcretePlayer p,String name)
    {
        this.onwer=p;
        this.name=name;
        this.num_step=0;
    }
    @Override
    public Player getOwner() {
        return onwer;
    }

    @Override
    public String getType() {
        return "";
    }

    public int getNum_step() {
        return num_step;
    }
    public  void inc_step()
    {
         num_step++;
    }
}
