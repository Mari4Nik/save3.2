import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(10, 50, 3, 15.2);
        GameProgress game2 = new GameProgress(33, 150, 10, 100.5);
        GameProgress game3 = new GameProgress(60, 178, 15, 357.2);

        saveGame("/D:/Games/savegames/save1.dat", game1);
        saveGame("/D:/Games/savegames/save2.dat", game2);
        saveGame("/D:/Games/savegames/save3.dat", game3);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("D:// Games//savegames//save1.dat");
        arrayList.add("D:// Games//savegames//save2.dat");
        arrayList.add("D:// Games//savegames//save3.dat");

        zipFiles("D://Games//savegames//zip.zip", arrayList);

        File save1Dat = new File("D:// Games//savegames//save1.dat ");
        File save2Dat = new File("D:// Games//savegames//save2.dat");
        File save3Dat = new File("D:// Games//savegames//save3.dat");
        if (save1Dat.delete()) System.out.println("Файл \"save1.dat\" удален");
        if (save2Dat.delete()) System.out.println("Файл \"save2.dat\" удален");
        if (save3Dat.delete()) System.out.println("Файл \"save3.dat\" удален");
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
