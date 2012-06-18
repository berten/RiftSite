package org.deschutter;

import org.deschutter.parser.DamageDoneParser;
import java.io.FileReader;
import org.deschutter.parser.exception.FileNullException;
import org.deschutter.parser.ParsedLine;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import org.deschutter.parser.IParser;
import org.junit.Before;
import org.springframework.stereotype.Component;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

/**
 *
 * @author berten
 */
@Component
public class CombatAnalyzerTest {

    private List<IParser> parsers;
    IParser attackParser;
    CombatAnalyzer analyzer;

    @Before
    public void setUp() {
        attackParser = mock(DamageDoneParser.class);
        parsers = Arrays.asList(attackParser);
        analyzer = new CombatAnalyzer(parsers);
    }
    
    @Test
    public void analyse_callsParser_canHandle() throws Exception {
        analyzer.analyze(new FileReader(System.getProperty("user.dir")+"/src/test/resources/oneLineParse.txt"));
        verify(attackParser).canHandle(any(ParsedLine.class));
    }
        
    @Test
    public void analyse_parserCanHandle_Must_handle() throws Exception {
        when(attackParser.canHandle(any(ParsedLine.class))).thenReturn(Boolean.TRUE);
        analyzer.analyze(new FileReader(System.getProperty("user.dir")+"/src/test/resources/oneLineParse.txt"));
        verify(attackParser).handle(any(ParsedLine.class));
    }
    
    @Test(expected = FileNullException.class)
    public void nullFile_givesError() {
        analyzer.analyze(null);
    }
}