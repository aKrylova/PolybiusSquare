package polybius;

import java.util.Scanner;

public class Polybius {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input patch to files (without filename):");
        String curPatch = in.nextLine();
        System.out.println("Input file name key (without *.txt):");
        Encryption_Polybius encr = new Encryption_Polybius(curPatch + in.nextLine() + ".txt");
        System.out.println("Input file name with message (without *.txt):");
        String file_mes = in.nextLine();
        System.out.println("Input file name encryption (without *.txt):");
        String file_encr = in.nextLine();
        encr.encryption(curPatch + file_mes + ".txt", curPatch + file_encr + ".txt");
        System.out.println("Input file name dencryption (without *.txt):");
        String file_decr = in.nextLine();
        encr.decryption(curPatch + file_encr + ".txt", curPatch + file_decr + ".txt");
    }
    
}
