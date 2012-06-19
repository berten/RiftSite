package org.deschutter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.deschutter.analyzer.FightAnalyzer;
import org.deschutter.parser.IParser;
import org.deschutter.parser.ParsedLine;
import org.deschutter.parser.exception.FileNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author berten
 */
@Service
public class Dispatcher {

    private List<IParser> parsers;
    private FightAnalyzer analyzer;

    @Autowired
    public Dispatcher(List<IParser> parsers,FightAnalyzer analyzer) {
        this.parsers = parsers;
        this.analyzer = analyzer;
    }

    public void dispatch(FileReader file) {
        List<Fight> fights = new ArrayList<>();
        if (file == null) {
            throw new FileNullException();
        }

        try {

            Fight fight = new Fight();
            String line = null;
            BufferedReader reader = new BufferedReader(file);
            Date fightDate = null;
            while ((line = reader.readLine()) != null) {
                final ParsedLine parsedLine = new ParsedLine(fightDate, line);
                if(fightDate == null) {
                    fightDate = parsedLine.getDate();
                }
                for (IParser parser : parsers) {
                    if (parser.canHandle(parsedLine)) {
                        fight.addAction(parser.handle(parsedLine));
                    }
                }
            }
            
            
            reader.close();
            analyzer.analyzeFight(fight);
        } catch (IOException ex) {
        }
    }
}