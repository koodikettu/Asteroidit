#Asteroidit
##Testausdokumentti

Koska kyseessä on reaaliajassa toimiva sovellus, jonka eteneminen perustuu suurelta osalta satunnaisuuteen, on testaamisessa omat haasteensa. Myös pelissä luotavat oliot, erityisesti Asteroidi-luokan ilmentymät ovat jokainen eri muotoisia, niissä on eri määrä kulmia jne. Tästä johtuen testauksessa on pitänyt luoda keinotekoisia yksinkertaistettuja tilanteita, jotka eivät täysin vastaa pelin monimutkaisia tilanteita, mutta joiden testaaminen ja metodien oikean toiminnan toteaminen on mahdollista.

Sovelluksessa ei ole myöskään ole kiellettyjä syötteitä eikä sellaisia kiellettyjä tiloja, joiden syntymistä testauksella erityisesti pitäisi testata. Lähinnä tärkeää on törmäysten tunnistaminen oikein sekä reunanylitysten hoitaminen. Kummassakaan tapauksessa oleellista ei ole rajatapauksissa pikselin tarkkuus, eikä siten ole suurta väliä, käytetäänkö vertailuja tehdessä > vai >= operaattoria.

Ohjelmakoodiin on myös testausta varten lisätty gettereitä ja settereitä, joita käytetään ainoastaan testauksessa. Samoin eräät muuttujat palauttavat paluuarvon, vaikka itse ohjelmassa paluuarvoa ei käytetä mihinkään.




