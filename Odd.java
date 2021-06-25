public class Odd 
{
   
    boolean val=true;
   public boolean isOdd(Node r) 
   {  
    if(!r.isLeaf()){
       if(r.numChildren()%2==0){
          val = false;
          return val;
       }
       for (Node u:r.getChildren())
          isOdd(u);     
    }
    return val;
   }
 }