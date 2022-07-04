import java.io.File;
import java.io.IOException;

class isDirectoryException extends Exception {}

public class Main {
    public static File PATH;

    public static void main(String[] args) throws IOException {
        PATH = new File(args[0]);
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Adjon meg parancssori argomentumot!");
        }
        try
        {

            //Meghívom magát a lényeget
            File path = PATH;
            if (path.exists() && path.isDirectory()){
                MyUtils.MappaBejar(PATH.toString());
            }
            else throw new isDirectoryException();
        }
        catch (isDirectoryException e)
        {
            System.out.println("Nem megfelelő elérési út!");
        }



    }
}