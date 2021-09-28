
Please dont make me do an other one like this for the love of god
--„a specifikáció lényegi részeit teljesítetted
--van egy alapszintű OO szervezés a kódban
--nagyon szép az állapotátmenetek kezelése és validációja, ami bármikor tovább bővíthető és testreszabható
specifikáció sértések / félreértések:
--ha a QUERY parancshoz az évszám mellé megadásra kerül egy versenyszám, akkor nem az adott verseny végeredményét, hanem az azzal bezárólag megrendezett versenyek ponteredményét kell visszaadni (Erre melesleg rákérdeztem, azt mondták jó lesz, hát ezekszerint mégsem)
--a PRESENT pontozásból kimaradt a leggyorsabb kör figyelembe vétele (oops my bad)
--FASTEST parancsot csak a megfelelő RESULT után lehet kiadni, miközben ilyet nem állított a specifikáció (Mondjuk a specifikáció is olyan volt mint amilyen)
--JavaFX library használata (nem a core Java része, az előre közölt Java runtime-nak, az AdoptOpenJDK-nak sem része) (oops my bad)
--teljesítményprobléma (pár másodpercbe beletelik, mire egy nagyobb adatbázison végre tudja hajtani a lekérdezéseket)
az alapját valószínűleg az adja, hogy nincs adatszervezés a lekérdezések mentén – a versenyeket évenként érdemes lett volna előre csoportosítani (Mondjuk nem is volt teljesítmény cél specifikálva szóval kissé unfair kritika)
komolyabb egyéb problémák:
--az állapotok neveit mindenhol új sztringként kell megadni – helyette enum, vagy egyszer definiált sztring konstans könnyítene a kódoláson (Kb 5 másodperc módosítani)
apró egyéb problémák:
--az input átállításához újra kell fordítani a programot (kódba beégetett útvonal) (fair de szintén 5 sec módosítani)
--nincs érdemleges packagelési logika (ráadásul a default com.company lett gyökérként használva) (Azt az 5 filet érdemes packegelgetni)
--szabadszájú kommentek és kiírások” (Szerintem ez inkább pozitív mint negatív, főleg ha már "HÁZI" nak van hívva...)
