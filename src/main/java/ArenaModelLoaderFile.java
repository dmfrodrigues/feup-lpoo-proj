import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArenaModelLoaderFile implements ArenaModelLoader {
    private ArenaModel arenaModel;
    public ArenaModelLoaderFile(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        arenaModel = new ArenaModel();
        for(int i = 0; scanner.hasNextLine(); ++i){
            String line = scanner.nextLine();
            System.out.println("line " + i + ": " + line);
        }
    }

    @Override
    public ArenaModel getArenaModel() {
        return arenaModel;
    }
}
