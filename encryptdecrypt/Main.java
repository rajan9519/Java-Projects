package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String option = "enc";
        String str = "";
        String output = "";
        String alg = "shift";
        int key = 0;

        for (int i = 0; i < args.length - 1; i += 2) {
            switch (args[i]) {
                case "-mode":
                    option = args[i + 1];
                    break;
                case "-data":
                    str = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-in":
                    if ("".equals(str)) {
                        File file = new File(args[i + 1]);
                        try (Scanner scanner = new Scanner(file);) {
                            str = scanner.nextLine();
                        } catch (FileNotFoundException e) {
                            System.out.println("Error: File not found");
                        }
                    }
                    break;
                case "-out":
                    output = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
            }
        }
        Algorithm algorithm = AlgoFactory.selectAlgo(alg);
        StringBuilder ans = new StringBuilder("");
        switch (option) {
            case "enc":
                ans = algorithm.encrypt(str, key);
                break;
            case "dec":
                ans = algorithm.decrypt(str, key);
                break;
        }
        if ("".equals(output)) {
            System.out.println(ans);
        } else {
            try (FileWriter writer = new FileWriter(output)) {
                writer.write(ans.toString());
            } catch (IOException e) {
                System.out.printf("An exception occurs %s", e.getMessage());
            }
        }
    }
}
