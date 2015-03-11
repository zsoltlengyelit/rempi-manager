package org.landasource.rempi.manager.core.view.wiidgets;

import java.util.Collections;
import java.util.List;

import com.landasource.wiidget.Extension;
import com.landasource.wiidget.Tag;
import com.landasource.wiidget.Wiidget;
import com.landasource.wiidget.antlr.WiidgetParser.StatementDeclarationContext;
import com.landasource.wiidget.parser.ParserException;
import com.landasource.wiidget.parser.StatementProcessor;

public class Code extends Wiidget implements Extension {

    @Override
    public void processStatements(final List<StatementDeclarationContext> statements, final StatementProcessor statementProcessor) throws ParserException {

        final StringBuilder builder = new StringBuilder();
        for (final StatementDeclarationContext statementDeclarationContext : statements) {
            builder.append(statementDeclarationContext.getText());
        }

        Collections.reverse(statements);

        statementProcessor.processStatements(statements);

        write(Tag.tag("pre", builder.toString()));
    }

}
