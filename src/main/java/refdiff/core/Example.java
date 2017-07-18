package refdiff.core;
import java.io.FileNotFoundException;
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
	
	public static void detectRefactoringAt(String project, String folder, String line) {
		
		String commit = line.split(",")[0];
		String date = line.split(",")[1];
		
		RefDiff refDiff = new RefDiff();
		GitService gitService = new GitServiceImpl();
		try (Repository repository = gitService.cloneIfNotExists(folder, project)) {
			List<SDRefactoring> refactorings = refDiff.detectAtCommit(repository, commit);
		    for (SDRefactoring refactoring : refactorings) {
		    	if ((refactoring.getName().equals("SameMethod") || refactoring.getName().equals("RenameMethod") || refactoring.getName().equals("MoveMethod")) && 
		    			refactoring.getRemovedTypes().size() == 1 && refactoring.getAddedTypes().size() == 1) {
			    	System.out.print(refactoring.getName());
//			    	System.out.print(";");
//			    	System.out.print(refactoring.getChangedCode());
//			    	System.out.print(";");
//			        System.out.print(refactoring.getEntityNameBefore());
//			        System.out.print(";");
//			        System.out.print(refactoring.getEntityNameAfter());
			        System.out.print(";");
			        System.out.print(commit);
//			        System.out.print(";");
//			        System.out.println(date);
			        System.out.print(";");
			        System.out.print(refactoring.getRemovedTypes().get(0));
			        System.out.print(";");
			        System.out.println(refactoring.getAddedTypes().get(0));
		    	}
		    }
		}
		catch (Exception ex) {
			//System.out.println("Error:" + ex.getMessage());
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String systemName = "che";
		String project = "";
		String systemFolder = "projects/"+systemName;
		
		PrintStream out = new PrintStream(new FileOutputStream("apimining_"+systemName));
		System.setOut(out);
		
		Path path = Paths.get("commits_"+systemName);
		//Path path = Paths.get("commits");
		
		try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> detectRefactoringAt(project, systemFolder, line));
        } catch (Exception ex) {
        	//System.out.println("Error:" + ex.getMessage());
        }
	}
}