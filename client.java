
import java.io.*;
import java.net.*;
import java.util.*;
/*author @sid28183997
created on Feb,19 2017*/
public class client{
public static void main(String args[]) throws IOException{
	if(args.length<2 ){
        System.out.println("Incomplete arguments : EXIT(0)");
        System.exit(0);
    }
    else if(!args[1].matches("[0-9]+")){
        System.out.println("Invalid arguments : EXIT(0)");
        System.exit(0);
    }
    try{
	 InetAddress ip = InetAddress.getByName(args[0]); // get ip address of remote server
        int port = Integer.parseInt(args[1]); // get port no.
        System.out.println("./client "+ip);
        Scanner sc = new Scanner(System.in);
 
        // Open the socket connection.
        Socket s = new Socket(ip, port);
 			
        // get the input and output stream
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
 		System.out.println("recieve: "+dis.readUTF());
        while (true) // start of loop
        {
 
            String inp = sc.nextLine(); // input
 
           
 
            // send the input to server
            dos.writeUTF(inp);
 
            
            String ans = dis.readUTF();

            // exception handling
            if(ans.equals("-1")){
            	System.out.println("recieve: incorrect operation command");    
            }
            else if(ans.equals("-2")){
            	System.out.println("recieve: number of inputs is less than two");     
            }
            else if(ans.equals("-3")){
            	System.out.println("recieve: number of inputs is more than four"); 
            }
            else if(ans.equals("-4")){
            	System.out.println("recieve: one or more of the inputs contain(s) non-number(s)");   
            }
            else if(ans.equals("-5")){
            	System.out.println("recieve: exit");   
            	try{
            	s.close();
            	System.out.println("Conn closed");
            }catch(IOException u){

            }break;
        }
            else{
            	System.out.println("recieve: "+ans); // display result fetched from server
            }
}
}catch(IOException t){
    System.out.println("Invalid host or host unreachable");
}

           
} // end main
} // end class
