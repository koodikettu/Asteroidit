#Asteroidit
##Testausdokumentti

Koska kyseess� on reaaliajassa toimiva sovellus, jonka eteneminen perustuu suurelta osalta satunnaisuuteen, on testaamisessa omat haasteensa. My�s peliss� luotavat oliot, erityisesti Asteroidi-luokan ilmentym�t ovat jokainen eri muotoisia, niiss� on eri m��r� kulmia jne. T�st� johtuen testauksessa on pit�nyt luoda keinotekoisia yksinkertaistettuja tilanteita, jotka eiv�t t�ysin vastaa pelin monimutkaisia tilanteita, mutta joiden testaaminen ja metodien oikean toiminnan toteaminen on mahdollista.

Sovelluksessa ei ole my�sk��n ole kiellettyj� sy�tteit� eik� sellaisia kiellettyj� tiloja, joiden syntymist� testauksella erityisesti pit�isi testata. L�hinn� t�rke�� on t�rm�ysten tunnistaminen oikein sek� reunanylitysten hoitaminen. Kummassakaan tapauksessa oleellista ei ole rajatapauksissa pikselin tarkkuus, eik� siten ole suurta v�li�, k�ytet��nk� vertailuja tehdess� > vai >= operaattoria.

Ohjelmakoodiin on my�s testausta varten lis�tty gettereit� ja settereit�, joita k�ytet��n ainoastaan testauksessa. Samoin er��t muuttujat palauttavat paluuarvon, vaikka itse ohjelmassa paluuarvoa ei k�ytet� mihink��n.




