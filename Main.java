import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.text.*;

public class Main {
    public Account acct;
    static public Scanner scan = new Scanner(System.in);
    static HashMap<String, Account> accounts = MiniLiquidation.accounts;


    public static void main(String[] args) {
        System.out.println("Hello Mini Liquidation!");
        MiniLiquidation program = new MiniLiquidation();
        program.scan = scan;
        
        System.out.print("please enter your account: ");
        String handle = scan.nextLine();
        if (!accounts.containsKey(handle)) {
          System.out.print("please enter your liquidation: ");
          String liquid = scan.nextLine();
          AcctType type = AcctType.FIFO;
          switch (liquid) {
            case "FIFO":
              type = AcctType.FIFO;
              break;
            case "LIFO":
              type = AcctType.LIFO;
              break;
            default:
              break;
          }
          accounts.put(handle, new Account(handle, type));
        }
        program.acct = accounts.get(handle);
        while (true) {
            program.inputTrade();
            System.out.println();

            program.displayPosition();
            System.out.println();
        }
	}
}