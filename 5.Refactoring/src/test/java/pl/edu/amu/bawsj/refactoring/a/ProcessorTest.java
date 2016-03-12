package pl.edu.amu.bawsj.refactoring.a;

import org.junit.Assert;
import org.junit.Test;

public class ProcessorTest {

    @Test
    public void processorShouldReturnResultWithNullTextWhenClosed() {
        Processor processor = new Processor();
        processor.setClose(true);
        Result result = processor.process("someText");
        Assert.assertEquals("null", result.getText());
    }

    @Test
    public void processorShouldReturnResultWithProcessedTextWhenOpen() {
        Processor processor = new Processor();
        processor.setClose(false);
        Result result = processor.process("someText");
        Assert.assertEquals("someText", result.getText());
    }
}