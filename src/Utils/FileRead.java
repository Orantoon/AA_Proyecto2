package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileRead {
    // private String path = "/Users/david/Desktop/Tareas David/TEC/Semestre 3/Análisis de Algoritmos/Proyectos/Proyecto 2/AA_Proyecto2/"; //Full Path Dave
    private final String path = "";

    public FileRead(){}

    public File getFile (String fileName){   // fileName is just the name
        return new File(path + fileName + ".txt");
    }

    public String readFile (String fileName) throws FileNotFoundException {
        File file = getFile(fileName);
        Scanner scanner = new Scanner(file);
        String res = "";

        while (scanner.hasNextLine()){
            res = res.concat(scanner.nextLine() + "\n");
        }

        return res;
    }

    public void overwriteFile (String fileName, String text) throws IOException {
        File file = getFile(fileName);

        FileWriter fileWriter = new FileWriter(path + fileName + ".txt");
        fileWriter.write(text + "\n");
        fileWriter.close();
    }

    public String readLine (String fileName, int lineNum) throws FileNotFoundException {
        File file = getFile(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()){
            if (lineNum == 0){
                return scanner.nextLine();
            }
            scanner.nextLine();
            lineNum -= 1;
        }

        System.out.println("ERROR. Line not found");
        return null;
    }

    public void writeLine (String fileName, String text) throws IOException {
        //File file = getFile(fileName);
        //text = text.concat(readFile(fileName) + text);

        FileWriter fileWriter = new FileWriter(path + fileName + ".txt", true);
        fileWriter.write(text + "\n");
        fileWriter.close();
    }

    public int lineCount (String fileName) throws FileNotFoundException {
        File file = getFile(fileName);
        Scanner scanner = new Scanner(file);
        int res = 0;

        while (scanner.hasNextLine()){
            scanner.nextLine();
            res += 1;
        }
        return res;
    }
}
