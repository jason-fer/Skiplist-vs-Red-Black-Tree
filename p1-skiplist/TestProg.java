import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class TestProg
{

   public static void main(String[] args)
   {
	  final SkipList S = new SkipList();
	  final Lock lock = new ReentrantLock();
	  final Random r = new Random();
	  for(int j=0;j<100;j++){
		  new Thread(new Runnable(){ 
			  public void run() { 
				  int i;
//				  lock.lock();//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
				  for ( i = 0; i < 3000; i++ )
			      {
					 int b = r.nextInt(3000);//生成29以内的随机数
			         S.insert("" + b,  b);
//			         S.insert("" + i,  i);
			      }
//				  lock.unlock();//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
				  System.out.println(Thread.currentThread().getName()+": "+S.lockNum+"    ");
				  S.lockNum=0;
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