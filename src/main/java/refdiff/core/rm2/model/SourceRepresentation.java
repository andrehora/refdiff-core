package refdiff.core.rm2.model;

import java.util.List;

public interface SourceRepresentation {

    SourceRepresentation combine(SourceRepresentation other);

    SourceRepresentation minus(SourceRepresentation other);

    double similarity(SourceRepresentation other);

    double partialSimilarity(SourceRepresentation other);
    
    public List<String> getTypeReferences();
    
    public int getNumberOfTokens();
    
    public int getNumberOfStatements();

}
