package ca.pmcgovern

class ThreeChecker {
	
    def  isProductOfThree( num, product ) {

        assert num
        assert product
              
        return num.toInteger() * 3 == product .toInteger()       
    }
}

