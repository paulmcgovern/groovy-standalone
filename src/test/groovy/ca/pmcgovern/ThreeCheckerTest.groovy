package ca.pmcgovern


import org.testng.Assert
import org.testng.annotations.Test
import org.testng.annotations.DataProvider
import org.testng.annotations.Parameters
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeTest

import java.io.File
import org.codehaus.groovy.runtime.powerassert.PowerAssertionError

class ThreeCheckerTest {
 
    /** Path to file containing test parameters. */
    def dataFile
    
    /** Class to be exercised */
    def checker = new ThreeChecker()
    
    /***
     1. ERROR in /home/mcgovern/NetBeansProjects/groovy_mvn/src/test/groovy/ca/pmcgovern/NubTest.groovy (at line 26)
	@Parameters( {"dataFile"} )
	             ^^^^^^^^^^^^^
Groovy:Annotation list attributes must use Groovy notation [el1, el2] in @org.testng.annotations.Parameters
**/

    /** NB: grovy annotation on param arr. Not {,,}, but [,,] */
    @BeforeClass(  groups = [ "positiveTests", "negativeTests" ])
    @Parameters( [ "dataFile" ] )
    public void setup( String dataFile ) {
   
       this.dataFile = dataFile
    }

    
    
    @Test(dataProvider="getParameters", groups = [ "positiveTests" ])
    public void  expectGoodData( num, product ) {
        
        Assert.assertTrue( this.checker.isProductOfThree( num, product ))         
    }
  
        
    @Test(groups = [ "negativeTests" ], expectedExceptions=[ PowerAssertionError ] )
    public void  checkNull() {
   
        this.checker.isProductOfThree( null, null ) 
    }
    
    
    @Test(dataProvider="getParameters", groups = [ "negativeTests" ])
    public void  expectBadData( num, product ) {
    
        assert !this.checker.isProductOfThree( num, product )    
    }
    
    
    @DataProvider( name="getParameters" )
    public Object[][] getParameters() {

        assert this.dataFile, "Parameter file not set."
       
        println "Reading parameters from file ${this.dataFile}"

        def dataIn = new File( this.dataFile )
       
        assert dataIn.exists(), "Parameter file ${this.dataFile} does not exist."
              
        def out = [][] 
       
        dataIn.eachLine {
            out.add( it.split( ',' ))            
        }
       
        return out;                 
    }
       
  
}

