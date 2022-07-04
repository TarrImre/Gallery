import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

////////////
//LÉTREHOZOK KÉT METÓDUST
//-MainIndexek
//-GenerateJPG
////////////

public class MakeHTML {

    public static void MainIndexek(File eleresiut) throws IOException {
        File[] files = eleresiut.listFiles();
        List<File> directories = MyUtils.MappaList(files);
        List<File> images = MyUtils.KepekList(files);
        FileWriter myWriter = new FileWriter(eleresiut.getAbsolutePath() + "\\Index.html");
        /////////////
        //FŐ INDEX
        ////////////
        if (eleresiut.toString().equals(Main.PATH.toString())) {


            myWriter.write(MessageFormat.format("" +
                    "<!DOCTYPE HTML>\n"+
                    "<html>\n"+
                    "<head>\n"+
                    "<title>index.html</title>\n"+
                    "</head>\n"+
                    "<body>\n"+
                    "<h1><a href=\"{0}\\Index.html\">Start Page</a></h1>\n" +
                    "<hr />\n" +
                    "<h1>Directories</h1>\n" +
                    "<ul>\n", Main.PATH.toString()));

            for (File temp : directories) {
                myWriter.write(MessageFormat.format("<li><a href=\"{0}\\Index.html\">{1}</a></li>\n", temp.getAbsolutePath(), temp.getName()));
            }

            myWriter.write("""
                    </ul>
                    
                    <hr />
                    <h1>Images</h1>
                    <ul>
                    """);

            for (File temp : images) {
                myWriter.write(MessageFormat.format("<li><a href=\"{0}\">{1}</a></li>\n", temp.getAbsolutePath().replace(".jpg", ".html"), temp.getName()));

            }

            myWriter.write("""
                    </ul>
                    </body>
                    </html>
                    """);

            myWriter.close();

        }

        /////////////
        //ALMAPPA INDEX
        ////////////
        else {
            myWriter.write(MessageFormat.format("" +
                    "<!DOCTYPE HTML>\n"+
                    "<html>\n"+
                    "<head>\n"+
                    "<title>index.html</title>\n"+
                    "</head>\n"+
                    "<body>\n"+
                    "<h1><a href=\"{0}\\Index.html\">Start Page</a></h1>\n" +
                    "<hr />\n" +
                    "<p><a href=\"{1}\\Index.html\">^^</a></p>\n" +
                    "<h1>Directories</h1>\n" +
                    "<ul>\n", Main.PATH.toString(), eleresiut.getParent()));


            for (File temp : directories) {
                myWriter.write(MessageFormat.format("<li><a href=\"{0}\\Index.html\">{1}</a></li>", temp.getAbsolutePath(), temp.getName()));
            }

            myWriter.write("""
                    </ul>

                    <hr>
                    <h1>Images</h1>
                    <ul>\n""");

            for (File temp : images) {
                myWriter.write(MessageFormat.format("<li><a href=\"{0}\">{1}</a></li>\n", temp.getAbsolutePath().replace(".jpg", ".html"), temp.getName()));

            }


            myWriter.write("""
                    </ul>
                    </body>
                    </html>
                    """);
            myWriter.close();
        }

    }

    /////////////
    //KEPEK INDEX
    ////////////
    public static void JpgIndex(File eleresiut, File name) throws IOException {
        String jpgpath = eleresiut.getParent();
        File jpgfilepath = new File(jpgpath);
        File[] files = jpgfilepath.listFiles();

        List<File> images = MyUtils.KepekList(files);


        FileWriter myWriter = new FileWriter(eleresiut.getAbsolutePath().replace(".jpg", ".html"));

        myWriter.write(MessageFormat.format("" +
                "<!DOCTYPE HTML>\n"+
                "<html>\n"+
                "<head>\n"+
                "<title>{0}</title>\n"+
                "</head>\n"+
                "<body>\n"+
                "<h1><a href=\"{1}\\Index.html\">Start Page</a></h1>\n" +
                "<hr />\n" +
                "<p><a href=\"{2}\\Index.html\">^^</a></p>\n", name, Main.PATH.toString(), eleresiut.getParent()));

        if (MyUtils.CNT == 0) {
            myWriter.write("<p><a href=\"#\">  <<  </a> <strong>" + name + "</strong> <a href=" + images.get(MyUtils.CNT + 1).getAbsolutePath().replace(".jpg", ".html") + ">&gt;&gt;</a></p>\n" +
                    "<p><a href=" + images.get(MyUtils.CNT + 1).getAbsolutePath().replace(".jpg", ".html") + "><img src=" + images.get(MyUtils.CNT) + " width=\"20%\"></a></p>");
        } else if (MyUtils.CNT == images.size() - 1) {
            myWriter.write("<p><a href=" + images.get(MyUtils.CNT - 1).getAbsolutePath().replace(".jpg", ".html") + "> << </a> <strong>" + name + "</strong> <a href=\"#\">  >>  </a></p>\n" +
                    "<p><a href=\"#\"><img src=" + images.get(MyUtils.CNT) + " width=\"20%\"></a></p>");
        } else {
            myWriter.write("<p><a href=" + images.get(MyUtils.CNT - 1).getAbsolutePath().replace(".jpg", ".html") + "> << </a> <strong>" + name + "</strong> <a href=" + images.get(MyUtils.CNT + 1).getAbsolutePath().replace(".jpg", ".html") + ">  >>  </a></p>\n" +
                    "<p><a href=" + images.get(MyUtils.CNT + 1).getAbsolutePath().replace(".jpg", ".html") + "><img src=" + images.get(MyUtils.CNT) + " width=\"20%\"></a></p>");
        }

        myWriter.write("""
                    \n</body>
                    </html>
                    """);
        myWriter.close();
    }
}