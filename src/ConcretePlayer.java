public class ConcretePlayer implements Player{
    // this is  item that said how is the right now players
    private  int counter_win;
    private  boolean atck;

    public  ConcretePlayer(boolean atck)
    {
        this.atck=atck;
        this.counter_win=0;
        // we intlize the class in we said that atck that is red will be true
        // we intlize the sum of win at the beging will be zero
    }

    @Override
    public boolean isPlayerOne() {
        return this.atck;
    }
public  void inc()
{
    this.counter_win++;
}
    @Override
    public int getWins() {
        return counter_win  ;
    }
}
