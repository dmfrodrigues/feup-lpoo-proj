import java.io.IOException;

public interface ArenaView {
    enum COMMAND {UP, DOWN, LEFT, RIGHT, ATTACK, EXIT}
    void draw(ArenaModel arena) throws IOException;
    COMMAND pollCommand() throws IOException;
    void close() throws IOException;
}
