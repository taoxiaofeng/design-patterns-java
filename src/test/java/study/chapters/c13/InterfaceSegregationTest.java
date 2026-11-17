package study.chapters.c13;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c13.InterfaceSegregation.*;

class InterfaceSegregationTest {
    @Test void printingNeedsOnlyPrinter() { assertEquals("print:report", printJob(new PrintOnly())); }
    @Test void multifunctionDeviceWorksAsEitherCapability() {
        AllInOne device = new AllInOne(); assertEquals("print:report", printJob(device));
        Scanner scanner = device; assertEquals("scan:data", scanner.scan());
    }
    @Test void callerAcceptsMinimalImplementation() { assertEquals("REPORT", printJob(String::toUpperCase)); }
}
