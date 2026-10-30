package oo2.redictado.core;

/**
 * Formats a report as plain text, one bad smell per line.
 */
public class PlainTextFormatter implements ReportFormatter {
    @Override
    public String format(AromaReport report) {
        if (!report.stinks()) {
            return "The diagram smells good.\n";
        }
        StringBuilder text = new StringBuilder();
        report.getAromas().forEach(aroma -> text.append(
                "[%s] line %d: %s%n".formatted(aroma.name(), aroma.line(), aroma.message())));
        return text.toString();
    }
}
