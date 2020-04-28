import java.io.IOException;

public interface GUI {
    void clear();
    void refresh() throws IOException;

    void close() throws IOException;
}
