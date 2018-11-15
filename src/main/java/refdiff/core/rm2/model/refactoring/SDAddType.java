package refdiff.core.rm2.model.refactoring;

import refdiff.core.rm2.model.SDEntity;
import refdiff.core.rm2.model.SDType;
import refdiff.core.api.RefactoringType;

public class SDAddType extends SDRefactoring {

    private final SDEntity entityBefore;
    private final SDEntity entityAfter;
    
    public SDAddType(SDEntity entityAfter) {
        super(RefactoringType.ADD_TYPE, entityAfter, entityAfter, entityAfter);
        this.entityBefore = entityAfter;
        this.entityAfter = entityAfter;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(' ');
        sb.append(entityBefore.fullName());
        sb.append(" add type ");
        sb.append(entityAfter.fullName());
        return sb.toString();
    }
    
//    public String getEntityNameBefore() {
//		return "TYPE_ADDITION";
//	}
}
