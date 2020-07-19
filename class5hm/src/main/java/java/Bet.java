package java;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Bet {

    private String name;
    private int amount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
