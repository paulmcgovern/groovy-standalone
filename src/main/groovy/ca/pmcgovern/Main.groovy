package ca.pmcgovern

assert args.length == 2, "Needs a factor and a product"

def num = args[ 0 ]
def product = args[ 1 ]

def checker = new ThreeChecker()

println "${num} * 3 = ${product} : " + checker.isProductOfThree( num, product )