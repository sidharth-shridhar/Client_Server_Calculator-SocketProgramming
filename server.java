import java.io.*;
import java.net.*;
import java.util.*;
/*author @sid28183997
created on Feb,19 2017*/
public class server{ //main class

public static void main(String args[]) throws IOException{ //main function
        if(args.length<1 ){
        System.out.println("Incomplete arguments : EXIT(0)");
        System.exit(0);
    }
      else if(!args[0].matches("[0-9]+")){
        System.out.println("Invalid argument : EXIT(0)");
        System.exit(0);
    }
        do{ //master loop
                //declaration and intialization
        ServerSocket ss = new ServerSocket(Integer.parseInt(args[0])); // open the socket
        System.out.println("./server "+args[0]);
        Socket sok = ss.accept(); // accept the incoming connection
         InetAddress ad=sok.getInetAddress(); // get IP address of remote client
        DataInputStream din=new DataInputStream(sok.getInputStream());
        DataOutputStream dout=new  DataOutputStream(sok.getOutputStream());
        
        System.out.println("get connection from "+ad);
        dout.writeUTF("Hello!"); // sends the hello message to client
        int rsl=0,op,x=0;
        while(true){// sub loop
                
        	String in=din.readUTF();
        	if(in.equals("bye")){ // when input recieved is bye 
        		try{
                                dout.writeUTF("-5");
                                System.out.println("get: "+in+",return -5");
                                din.close();
                                dout.close();
                                 ss.close();     //all sockets closed 
                        
                        
                       }catch(IOException q){
                        System.out.println("Socket not closed");// Exception handling
                       }
                       finally{
                        break; // termination of sub loop
                }
                       


        	}
                else if(in.equals("terminate")){
                        try{
                                dout.writeUTF("-5");

                                System.out.println("get: "+in+",return -5");
                                din.close();
                                dout.close();
                                 ss.close();     
                        
                        
                       }catch(IOException q){
                        System.out.println("Socket not closed");
                       }
                       finally{
                        System.exit(0);// terminates the main program
                }
                }
                
        
                
                StringTokenizer stk=new StringTokenizer(in); //break the input string to tokens
                int count=stk.countTokens();        // count no. of token generated
                String opr=stk.nextToken();
                String pre="";
                String first="";
                int f=0;
                //if operator is invalid
                if(!(opr.equalsIgnoreCase("add") || opr.equalsIgnoreCase("subtract") || opr.equalsIgnoreCase("multiply"))){
                      System.out.println("get: "+in+", return -1");
                      dout.writeUTF("-1");

                }
                //if no. of operands is less than 2
                else if(count<3){
                        System.out.println("get: "+in+", return -2");
                      dout.writeUTF("-2");
                }
                // if no. of operands greater than 4
                else if(count>5){
                        System.out.println("get: "+in+", return -3");
                        dout.writeUTF("-3");
                }               
                 else{
                        
                        first=stk.nextToken(); // fetching the first operand
                        if(first.matches("[0-9]+")){ // verification of first token for integer(master if loop)
                        rsl=Integer.parseInt(first);
                        for(int i=2;i<count;i++){ // fetching tokens one after another(start of main logic loop)
                                pre=stk.nextToken();
                                if(pre.matches("[0-9]+")  ){ // verification of incoming consecutive tokens for integer

                                op=Integer.parseInt(pre);

                                if(opr.equalsIgnoreCase("add")){ // addition logic
                                        rsl=rsl+op;
                                }
                                else if(opr.equalsIgnoreCase("subtract")){  // subtraction logic

                                       
                                        rsl=rsl-op; 
                                }
                                else if(opr.equalsIgnoreCase("multiply")){ // multiplication logic
                                        rsl=rsl*op;
                                }
                        }
                        else{
                                System.out.println("get: "+in+", return -4"); // message printed on server side
                         dout.writeUTF("-4"); // returns -4 code to client as equation contains other than numbers for input other than first token
                         f=1;
                         rsl=0;
                         break;     
                        }
                        }
                }//end of for loop
                else{// first token verification failed
                                System.out.println("get: "+in+", return -4"); 
                         dout.writeUTF("-4");
                         f=1;
                         rsl=0;
                         
                        }//  end of else
                        if(f==0){ 
                        System.out.println("get: "+in+", return "+rsl); //if no error takes place return result
                        dout.writeUTF(Integer.toString(rsl));
                }
                        rsl=0; // re-intialization of calculation variables
                        f=0;
                }// end of master if else loop
                
      
      } // end of while sub loop
}while(true); // end of master do while loop
} // end of main
} // end of class
