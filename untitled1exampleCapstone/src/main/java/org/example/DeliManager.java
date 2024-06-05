package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.File;
import java.io.FileWriter;

import java.util.List;
public class DeliManager {

    public static void saveReceipt(List<Sandwich> sandwichList,List<Drink> drinkList,List<Chip> chipList){
        try{
            String receipt = LocalDate.now()+" - "+ LocalTime.now();
            String filePath = String.format("src/main/resources/Receipt.csv", receipt);
            File file = new File(filePath);
            boolean fileExists = file.exists();
            FileWriter fileWriter = new FileWriter(file);

            if(!fileExists || file.length() == 0){
                fileWriter.write("⋆｡‧˚ʚ\uD83C\uDF53ɞ˚‧｡⋆    Welcome to DELI's Deli    ⋆｡‧˚ʚ\uD83C\uDF53ɞ˚‧｡⋆\n");
            }
            for(Sandwich sandwich: sandwichList){
                String s = String.format("Bread: %s\nBreadSize: %s Price: %.2f\n" +
                        "Meat: %s\nExtraMeat: %s price: %.2f\n" +
                        "Cheese: %s\nExtraCheese: %s price: %.2f\n" +
                        "Toppings: %s\n" +
                        "Sauce: %s\n" +
                        "Side: %s\n" +
                        "isToasted: %s\n" +
                        "TotalSandwichPrice: %.2f\n━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━\n",sandwich.getBread(),sandwich.getBreadSize(),sandwich.getBreadSizePrice(),sandwich.getMeat(),sandwich.isHasExtraMeat(),sandwich.getExtraMeatPrice(),sandwich.getCheese(),sandwich.isHadExtraCheese(),sandwich.getExtraCheesePrice(),sandwich.getRegularTopping(),sandwich.getSauce(),sandwich.getSide(),sandwich.isToasted(),sandwich.getBasePrice() );
                fileWriter.write(s);
            }
            for(Drink drink: drinkList){
                String s = "Drink Size: "+ drink.getSize()+"\n"+"Price: "+drink.getPrice()+"\n━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━\n";
                fileWriter.write(s);
            }
            for(Chip chip: chipList){
                String s = "Chip price: "+ chip.getChipPrice()+"\n━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━━━━━⊱⋆⊰━━━━\n";
                fileWriter.write(s);
            }
            double total = sandwichList.stream().mapToDouble(Sandwich::getBasePrice).sum()+drinkList.stream().mapToDouble(Drink::getPrice).sum()+chipList.stream().mapToDouble(Chip::getChipPrice).sum();
            String s = String.format("Total Sandwich Price: %.2f\nTotal Drink Price: %.2f" +
                            "\nTotal Chips Price: %.2f\nCheckout Total: %.2f\n",sandwichList.stream().mapToDouble(Sandwich::getBasePrice).sum()
                    ,drinkList.stream().mapToDouble(Drink::getPrice).sum(),chipList.stream().mapToDouble(Chip::getChipPrice).sum(),total);
            fileWriter.write(s);
            fileWriter.close();

        }
        catch(IOException exception){
            System.out.println("File cannot be written");
        }

    }
}