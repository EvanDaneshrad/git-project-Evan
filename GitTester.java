import java.io.IOException;

public class GitTester {
    public static void main(String[] args) throws IOException {
        GitWrapper.init();
        GitWrapper.add("myProgram/hello.txt");
        GitWrapper.add("myProgram/inner/world.txt");
        String hash = GitWrapper.commit("badem", "og");
        System.out.println("Commit: " + hash);
    }
}
