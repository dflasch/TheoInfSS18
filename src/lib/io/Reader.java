package lib.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    
    public String readFile(String path) throws IOException {
        String eachLine = "";
        File file = new File(path);
        String readText = "";
        
        if (!file.exists()) return readText;
        
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        
        while((eachLine = fileReader.readLine())!=null) 
            readText += eachLine.trim() + "\n";
        
        fileReader.close();
        
        return readText;
    }
    
}
