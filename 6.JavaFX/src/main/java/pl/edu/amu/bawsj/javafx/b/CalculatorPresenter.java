package pl.edu.amu.bawsj.javafx.b;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculatorPresenter
{
    private static final Logger LOG = LogManager.getLogger();

    private CalculatorView view;

    public CalculatorPresenter( CalculatorView calculatorView )
    {
        this.view = calculatorView;
    }

    public void numClicked( int finalI )
    {
        LOG.info( finalI + " clicked" );
    }

    public void additionClicked()
    {
        LOG.info( "Addition clicked" );
    }

    public void multiplicationClicked()
    {
        LOG.info( "Multiplication clicked" );
    }

    public void subtractionClicked()
    {
        LOG.info( "Subtraction clicked" );
    }

    public void divisionClicked()
    {
        LOG.info( "Division clicked" );
    }

    public void resultClicked()
    {
        LOG.info( "Result clicked" );
        view.showResult( "The result should be displayed here" );
    }
}
