package org.example;
import java.util.*;
import java.text.ParseException;
import java.io.*;


//transaction handler file class calling the pathname
public class TransactionHandler {

    //array list
    private List<Transaction> transactionList;
    private File transactionFile = new File("src/main/resources/transactions.csv");


    //transaction handler to read and get the transaction list
    public TransactionHandler() {
        transactionList = readTransactions();
        Collections.reverse(transactionList);
        Collections.sort(transactionList);

    }

    //now we are using the boolean method calling the payment
    //we are doing the filter in the transaction within the if statements
    public List<Transaction> filter(boolean payment) {
        List<Transaction> l = new ArrayList<>();
        if (payment) {
            for (Transaction t : transactionList) {
                if (t.getAmount() < 0) l.add(t);
            }
            return l;
        }
        for (Transaction t : transactionList) {
            if (t.getAmount() > 0) l.add(t);
        }
        return l;
    }

    //here we are calling the calender in the transaction to read the file and filter it out.
    public List<Transaction> filter(Calendar calendario) {
        List<Transaction> lista = readTransactions();

        for (Transaction transaccion : transactionList) {
            if (!(transaccion.getDate().after(calendario.getTime())) || transaccion.getDate().equals(calendario.getTime())) return lista;
            lista.add(transaccion);
        }
        return lista;
    }

    //calling the transaction array list to successfully read the list
    //now we filter
    public List<Transaction> filter(String vendor) {
        List<Transaction> lista = new ArrayList<>();
        for (Transaction transaccion : transactionList) {
            if (transaccion.getVendor().equals(vendor)) lista.add(transaccion);
        }
        return lista;
    }

    //using the filter in the transaction list we do an if statement
    //if the statement gets filtered, filter into the right category
    //call the array list
    //for loop if
    //transaccion date is before end date
    public List<Transaction> filter(TransactionFilter filtro) {

        //call array list
        List<Transaction> lista = new ArrayList<>();
        for (Transaction transaccion : transactionList) {
            if (
                    transaccion.getDate().before(filtro.getEndDate())
                            && transaccion.getDate().after(filtro.getStartDate())
                            && transaccion.getDescription().contains(filtro.getDescription())
                            && transaccion.getVendor().contains(filtro.getVendor())
                            && transaccion.getAmount() >= filtro.getAmountMin()
                            && transaccion.getAmount() <= filtro.getAmountMax()
            ) lista.add(transaccion);
        }
        return lista;
    }

    //now we are implementing the calender into the dates so they can organize within the filter
    public List<Transaction> monthToDate() {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.DAY_OF_MONTH, 1);
        return filter(calendario);
    }
    public List<Transaction> previousMonth() {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.MONTH, calendario.get(Calendar.MONTH) - 1);
        return filter(calendario);
    }
    public List<Transaction> yearToDate() {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.DAY_OF_MONTH, 1);
        calendario.set(Calendar.MONTH, 1);
        return filter(calendario);
    }
    public List<Transaction> previousYear() {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, calendario.get(Calendar.YEAR) - 1);
        return filter(calendario);
    }

    //writing the transaction into the buffer writer, now we are using the buffered writer
    public void writeTransaction(Transaction transaction) {
        transactionList.add(transaction);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(transactionFile, true))) {
            bw.append(transaction.toCal());
        } catch (IOException e) {
            System.out.println("⋘ ᴛʀʏ ʟᴀᴛᴇʀ... could not write file... ⋙");
        }
    }

    //here we are doing the buffered reader. Calling in the buffered reader to read what is in the transaction line by line
    private List<Transaction> readTransactions() {
        List<Transaction> reading = new ArrayList<>();
        if (transactionFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
                String line = br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    Date date = Transaction.dateForm.parse(parts[0] + "|" + parts[2]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    reading.add(new Transaction(date, description, vendor, amount));
                }
            } catch (IOException | ParseException e) {
                System.out.println("⋘ ᴛʀʏ ʟᴀᴛᴇʀ... ⋙ could not read file... ");
            }
        } else try {
            transactionFile.createNewFile();
        } catch (IOException e) {
            System.out.println("C⋘ ᴛʀʏ ʟᴀᴛᴇʀ... ⋙ could not create ... ");
        }
        return reading;
    }

    //just return the transaction list (get)
    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}