import java.util.*;

public  class Position {
        String acct;
        String ticker;
        String date;
        int qty;
        double avgCost;
        double realized_GL;
        public Position(String acct, String ticker, String date, int qty, double avgCost, double realized_GL) {
            this.acct = acct;
            this.ticker = ticker;
            this.qty = qty;
            this.avgCost = avgCost;
            this.realized_GL = realized_GL;
            this.date = date;
        }
    }