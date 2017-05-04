package refdiff.core.rm2.model.refactoring;

import refdiff.core.rm2.model.SDEntity;
import refdiff.core.rm2.model.SDType;
import refdiff.core.api.RefactoringType;

public class SDChangeEntity extends SDRefactoring {

    private final SDEntity entityBefore;
    private final SDEntity entityAfter;
    
    public SDChangeEntity(SDEntity entityBefore, SDEntity entityAfter) {
        super(RefactoringType.CHANGE_CODE, entityBefore, entityBefore, entityAfter);
        this.entityBefore = entityBefore;
        this.entityAfter = entityAfter;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(' ');
        sb.append(entityBefore.fullName());
        sb.append(" change code ");
        sb.append(entityAfter.fullName());
        return sb.toString();
    }
}
