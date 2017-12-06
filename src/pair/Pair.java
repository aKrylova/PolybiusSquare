package pair;

public class Pair {
    private int x;
    private int y;
    
    public Pair(){
        x = 0;
        y = 0;
    }
    
    public Pair(int nx, int ny){
        x = nx;
        y = ny;
    }
    
   public void set_x(int nx){
       x = nx;
   }
   
   public void set_y(int ny){
       y = ny;
   }
   
   public int get_x(){
       return x;
   }
   
   public int get_y(){
       return y;
   }
           
}
