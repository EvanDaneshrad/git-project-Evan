import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BranchTester {

    public static void main(String[] args) throws IOException {

        Git.init(false, false, false, false);

        // First commit on main
        FileWriter writer = new FileWriter("a.txt");
        writer.write("one");
        writer.close();

        Git.updateIndexFile("a.txt");

        String firstCommit = Git.commit("me", "First commit");
        System.out.println("First commit: " + firstCommit);

        // Create feature branch
        Git.createBranch("feature");
        System.out.println("Created feature branch");

        // Switch to feature
        Git.checkout("feature");
        System.out.println("Checked out feature");

        // Change a.txt and commit
        writer = new FileWriter("a.txt");
        writer.write("two");
        writer.close();

        Git.updateIndexFile("a.txt");

        String secondCommit = Git.commit("me", "Feature commit");
        System.out.println("Second commit: " + secondCommit);

        // Switch back to main
        Git.checkout("main");
        System.out.println("Checked out main");

        // Switch back to feature
        Git.checkout("feature");
        System.out.println("Checked out feature again");

        // Test duplicate branch
        try {
            Git.createBranch("feature");
        } catch (IOException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // Test nonexistent branch
        try {
            Git.checkout("nonexistent");
        } catch (IOException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // Test detached HEAD
        Git.checkout(firstCommit);
        System.out.println("Detached HEAD: " + Git.getCurrentCommit());
    }
}