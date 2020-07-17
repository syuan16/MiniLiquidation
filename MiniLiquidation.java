import java.text.*;
import java.util.*;

public class MiniLiquidation {

    public Account acct;
    HashMap<String, Trade> trades = new HashMap<>();
    
    // HashMap<String, Valuation> valuations = new HashMap<>();
    HashMap<Account, List<Position>> positions = new HashMap<>();
    HashMap<Account, List<Valuation>> valuations = new HashMap<>();
    // HashMap<String, AcctType> accounts = new HashMap<>();
    HashMap<Account, HashMap<String, List<Share>>> portfolio = new HashMap<>();
    static public HashMap<String, Account> accounts = new HashMap<>();
    public Scanner scan;
    // public MiniLiquidation()

    void inputTrade() {
        System.out.print("input new trade?: ");
        String s = scan.nextLine();
        if (s.equals("Yes")) {
            // System.out.print("What account?: ");
            // String acct = scan.nextLine();
            // if (!accounts.containsKey(acct))
            //   accounts.put(acct, AcctType.FIFO);
            System.out.print("input ticker: ");
            String ticker = scan.nextLine();            
            System.out.print("input Date: ");
            String date = scan.nextLine();
            // System.out.println
            // DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            // Date date = null;
            // try {
            //   date = format.parse(string);
            // }
            // catch (Exception e) {
            //   return;
            // }
            
            System.out.print("buy or sell?: ");
            boolean buy = scan.nextLine().equals("buy") ? true : false;
            System.out.print("how many shares?: ");
            int qty = scan.nextInt(); scan.nextLine();
            System.out.print("at what price?: ");
            double price = scan.nextDouble(); scan.nextLine();
            System.out.print("What's the market price?: ");
            double mp = scan.nextDouble(); scan.nextLine();
            // Trade trade = new Trade(ticker, date, price, qty, acct, buy);
            // trades.put(trade.Date, trade);
            if (buy) {
                HashMap<String, List<Share>> map = portfolio.getOrDefault(acct, new HashMap<>());
                
                portfolio.put(acct, map);
                List<Share> list = map.getOrDefault(ticker, new ArrayList<>());
                map.put(ticker, list);
                list.add(new Share(date, qty, price));
                
                List<Position> listPos = positions.getOrDefault(acct, new ArrayList<>());
                positions.put(acct, listPos);
                positions.get(acct).add(new Position(acct, ticker, date, getQty(acct, ticker, date), getAvgCost(acct, ticker, date), 0));


                List<Valuation> listVal = valuations.getOrDefault(acct, new ArrayList<>());
                valuations.put(acct, listVal);
                valuations.get(acct).add(new Valuation(acct, ticker, date, getQty(acct, ticker, date) * mp, getUnreal(acct, ticker, date, mp), mp));
            }
            else {
                double realized = 0.0;
                while (qty > 0) {
                  // System.out.println("qty: "  + qty);
                    List<Share> list = portfolio.get(acct).get(ticker);
                    // if ()
                    if (acct.type == AcctType.FIFO) {
                        int temp = qty;
                        qty = Math.max(0, qty - list.get(0).qty);
                        list.get(0).qty = Math.max(0, list.get(0).qty - temp);
                        realized += (temp-qty) * (price - list.get(0).price);
                        if (list.get(0).qty == 0)
                            list.remove(0);
                        
                    }
                    else if (acct.type == AcctType.LIFO) {
                        int temp = qty;
                        int len = list.size();
                        qty = Math.max(0, qty - list.get(len-1).qty);
                        list.get(len-1).qty = Math.max(0, list.get(len-1).qty - temp);
                        realized += (temp-qty) * (price - list.get(len-1).price);
                        if (list.get(len-1).qty == 0)
                            list.remove(len-1);
                    }
                }
                positions.getOrDefault(acct, new ArrayList<>());
                positions.get(acct).add(new Position(acct, ticker, date, getQty(acct, ticker, date), getAvgCost(acct, ticker, date), realized));

                List<Valuation> listVal = valuations.getOrDefault(acct, new ArrayList<>());
                valuations.put(acct, listVal);
                valuations.get(acct).add(new Valuation(acct, ticker, date, getQty(acct, ticker, date) * mp, getUnreal(acct, ticker, date, mp), mp));
            }
            
            
        }        
    }

    double getUnreal(Account acct, String ticker, String date, double mp) {
        double sum = 0;
        List<Share> list = portfolio.get(acct).get(ticker);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for (Share share : list) {
          Date date1;
          Date date2;
          try {
              date1 = format.parse(share.date);
              date2 = format.parse(date);
          }
          catch (Exception e) {
            System.out.println(e);
            return 0;
          }
          if (!date1.after(date2)) {
            sum += share.qty * (mp - share.price);
          }
        }
        return sum;
    }

    int getQty(Account acct, String ticker, String date) {
        int sum = 0;
        List<Share> list = portfolio.get(acct).get(ticker);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for (Share share : list) {
          Date date1;
          Date date2;
          try {
              date1 = format.parse(share.date);
              date2 = format.parse(date);
          }
          catch (Exception e) {
            System.out.println(e);
            return 0;
          }
          
            if (!date1.after(date2)) {
                sum += share.qty;
            }
        }
        return sum;
    }

    double getAvgCost(Account acct, String ticker, String date) {
        double cost = 0.0;
        int sum = 0;
        List<Share> list = portfolio.get(acct).get(ticker);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for (Share share : list) {
          // System.out.println("Account: " + acct.acctName);
          // System.out.println("price " + share.price);
          Date date1;
          Date date2;
          try {
              date1 = format.parse(share.date);
              date2 = format.parse(date);
          }
          catch (Exception e) {
            System.out.println(e);
            return 0.0;
          }
            if (!date1.after(date2)) {
                sum += share.qty;
                cost += share.price * share.qty;
            }
        }
        return cost / sum;

        // if (account.get(acct) == AcctType.FIFO) {

        // }
        // else if (account.get(acct) == AcctType.LIFO) {

        // }
    }

    void display() {
        System.out.print("display positions and valuations?: ");
        String s = scan.nextLine();
        if (s.equals("Yes")) {
            // System.out.print("What account?: ");
            // String acct = scan.nextLine();
            List<Position> list = positions.get(acct);
            List<Valuation> list2 = valuations.get(acct);
            System.out.println("Positions: ");
            int len = list.size();
            Position p = list.get(len-1);
            // for (Position p : list) {
                System.out.println("Stock: " + p.ticker);
                System.out.println("Date: " + p.date);
                System.out.println("Quantity: " + p.qty);
                System.out.println("Average Cost: " + p.avgCost);
                System.out.println("Realized G/L: " + p.realized_GL);
            // }
            System.out.println();
            Valuation v = list2.get(len-1);
            System.out.println("Valuations: ");
            // for (Valuation v : list2) {
                System.out.println("Stock: " + v.ticker);
                System.out.println("Date: " + v.date);
                System.out.println("Market Price: " + v.price);
                System.out.println("Market Value: " + v.MV);
                System.out.println("Unrealized G/L: " + v.unrealized);
            // }
            // System.out.print("what date: ");
            // String string = scan.nextLine();
            // DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            // Date date = format.parse(string);
            // System.out.print("what stock: ");
            // String ticker = scan.nextLine();
            // int qty = getQty(acct, ticker, date);
            // int avgCost = getAvgCost(acct, ticker, date);
            // int realizedGL = getRealized(acct, ticker, date);
            // System.out.println("Account: " + acct);
            // System.out.println("Stock: " + ticker);
            // System.out.println("Date: " + date);
            // System.out.println("Quantity: " + qty);
            // System.out.println("Average Cost: " + avgCost);
        }
    }

}