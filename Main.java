import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.text.*;

public class Main {
    

    public static void main(String[] args) {
        System.out.println("Hello Mini Liquidation!");
        MiniLiquidation program = new MiniLiquidation();
        while (true) {
            program.inputTrade();
            System.out.println();
            System.out.println();
            System.out.println();
            program.displayPosition();
            System.out.println();
            System.out.println();
            System.out.println();
        }
	}
}