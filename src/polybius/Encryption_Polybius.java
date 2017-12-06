package polybius;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import pair.Pair;

public class Encryption_Polybius {
    private final ArrayList<ArrayList<Character>> matrix;
    private int size;
    
    public Encryption_Polybius(String file_key_name){
        matrix = new ArrayList<ArrayList<Character>>();
        this.generate_key(file_key_name);
        this.read_key(file_key_name);
    }
    
    public void generate_key(String file_name){
        StringBuilder str_b = new StringBuilder(0);
        StringBuilder str_b2 = new StringBuilder();
        try(BufferedReader file_key = new BufferedReader (new FileReader(file_name))){
            String phr = file_key.readLine(); // фраза
            str_b.append(phr);
            str_b.append(phr.toUpperCase());
            for(int i = 32; i < 127; i++){
                if(str_b.indexOf(Character.toString((char)i)) == -1){
                    str_b.append(Character.toString((char)i));
                }
            }
            size = (int) Math.sqrt(str_b.length());
            str_b2.append(Integer.toString(size) + System.getProperty("line.separator"));
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file_name, false))){
            str_b2.append(str_b.substring(0, size * size));
            String filestr = str_b2.toString();
            bw.write(filestr);
         }
         catch(IOException ex){
             System.out.println(ex.getMessage());
        
         }
    }

    public void matrix_fill(String c){
        for(int i = 0; i < Integer.parseInt(c); i++){
            ArrayList<Character> tmp_list = new ArrayList<Character>(Integer.parseInt(c));
            for(int j = 0; j < Integer.parseInt(c); j++){
                tmp_list.add(Character.MIN_VALUE);
            }
            matrix.add(tmp_list);
        }
    }
    
    public void read_key(String name){
        try(BufferedReader file_key = new BufferedReader (new FileReader(name))){
            String chrs;
            matrix_fill(file_key.readLine());
            while((chrs = file_key.readLine()) != null ){
                int row = 0;
                int col = 0;
                char [] syms = chrs.toCharArray();
                for(int i = 0; i < syms.length; i++){
                    matrix.get(row).set(col, syms[i]);
                    ++col;
                    if(col == (matrix.size())){
                        ++row;
                        col = 0;
                    }
                }
                
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void encryption(String file_message_name, String file_encr_mes_name){
        StringBuilder str_b = new StringBuilder();
        StringBuilder str_b2 = new StringBuilder();
        String st = "";
        Map<Integer, Pair> indexs = new LinkedHashMap();
        try(BufferedReader file_mes = new BufferedReader (new FileReader(file_message_name))){
            int ch;
            int key_count = 0;
            Integer index_j;
            while((ch = file_mes.read()) != -1){
                for(Integer i = 0; i < size; i++){
                    if((index_j = matrix.get(i).indexOf((char)ch)) != -1){
                       indexs.put(key_count, new Pair(i, index_j));
                       ++key_count;
                       break;
                    }
                }
            }
            Iterator<Entry<Integer, Pair>> it1 = indexs.entrySet().iterator();
            while (it1.hasNext()){
                Entry<Integer, Pair> entry = it1.next();
                str_b.append(indexs.get(entry.getKey()).get_x());
                str_b2.append(indexs.get(entry.getKey()).get_y());   
            }
            str_b.append(str_b2);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file_encr_mes_name))){
             st = str_b.toString();
             bw.write(st);
         }
         catch(IOException ex){
             System.out.println(ex.getMessage());
        
         }
    }
    
        public void decryption(String file_encr_mes_name, String file_decr_mes_name){
        String filestr = "";
        StringBuilder str_b = new StringBuilder();
        StringBuilder str_b2 = new StringBuilder();
        try(BufferedReader file = new BufferedReader (new FileReader(file_encr_mes_name))){
            int k = 0;
            while((filestr = file.readLine()) != null){
                str_b.append(filestr);
            }
            int indxs; // x | y
            indxs = str_b.length()/2;
            char [] syms = str_b.toString().toCharArray();
            for(int i = 0; i < indxs; i++){
                str_b2.append(matrix.get(Character.getNumericValue(syms[i])).get(Character.getNumericValue(syms[indxs+i])));
                if(indxs >= syms.length){
                    break;
                }
            }
         }
         catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file_decr_mes_name))){
             filestr = str_b2.toString();
             bw.write(filestr);
         }
         catch(IOException ex){
             System.out.println(ex.getMessage());
        
         }
    }
    
}
