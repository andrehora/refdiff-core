package refdiff.core.rm2.model.refactoring;

import refdiff.core.rm2.model.SDEntity;
import refdiff.core.rm2.model.SDType;
import refdiff.core.api.RefactoringType;

public class SDAddMethod extends SDRefactoring {

    private final SDEntity entityBefore;
    private final SDEntity entityAfter;
    
    public SDAddMethod(SDEntity entityAfter) {
        super(RefactoringType.ADD_METHOD, entityAfter, entityAfter, entityAfter);
        this.entityBefore = entityAfter;
        this.entityAfter = entityAfter;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(' ');
        sb.append(entityBefore.fullName());
        sb.append(" add method ");
        sb.append(entityAfter.fullName());
        return sb.toString();
    }
    
//    public String getEntityNameBefore() {
//		return "METHOD_ADDITION";
//	}
}
