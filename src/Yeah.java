
public class BufferedReaderDemo {
	
	import java.io.BufferedReader;
	import java.io.FileInputStream;
	import java.io.InputStream;
	import java.io.InputStreamReader;

	public class BufferedReaderDemo {
	   public static void main(String[] args) throws Exception {
	      
	      InputStream is = null; 
	      InputStreamReader isr = null;
	      BufferedReader br = null;

	      try{
	         // open input stream test.txt for reading purpose.
	         is = new FileInputStream("c:/test.txt");
	         
	         // create new input stream reader
	         isr = new InputStreamReader(is);
	         
	         // create new buffered reader
	         br = new BufferedReader(isr);

	         // reads and prints BufferedReader
	         System.out.println((char)br.read());
	         System.out.println((char)br.read());
	         
	         // mark invoked at this position
	         br.mark(26);
	         System.out.println("mark() invoked");
	         System.out.println((char)br.read());
	         System.out.println((char)br.read());
	         
	         // reset() repositioned the stream to the mark
	         br.reset();
	         System.out.println("reset() invoked");
	         System.out.println((char)br.read());
	         System.out.println((char)br.read());
	         
	      } catch (Exception e) {
	         
	         // exception occurred.
	         e.printStackTrace();
	      }finally{

	         // releases any system resources associated with the stream
	         if(is!=null)
	            is.close();
	         if(isr!=null)
	            isr.close();
	         if(br!=null)
	            br.close();
	      }
	   }
	
	
	       
	}                                 


