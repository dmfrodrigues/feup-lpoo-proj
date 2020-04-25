import java.io.InputStream;
import java.util.Scanner;

public class ArenaModelLoaderFile implements ArenaModelLoader {
    private ArenaModel arenaModel;
    public ArenaModelLoaderFile(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        String firstLine = scanner.nextLine();
        int W = Integer.parseInt(firstLine.split(" ")[0]);
        int H = Integer.parseInt(firstLine.split(" ")[1]);
        arenaModel = new ArenaModel(W, H);
        Position pos;
        for(Integer y = 0; y < H; ++y){
            String line = scanner.nextLine();
            for(Integer x = 0; x < W; ++x){
                switch(line.charAt(x)){
                    case 'W': arenaModel.addStaticElement(new Wall(new Position(x, y))); break;
                    case ' ': break;
                    default: throw new IllegalArgumentException("Unknown character");
                }
            }
        }
    }

    @Override
    public ArenaModel getArenaModel() {
        return arenaModel;
    }
}
