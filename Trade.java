import java.util.*;


public class Trade {
        String ticker;
        String Date;
        double price;
        int qty;
        // String acct;
        boolean buy;
        Account acct;

        public Trade(String ticker, String date, double price, int qty, boolean buy, Account acct) {
            this.Date = date;
            this.ticker = ticker;
            this.price = price;
            this.qty = qty;
            // this.acct = acct;
            this.buy = buy;
            this.acct = acct;
        }
}