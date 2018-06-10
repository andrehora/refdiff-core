package refdiff.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.eclipse.jgit.lib.Repository;

import refdiff.core.RefDiff;
import refdiff.core.api.GitService;
import refdiff.core.rm2.model.refactoring.SDRefactoring;
import refdiff.core.util.GitServiceImpl;

public class RefDiffRunner {

	public static void detectRefactoringAt(String folder, String data, PrintWriter out) {

		String commit = data.split(",")[0];
		String date = data.split(",")[1];
		String author = data.split(",")[2];

		RefDiff refDiff = new RefDiff();
		GitService gitService = new GitServiceImpl();

		try (Repository repository = gitService.openRepository(folder)) {
			List<SDRefactoring> refactorings = refDiff.detectAtCommit(repository, commit);
			for (SDRefactoring refactoring : refactorings) {
				out.print(refactoring.getName());
				out.print(";");
				out.print(refactoring.getChangedCode());
				out.print(";");
				out.print(refactoring.getEntityNameBefore());
				out.print(";");
				out.print(refactoring.getEntityNameAfter());
				out.print(";");
				out.print(commit);
				out.print(";");
				out.print(date);
				out.print(";");
				out.println(author);
			}
		} catch (Exception ex) {
			// System.out.println("Error:" + ex.getMessage());
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		String projectName = "RefDiff";
		
		String baseFolder = "projects";
		String projectFolder = baseFolder + "/" + projectName;
		Path path = Paths.get(baseFolder + "/commits_" + projectName);

		try (PrintWriter out = new PrintWriter(baseFolder + "/model_" + projectName)) {
			try (Stream<String> lines = Files.lines(path)) {
				lines.forEach(line -> detectRefactoringAt(projectFolder, line, out));
			} catch (Exception ex) {}
		}
	}
}