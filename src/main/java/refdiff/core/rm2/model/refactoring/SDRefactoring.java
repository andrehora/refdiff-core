package refdiff.core.rm2.model.refactoring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import refdiff.core.rm2.model.SDEntity;
import refdiff.core.api.Refactoring;
import refdiff.core.api.RefactoringType;

public class SDRefactoring implements Refactoring {

	protected RefactoringType type;
	protected SDEntity mainEntity;
	protected SDEntity entityBefore;
	protected SDEntity entityAfter;
	protected boolean changedCode = false;
	
	public SDRefactoring() {
		
	}

	public SDRefactoring(RefactoringType type, SDEntity mainEntity, SDEntity entityBefore, SDEntity entityAfter) {
		this.type = type;
		this.mainEntity = mainEntity;
		this.entityBefore = entityBefore;
		this.entityAfter = entityAfter;
		this.changedCode = entityBefore.hasSourceCodeChanged(entityAfter);
		//System.out.println(entityBefore.getTypeReferences());
		//System.out.println(entityAfter.getTypeReferences());
	}

	@Override
	public RefactoringType getRefactoringType() {
		return type;
	}

	@Override
	public String getName() {
		return type.getDisplayName();
	}
	
	public SDEntity getEntityBefore() {
		return entityBefore;
	}
	
	public String getEntityNameBefore() {
		return this.getEntityBefore().toString();
	}

	public SDEntity getEntityAfter() {
		return entityAfter;
	}
	
	public String getEntityNameAfter() {
		return this.getEntityAfter().toString();
	}
	
	public boolean getChangedCode() {
		return this.changedCode;
	}
	
	public List<String> getTypeReferencesBefore() {
		return entityBefore.getTypeReferences();
	}
	
	public List<String> getTypeReferencesAfter() {
		return entityAfter.getTypeReferences();
	}
	
	public List<String> getRemovedTypes() {
		List<String> toReturn = new ArrayList<String>(this.getTypeReferencesBefore());
		toReturn.removeAll(this.getTypeReferencesAfter());
		return toReturn;
	}
	
	public List<String> getAddedTypes() {
		List<String> toReturn = new ArrayList<String>(this.getTypeReferencesAfter());
		toReturn.removeAll(this.getTypeReferencesBefore());
		return toReturn;
	}

  @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName());
		sb.append(' ');
		sb.append(this.mainEntity);
//		if (this.details != null && !this.details.isEmpty()) {
//			sb.append(' ');
//			sb.append(this.details);
//		}
		return sb.toString();
	}
}
