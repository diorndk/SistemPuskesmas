/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalbisPuskes.gui;
import java.io.FileWriter; 
import java.io.IOException;
/**
 *
 * @author IT
 */
public class PrintTes {
    
   private FileWriter out;

   public PrintTes() {
       try {
            //out = new FileWriter("LPT1);

           out.write("----------------------------------------");
           out.write(13);
            out.write(10);
           out.write("TES PRINT");

           out.write(13);

            out.write(10);
           out.write("----------------------------------------");
           out.write(13);

            out.write(10);
         
     
                    out.close();
       }catch (IOException e){
       }

   }

   
    
}
