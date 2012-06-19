package org.deschutter.analyzer;

import org.deschutter.parser.actions.RiftAction;

/**
 *
 * @author berten
 */
public interface IAnalyzer {
    
    public void analyze(AnalyzedFight fight, RiftAction action);
    
}
