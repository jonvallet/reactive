val complexList = List(List(1,2),List(3))


//complexList flatMap(elem => elem) foldRight(0)((a,b,c) => a+b)

complexList map (elem => elem)

val simpleList = List(1,2,3,4,5)

simpleList filter (_ % 2 == 0)
