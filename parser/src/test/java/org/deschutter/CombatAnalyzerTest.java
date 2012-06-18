package org.deschutter;

import org.deschutter.parser.ParsedLine;
import java.util.Map;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import org.deschutter.parser.AttackParser;
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
        attackParser = mock(AttackParser.class);
        parsers = Arrays.asList(attackParser);
        analyzer = new CombatAnalyzer(parsers);
    }
    
    @Test
    public void analyse_callsParser_canHandle() {
        analyzer.analyze();
        verify(attackParser).canHandle(any(ParsedLine.class));
    }
        
    @Test
    public void analyse_parserCanHandle_Must_handle() {
        when(attackParser.canHandle(any(ParsedLine.class))).thenReturn(Boolean.TRUE);
        analyzer.analyze();
        verify(attackParser).handle(any(ParsedLine.class));
    }
}