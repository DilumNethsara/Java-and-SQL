package connecttodb;

import java.sql.*;
import java.util.Scanner;

public class ConnectToDB {

  
    public static void main(String[] args) {
        
        
        
        
        Connection c= null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:info.db");
            System.out.println("connect successfully");
            
            System.out.println("Enter \n* 1 - Add details\n 2 - Select  \n 3 - Search \n 4 - Delete \n 5 - Update");
            Scanner sc = new Scanner(System.in);
            int processIndex = sc.nextInt();
            
            Statement stmt = c.createStatement();
            
            
            switch(processIndex){
                case 1:
                    //add
                    Scanner u_nameIn = new Scanner(System.in);
                    Scanner passwordIn = new Scanner(System.in);
            
                    System.out.print("Enter admin name : ");
                    String u_name;
                    u_name = u_nameIn.nextLine();
            
                    System.out.print("Create a password : ");
                    String password;
                    password = passwordIn.nextLine();
            
                    String SQLadd = "insert into"
                            + " admins(u_name,password) "
                            + "values('"+u_name+"','"+password+"')";
                    
                    stmt.executeUpdate(SQLadd);
                    System.out.println("detail added");
                    stmt.close();
                    c.close();
                            
            break;
                case 2:
                    //selection           
                    String SQLread = "select * from admins";
                    ResultSet rs = stmt.executeQuery(SQLread);
                    System.out.println("id \t username \t password");
                    
                    while(rs.next()){
                        int idR = rs.getInt("id");
                        String userNameR = rs.getString("u_name");
                        String passwordR = rs.getString("password");
                        System.out.println(idR+"\t"+userNameR+"\t"+passwordR+"\t");
                    }
                    rs.close();
                    stmt.close();
                    c.close();
                    
                    break;                    
                    
                case 3:
                    //search
                    System.out.print("Enter ID : ");
                    int Sid = sc.nextInt();
                    String SQLsearch = "select * from admins where id = "+Sid;
                    ResultSet rs1 = stmt.executeQuery(SQLsearch);
                    System.out.println("id \t username \t password");
                    
                    while(rs1.next()){
                        int idR = rs1.getInt("id");
                        String userNameR = rs1.getString("u_name");
                        String passwordR = rs1.getString("password");
                        System.out.println(idR+"\t"+userNameR+"\t"+passwordR+"\t");
                    }
                    rs1.close();
                    stmt.close();
                    c.close();
                    
                    break;
                    
                case 4:
                    //delete
                    System.out.print("Delete ID : ");
                    int Did = sc.nextInt();
                    String SQLdelete = "delete from admins where id = "+Did;
                    stmt.executeUpdate(SQLdelete);
                    System.out.println("Record deteled");
                    
                    stmt.close();
                    c.close();
                                       
                    break;
                    
                case 5:
                    //update
                    System.out.print("Select ID : ");
                    Scanner UID = new Scanner(System.in);
                    int Uid = sc.nextInt();
                    
                    System.out.println("Change password : ");
                    String passwordU = UID.nextLine();
                    
                    String SQLupdate = "update admins set password = '"+passwordU+"' "+"where id = "+Uid;
                    stmt.executeUpdate(SQLupdate);
                    System.out.println("Password changed");
                    
                    stmt.close();
                    c.close();
                    
                    break;
                    
                default:
                    break;
                    
            }
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(0);
        }
    }
    
}
