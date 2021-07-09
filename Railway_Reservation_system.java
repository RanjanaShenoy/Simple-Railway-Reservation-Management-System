import java.util.*;
import java.util.Scanner;

class railwayres{
	LinkedList<String> confirmed = new LinkedList<String>();   // reservation confirmed list
	LinkedList<String> rac = new LinkedList<String>();       // rac - reservation against cancellation
	LinkedList<String> waitlist = new LinkedList<String>();   // waitlist- stores reservations that are in the waiting list
	
	int C,R,W;    
	
	railwayres(int c1,int r1,int w1)       // C,R,W  - capacity of confirmed, rac, and waitlist 
	{
		 C=c1;
		 R=r1;
		 W=w1;
	}

	void reservation_confirmed(String pname) {

    	confirmed.add(pname);
    	System.out.println("Reservation confirmed in confirmed list");
        System.out.println(confirmed);			
	}
	
	void reservation_rac(String pname) {
		rac.add(pname);
		System.out.println("Reservation confirmed in rac");
		System.out.println(rac);
	}
	
	public void reservation_wait(String pname) {
		waitlist.add(pname);
		System.out.println("Reservation confirmed in waiting list");
		System.out.println(waitlist);
	}
	  
	void cancel_confirmed(String can_name){
		
		int num= confirmed.indexOf(can_name);
   	  String rem=confirmed.remove(num);               //fetch the element  to delete
   	  System.out.println("Element Removed:"+ rem);              
   	  
   	  	String elem2=rac.remove();              //removes the element from rac and adds to confirmed 
		  confirmed.add(elem2);
		  
		  String elem3=waitlist.remove();          // removes the element from waitlist and adds to rac
		  rac.add(elem3);
	}
	
	void cancel_rsa(String can_name){
		 int num2= rac.indexOf(can_name);                //fetch the element to delete
   	  String rem2=rac.remove(num2);
   	  System.out.println("Element Removed:"+ rem2);     
   	  
   	  String elem4=waitlist.remove();           // removes the element from waitlist and adds to rac
		  rac.add(elem4);
	}

	void cancel_wait(String can_name){
	  int num3= waitlist.indexOf(can_name);                        //fetch the element to delete
  	  String rem3=waitlist.remove(num3);
  	  System.out.println("Element Removed:"+ rem3); 
	}
	
	void list_details()                            // displays details of all lists
	{
		
		System.out.println("confirmed :"+confirmed);
        System.out.println("rac :"+ rac);
        System.out.println("waitlist:"+waitlist);
	}
	
	void settings()                    // displays the details about the capacity and the vacancy of each list.
	{
        System.out.println("Total No. of tickets (Capacity) of Confirmed:"+C+"\n rac: "+R+"\n Waitlist:"+W);
		
		 System.out.println("people under confirmed :"+ confirmed);
		 System.out.println("people under rac :"+ rac);
		 System.out.println("people under waitlist:"+ waitlist);       
		
		 System.out.println("Tickets already Booked in  Confirmed:"+confirmed.size() +"\n"+"Tickets RAC:"+rac.size()+"\n"+"Tickets in waiting list"+waitlist.size());
	}
	
}
public class reservation {
	public static void main(String args[])
	{
			
			
			railwayres res= new railwayres(10,10,10);
			Scanner scan=new Scanner(System.in);
			
			int out=0;
			
			while(true)
			{
			
			System.out.println("Enter your choice: 1: Reservation\t 2: Cancellation\t 3: Details of availability\t  4: Settings\t  0: Exit");
			int choice=scan.nextInt();
				
				switch(choice)
				{
				 
				case 0 : System.out.println("-------EXIT-------");
		                  out=1;
	          	          break;	   
				
				case 1 : System.out.println("-------RESERVATION-------");
				              System.out.println("Enter your name:");
				              String pname= scan.next();
				              
				              System.out.println("Confirm Reservation? (Enter 1 for yes or 0 for no)");
				              int resp=scan.nextInt();
				            
				              if(resp==1) {
				            	  if(res.confirmed.size()<res.C)    // C- capacity of confirmed
					              {
				            		  res.reservation_confirmed(pname);
					              }
					              
				            	  else if(res.confirmed.size()>=res.C)   
					              {
					            	 System.out.println("No vacancy at Confirmation List. Reserve in the RAC? (Enter 1 for yes or 0 for no)");
					            	 int resp2=scan.nextInt();
					            	 if(resp2==1)
					            	 {
					            		 if(res.rac.size()<res.R)           // R-capacity of rac
					            			 res.reservation_rac(pname);
					            		 
					            		 else {
					            			 System.out.println("No vacancy at rac . Reserve in the waiting list? (Enter 1 for yes or 0 for no)");
					            			 int resp3=scan.nextInt();
					            			 
					            			 if(res.waitlist.size()<res.W && (resp3==1))     // W- capacity of waitlist
					            			 {
					            				 res.reservation_wait(pname);
					            			 }
					            			 
					            			 else if (res.waitlist.size()>=res.W)
					             				 System.out.println("No Vacancy in the Waiting list. All lists are full");
					  
					             		 }
					            			 
					            	 }
					            	 
					            	 
					              } 
				              }
				               
							break;
			
					case 2 : System.out.println("-------CANCELLATION-------");
				             System.out.println("Enter the name for cancellation: ");    //name of the person whose reservation is to be cancelled.
				             String can_name=scan.next();
				             
				              if(res.confirmed.contains(can_name))                             // checks if name is present in confirmed 
				              {
				            	  res.cancel_confirmed(can_name);           	  
				              }
				              
				              else if(res.rac.contains(can_name))
				              {
				            	   res.cancel_rsa(can_name);
				              }
				              
				              else if(res.waitlist.contains(can_name))
				              {
				            	  res.cancel_wait(can_name);
				              }
				             
			              	break;
			              
					case 3 : System.out.println("Details of Three list\n-------AVAILABILITY-------");
					          res.list_details();
				          	 break;
				          	
				          	
					case 4 : System.out.println("-------SETTINGS-------");
								  res.settings(); 
								 break;
				          	  
					default : System.out.println("INVALID OPTION");
				
				}
				if(out==1)
					break;
					
			}
			res.settings();			
	}
}
