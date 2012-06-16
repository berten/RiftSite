package org.deschutter.parser;

import java.util.Map;
import org.deschutter.parser.actions.RiftAction;

/**
 *
 * @author berten
 */
public interface IParser {
    
    public Boolean canHandle(ParsedLine parsedLine);
    
    public RiftAction handle(ParsedLine parsedLine);
    
}
