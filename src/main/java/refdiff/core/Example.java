package refdiff.core;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.eclipse.jgit.lib.Repository;

import refdiff.core.RefDiff;
import refdiff.core.api.GitService;
import refdiff.core.rm2.model.refactoring.SDRefactoring;
import refdiff.core.util.GitServiceImpl;


public class Example {
	
	
	public static void detectRefactoringAt(String project, String folder, String commit) {
		RefDiff refDiff = new RefDiff();
		GitService gitService = new GitServiceImpl();
		try (Repository repository = gitService.cloneIfNotExists(folder, project)) {
			List<SDRefactoring> refactorings = refDiff.detectAtCommit(repository, commit);
		    for (SDRefactoring refactoring : refactorings) {
		    	System.out.print(refactoring.getName());
		    	System.out.print(",");
		        System.out.print(refactoring.getEntityBefore());
		        System.out.print(",");
		        System.out.print(refactoring.getEntityAfter());
		        System.out.print(",");
		        System.out.println(commit);
		    }
		}
		catch (Exception ex) {}
	}
	
	public static void detectRefactorings(String project, String folder, List<String> commits) throws Exception {
		for (String commit : commits) {
			detectRefactoringAt(project, folder, commit);
		}
	}
	
	public static void main(String[] args) {
		
		//PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		//System.setOut(out);
		
		String project = "https://github.com/square/leakcanary.git";
		String folder = "projects/leakcanary";
		Path path = Paths.get("commits.json");
		
		try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(commit -> detectRefactoringAt(project, folder, commit));
        } catch (Exception ex) {}
	}
}