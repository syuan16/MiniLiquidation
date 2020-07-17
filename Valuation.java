import java.util.*;

public  class Valuation {
        Account acct;
        String ticker;
        String date;
        double MV;
        double unrealized;
        double price;

        public Valuation(Account acct, String ticker, String date, double MV, double unrealized, double price) {
            this.acct = acct;
            this.ticker = ticker;
            this.MV = MV;
            this.unrealized = unrealized;
            this.date = date;
            this.price = price;
        }
    }