package refdiff.core;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.eclipse.jgit.lib.Repository;

import refdiff.core.RefDiff;
import refdiff.core.api.GitService;
import refdiff.core.rm2.model.refactoring.SDRefactoring;
import refdiff.core.util.GitServiceImpl;


public class Example {
	
	public static void main(String[] args) throws Exception {
		
		//PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		//System.setOut(out);
		
		RefDiff refDiff = new RefDiff();
		GitService gitService = new GitServiceImpl();
		try (Repository repository = gitService.cloneIfNotExists("projects/RefDiff", "https://github.com/aserg-ufmg/RefDiff.git")) {
			List<SDRefactoring> refactorings = refDiff.detectAtCommit(repository, "c56990c79da89f5da2667c738ee2426be5ec5c44");
		    for (SDRefactoring refactoring : refactorings) {
		    	System.out.print(refactoring.getName());
		    	System.out.print(",");
		        System.out.print(refactoring.getEntityBefore());
		        System.out.print(",");
		        System.out.println(refactoring.getEntityAfter());
		    }
		}
	}
}