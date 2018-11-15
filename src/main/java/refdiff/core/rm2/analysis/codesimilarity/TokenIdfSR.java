package refdiff.core.rm2.analysis.codesimilarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import refdiff.core.rm2.model.Multiset;
import refdiff.core.rm2.model.SourceRepresentation;

class TokenIdfSR implements SourceRepresentation {

    private final Multiset<String> tokens;
    private final TokenIdfSRBuilder builder;
    private int numberOfStatements = 0;

    public TokenIdfSR(Multiset<String> tokens, TokenIdfSRBuilder builder, int numberOfStatements) {
        this.tokens = tokens;
        this.builder = builder;
        this.numberOfStatements = numberOfStatements;
    }
    
    public TokenIdfSR(Multiset<String> tokens, TokenIdfSRBuilder builder) {
        this.tokens = tokens;
        this.builder = builder;
    }

    @Override
    public TokenIdfSR minus(SourceRepresentation other) {
        return new TokenIdfSR(tokens.minus(((TokenIdfSR) other).tokens), builder);
    }

    @Override
    public String toString() {
        return tokens.toString();
    }

    public Set<String> getTokenSet() {
        return tokens.asSet();
    }
    
    public List<String> getTypeReferences() {
    	ArrayList<String> typeReferences = new ArrayList<String>();
    	for (String token : this.getTokenSet()) {
    		if (token.length() >= 2 && Character.isUpperCase(token.charAt(0)) && Character.isLowerCase(token.charAt(1))) {
    			typeReferences.add(token);
    		}
		}
    	return typeReferences;
    }

    @Override
    public TokenIdfSR combine(SourceRepresentation sr) {
        Multiset<String> multisetUnion = tokens;
        TokenIdfSR tokenIdfSR = (TokenIdfSR) sr;
        multisetUnion = multisetUnion.plus(tokenIdfSR.tokens);
        return new TokenIdfSR(multisetUnion, builder);
    }

    @Override
    public double similarity(SourceRepresentation other) {
        return jaccardSimilarity(((TokenIdfSR) other).tokens, false);
    }

    @Override
    public double partialSimilarity(SourceRepresentation other) {
        return jaccardSimilarity(((TokenIdfSR) other).tokens, true);
    }
    
    public double jaccardSimilarity(Multiset<String> tokens2, boolean partial) {
        if (tokens.isEmpty() || tokens2.isEmpty()) {
            return 0.0;
        }
        Set<String> keys = new HashSet<String>();
        keys.addAll(tokens.asSet());
        keys.addAll(tokens2.asSet());
        double idfu = 0.0;
        double idfd = 0.0;
        for (String key : keys) {
            int c1 = tokens.getMultiplicity(key);
            int c2 = tokens2.getMultiplicity(key);
            idfu += Math.min(c1, c2) * builder.idf(key);
            idfd += Math.max(c1, c2) * builder.idf(key);
        }
        if (partial) {
            double idfp = 0.0;
            for (String key : tokens.asSet()) {
                int c1 = tokens.getMultiplicity(key);
                idfp += c1 * builder.idf(key);
            }
            return idfu / idfp;
        }
        return idfu / idfd;
    }
    
    public int getNumberOfTokens() {
    	return tokens.size();
    }

	public int getNumberOfStatements() {
		return numberOfStatements;
	}
}
