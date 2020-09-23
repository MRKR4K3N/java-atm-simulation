import java.util.Scanner;
import javax.swing.JOptionPane;

public class Atm{
  public static String[] users = {"user_uid000","user_uid001","user_uid002","user_uid003","user_uid004","user_uid005","user_uid006","user_uid007","user_uid008","user_uid009","user_uid010"};
  public static String[] user_pins = {"0000","0001","0002","0003","0004","0005","0006","0007","0008","0009","0010"};
  public static double[] amount = {994839.00,32344.00,332122.00,554233.00,345949.00,559293.00,7728389.00,443234.00,221223.00,2391292.00,9948494.00};
  public static int isauth_usruid = 0;

  public static void main(String[] args){
    int isout = 1; //0 exit | 1 logout

    System.out.print("+==========================+\n");
    System.out.print("|  Welcome to Virtual Atm  |\n");
    System.out.print("+==========================+\n");

    while (isout != 0) {
      if(login()){
        isout = user_access(isauth_usruid);
      }
    }
  }

  private static Boolean login(){
    Scanner usrIn = new Scanner(System.in);
    Boolean isauth = false;
    System.out.print("\nTo access the atm account please input username and user pin\n\n");
    System.out.print("Username > ");
    String usr_name = usrIn.nextLine();
    System.out.print("User PIN > ");
    String usr_pin = usrIn.nextLine();

    for (int i = 0; i < users.length ;i++ ) {
      if (users[i].equals(usr_name) && user_pins[i].equals(usr_pin)){
        isauth_usruid = i;
        isauth = true;
        break;
      }
    }
    if (!isauth){
      JOptionPane.showMessageDialog(null,"unauthorize user please use authorized user only");
    }
    return isauth;
  }

  private static void transfer(){
    Scanner usrInput = new Scanner(System.in);
    int target_uid = 0;
    double my_amount = amount[isauth_usruid];
    System.out.println("\nPlease choose the user you want to transfer:\n");
    for (int i = 0; i < users.length ; i++ ) {
      if (i != isauth_usruid){
        System.out.println(i+"] "+users[i]);
      }
    }
    while (true) {
      System.out.print("number > ");
      int uid = usrInput.nextInt();
      if (uid != isauth_usruid){
        target_uid = uid;
        break;
      }
    }
    while(true){
      System.out.print("the amount you want to transfer to the "+users[target_uid]+" > ");
      double transfer_amount = usrInput.nextDouble();
      if (transfer_amount > my_amount){
        System.out.println("the amount you want to transfer is to large then your amount");
      }else{
        amount[target_uid] += transfer_amount;
        amount[isauth_usruid] -= transfer_amount;
        JOptionPane.showMessageDialog(null,"transfer is successful!");
        break;
      }

    }

  }

  private static void deposit(){
    Scanner usrInput = new Scanner(System.in);
    System.out.println("this atm system have limit deposit, you can only deposit $500.00 only\n");
    System.out.print("deposit amount > ");
    while(true){
      double deposit_amount = usrInput.nextDouble();
      if (deposit_amount > 500.00){
        System.out.println("the deposit is over limit!");
      }else{
        amount[isauth_usruid] += deposit_amount;
        JOptionPane.showMessageDialog(null,"the deposit is successful");
        break;
      }
    }
  }

  private static void withdraw(){
    Scanner usrInput = new Scanner(System.in);
    System.out.println("this atm system has limit withdraw, you can only withdraw $2000.00 only\n");
    System.out.print("withdraw amount > ");
    while(true){
      double withdraw_amount = usrInput.nextDouble();
      if (withdraw_amount > 2000.00){
        System.out.println("withdraw value is over limit!");
      }else{
        amount[isauth_usruid] -= withdraw_amount;
        JOptionPane.showMessageDialog(null,"withdraw is successful!");
        break;
      }
    }
  }

  private static int user_access(int uid){
    //access by authorized user only
    Scanner usrInput = new Scanner(System.in);
    Boolean isDone = false;
    int isout = 1;
    String usr_name = users[uid];

    System.out.println("\n\nWelcome "+usr_name);

    do {
      System.out.println("atm menu");
      System.out.println("========");
      System.out.println("1] transfer ");
      System.out.println("2] deposit ");
      System.out.println("3] withdraw ");
      System.out.println("4] check amount \n");
      System.out.println("9] logout ");
      System.out.println("10] exit ");
      System.out.print("> ");
      int cmenu = usrInput.nextInt();

      // either i can use switch or if statement
      if (cmenu == 9){
        isout = 1; //logout
        isDone = true;
      }
      else if (cmenu == 10){
        isout = 0; //exit
        isDone = true;
      }
      else if (cmenu == 1){
        transfer();
      }
      else if (cmenu == 2){
        deposit();
      }
      else if (cmenu == 3){
        withdraw();
      }
      else if (cmenu == 4){
        String current_amount = "your current amount is: $"+ amount[isauth_usruid];
        JOptionPane.showMessageDialog(null,current_amount);
      }

    } while (!isDone);
    return isout;
  }

}
