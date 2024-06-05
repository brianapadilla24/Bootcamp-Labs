package org.example;
//chip is 1.50
public class Chip {
    private double chipPrice;

    public double getChipPrice() {
        return chipPrice;
    }

    public void setChipPrice(double chipPrice) {
        this.chipPrice = chipPrice;
    }
    @Override
    public String toString(){
        return "Chip Price: "+ getChipPrice()+"\n━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━\n";
    }
}