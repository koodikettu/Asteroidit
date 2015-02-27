#Ohjelman rakenteen kuvaus#

Ohjelman rakenteen keski�ss� on Asteroidipeli-luokka. Se sis�lt�� oliomuuttujina sovelluslogiikan sis�lt�v�t luokat sek� Asteroidi-, Alus- ja Ammus-luokan oliot.

K�ytt�liittym�n toteuttavat luokat (K�ytt�liittym�, Piirtoalusta, Yl�paneeli ja N�pp�imist�nkuuntelija) ovat omassa asteroidit.gui -pakkauksessaan, ja ne on muutenkin erotettu mahdollisimman hyvin sovelluslogiikasta. Yl�paneeli-luokka vastaa ohjelmaikkunan yl�osassa olevasta alueesta, jolle p�ivittyy pelin aikana tietoa mm. pistetilanteesta. Piirtoalusta-luokka vastaa itse pelialueen piirt�misest�. N�pp�imist�nkuuntelija kuuntelee pelaajan n�pp�imist�n v�lityksell� antaa sy�tett� ja v�litt�� sen sovelluslogiikalle.

asteroidit.domain -pakkaus sis�lt�� Asteroidi-, Alus- ja Ammus-luokat sek� Liikkuva-rajapinnan. Liikkuva-rajapinta mahdollistaa samojen metodien k�yt�n sek� asteroidien ett� aluksen reunanylityksen hallinnassa. 

Sovelluslogiikka on koottu asteroidit.logiikka -pakkaukseen, ja se sis�lt�� luokat Kirjanpitaja, Tormaystenkasittelija ja TilanteenLaskija. TilanteenLaskija -luokka vastaa uuden pelitilanteen laskemisesta seuraavaa p�ivityskertaa varten. Tormaystenkasittelija-luokka tutkii Asteroidien ja Ammusten v�liset t�rm�ykset ja t�rm�yksen havaitessaan niihin liittyv�t yll�pitotoimenpiteet kuten t�rm�nneiden asteroidien ja ammusten poistamisen pelist� sek� tiedon v�litt�misen Kirjanpitaja-luokalle. T�rm�ystenk�sittelij� tutkii my�s asteroidien ja aluksen v�liset t�rm�ykset, ja sellaisen havaitessaan v�litt�� Kirjanpitaja-luokalle tiedon, ett� peli on p��ttynyt. Kirjanpit�j� vastaa pistekirjanpidosta sek� muusta pelin tilaan liittyv�n tiedon k�sittelyst�.