/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsecameradata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 *
 * @author k00223375
 */
public class ParseCameraData {

    /**
     * @param args the command line arguments
     */
    // Declare file reader and writer streams
    FileReader frs = null;
    FileWriter fws = null;

    // Declare streamTokenizer
    StreamTokenizer in = null;

    // Declare a print stream
    PrintWriter out = null;

    public static void main(String[] args) {

        // Declare file reader and writer streams
        FileReader frs = null;
        FileWriter fws = null;

        // Declare streamTokenizer
        StreamTokenizer in = null;

        // Declare a print stream
        PrintWriter out = null;

        try {
            // Create file input and output streams
            frs = new FileReader("stock.dat");
            fws = new FileWriter("newstock.dat");

            // Create a stream tokenizer wrapping file input stream
            in = new StreamTokenizer(frs);
            out = new PrintWriter(fws);

            //set the - character as a "ordinary" char
            // Read past col headings.
            in.nextToken();
            in.nextToken();
            in.nextToken();
            in.nextToken();
            in.nextToken();

            //read first token of first row (Title)
            in.nextToken();

            //print col heads to second file
            out.println("Brand \t\tModel\t\tColour\t\tMegaPixel\tOldPrice\tNewPrice");
            
             while (in.ttype != StreamTokenizer.TT_EOF) 
             {
                //token (Brand)
                if (in.ttype == StreamTokenizer.TT_WORD) {
                    out.printf("%-9s\t",in.sval);
                } else {
                    System.out.println("Bad file structure");
                }
                
                in.nextToken();
                
                //token (Model)
                if (in.ttype == StreamTokenizer.TT_WORD) {
                    out.printf("%-10s\t",in.sval);
                } else {
                    System.out.println("Bad file structure");
                }
               
                in.nextToken();
                
                //token (Colour)
                if (in.ttype == StreamTokenizer.TT_WORD) {
                    out.printf("%-8s\t",in.sval);
                } else {
                    System.out.println("Bad file structure");
                }
                
                in.nextToken();
                
                //token (MegaPixel)
                if (in.ttype == StreamTokenizer.TT_NUMBER) {
                    int mPixel = (int)(in.nval);
                    out.print(mPixel + "\t\t");
                } else {
                    System.out.println("Bad file structure");
                }
                
                in.nextToken();
                
                //token (Price)
                if (in.ttype == StreamTokenizer.TT_NUMBER) {
                    int price=(int)(in.nval);
                    out.print(price + "\t\t");
                    
                    //Calculate new Price and write to new file.
                    out.print(discountApplied(price));
                    
                } else {
                    System.out.println("Bad file structure");
                }
                
                out.println();
                
                in.nextToken();
                        
             }
            

        } catch (FileNotFoundException ex) {
            System.out.println("File not found: in.dat");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (frs != null) {
                    frs.close();
                }
                if (fws != null) {
                    fws.close();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    
    public static int discountApplied(int price)
    {
        int newPrice =0;
        double discount=0;
        if(price>=450)
        {
            discount = (int)(price*0.15);
            newPrice = (int)(price-discount);
        }
        else if(price>=300)
        {
            discount = Math.round(price*0.12);
            newPrice = (int)(price-discount);
        }
        else if(price>=200)
        {
            discount = Math.round(price*0.10);
            newPrice = (int)(price-discount);
        }
        else if(price>=100)
        {
            discount = Math.round(price*0.08);
            newPrice = (int)(price-discount);
        }
        else
        {
            discount = Math.round(price*0.05);
            newPrice = (int)(price-discount);
        }
        return newPrice;
    }
}
