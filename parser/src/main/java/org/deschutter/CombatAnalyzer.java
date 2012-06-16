package org.deschutter;

import java.util.Date;
import java.util.List;
import org.deschutter.parser.IParser;
import org.deschutter.parser.ParsedLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author berten
 */
@Service
public class CombatAnalyzer {

    public List<IParser> parsers;

    @Autowired
    public CombatAnalyzer(List<IParser> parsers) {
        this.parsers = parsers;
    }

    void analyze() {
        for(IParser parser : parsers) {
            final ParsedLine parsedLine = new ParsedLine(new Date(),"");
            
            if(parser.canHandle(parsedLine)) {
                parser.handle(parsedLine);
            }
        }
    }
}