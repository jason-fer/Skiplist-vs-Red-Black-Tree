import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class TestProg
{

   public static void main(String[] args)
   {
	  final SkipList S = new SkipList();
	  final Lock lock = new ReentrantLock();
	  for(int j=0;j<2;j++){
		  new Thread(new Runnable(){ 
			  public void run() { 
				  int i;
				  lock.lock();//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
				  for ( i = 0; i < 100; i++ )
			      {
			         S.insert("" + i,  i);
			      }
				  lock.unlock();//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
				  System.out.println(Thread.currentThread().getName()+": "+S.lockNum+"    ");
			  }
		  }).start();
	  }
	  
//      S.remove("4", 4);
//      S.remove("7", 7);
//      System.out.println("------");
//      S.insert("" + 15,  15);
//      S.printVertical();
//      System.out.println("haha  "+S.lockNum+"    "+S.lockNumH);
   }

}
