import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MyUtils {

    //Ahányszor meghívom a metódust, annyiszor növelem
    public static int CNT = 0;

    public static void MappaBejar(String filepath) throws IOException {
        File root = new File(filepath);
        MakeHTML.MainIndexek(root);
        File[] list = root.listFiles();
        for ( File f : list ) {

            //Ha könyvtár index file
            if (f.isDirectory())
            {
                //Minden egyes mappánál nullázom
                CNT = 0;
                MakeHTML.MainIndexek(f);
                MappaBejar(f.getAbsolutePath());
                //System.out.println("Mappa:" + f.getAbsoluteFile());

            }

            //Ha kép, kepekindex file
            else
            {
                //System.out.println("File:" + f.getAbsoluteFile());
                if(f.toString().toUpperCase().contains(".JPG") || f.toString().toUpperCase().contains(".JPEG"))
                {
                    File name = new File(f.getName());
                    MakeHTML.JpgIndex(f,name);
                    CNT++;
                }
                //System.out.println("File:" + f.getAbsoluteFile());

            }
        }

    }

    public static List<File> KepekList(File[] files)
    {
        List<File> results = new ArrayList<>();
        for (File temp : files)
        {
            if (temp.isFile() && temp.toString().toUpperCase().contains(".JPG") || temp.toString().toUpperCase().contains(".JPEG"))
            {
                results.add(temp);
            }
        }
        return results;
    }

    public static List<File> MappaList(File[] files)
    {
        List<File> results = new ArrayList<>();
        for (File temp : files)
        {
            if (temp.isDirectory())
            {
                results.add(temp);
            }
        }

        return results;
    }



}