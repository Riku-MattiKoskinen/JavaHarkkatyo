# JavaHarkkatyo - Lutemon-peli
Tekijä: Riku-Matti Koskinen 0560556


Työn kuvaus:

Työssä luotiin peli android-käyttöjärjestelmälle, missä käyttäjä pääsee luomaan Lutemoneja. Lutemonit ovat viidestä eri tyypistä valittavissa olevia olioita, joiden ominisuuksiin kuuluvat nimi, tyyppi, hyökkäys, puolustus, elämäpisteet ja kokemus.
Mahdolliset lutemontyypit ovat tässä Earth, Moon, Mars, Sun ja Mercury ja niitä havainnollistetaan planeettojen kuvilla.
Jokaisella eri lutemontyypillä on erilainen aloitusmäärä hyökkäys- ja puolustuspisteitä, mutta yhtä paljon elämäpisteitä ja taistelussa oletusarvoiset lutemonit päätyvät tasapeliin eli ovat aluksi yhdenvertaisia. Näin yhtä tyyppiä ei suosita.
Lutemonien treenaaminen tapahtuu kuntosalilla, missä 10 sekunnin odotuksella käyttäjä voi lisätä Lutemonin hyökkäys- ja puolustuskykyä sen kokemuspisteiden verran. Jos kuitenkin kokemuspisteitä on oletusarvon 0 verran, treenaaminen
kasvattaa taitoja 1 pisteen verran. Tähän ratkaisuun päädyin, jotta peli olisi mielekäs. Jos lutemonia ei voisi kehittää ollenkaan ennen ensimmäistä kokemuspistettä ja peli päättyy oletusarvoilla tasapeliin, ei Lutemojena edes voisi kehittää.
Taistelut Lutemonien välillä käydään taisteluareenalla. Areenalla käyttäjä valitsee luomistaan lutemoneista kaksi, jotka taistelevat keskenään. Taistellessa, molemmat lutemonit saavat hyökkäysvuoron joka kierroksella, jotta ensimmäinen ei saisi etulyöntiasemaa. Hykkäyksen voima on hyökkääjän hyökkäyspisteet miinus puolustajan puolustuspisteet.
Voittajaksi selviytyy se, jonka elämäpisteet kestävät toista pidempään nollaa suurempina. Ottelun jälkeen häviäjä poistetaan pelistä ja voittaja kartuttaa itselleen yhden kokemuspisteen ja sen elämäpisteet palautetaan oletusarvoonsa. Tasapelitilanteessa kumpaakaan ei poisteta, molempien elämäpisteet palautetaan ja kokemuspisteitä ei kerry. Lutemoneja voi luoda lisää haluamansa verran missä vaiheessa peliä tahansa.


Toteutus ja ohjelman rakenne:

Ohjelma toteutettiin yhdellä kahdella aktiviteetilla ja neljällä fragmentilla. MainActivity toimii ainoastaan pelin aloitussivuna, josta pelaaja siirtyy pelin käynnistämällä GameActivity-aktiviteetiin. GameActivity sisältää neljä fragmenttia FragmentHome, FragmentGym, FragmentList ja FragmentFight, joiden välillä käyttäjä voi liikkua välilehtien tavoin. Fragmentit sisältävät suurimman osan pelin toiminnallisuudesta. Työssä implementoidut ominaisuudet ovat siis perusominaisuuksien lisäksi RecyclerView, kuva lutemoneilla ja fragmentit, ja lutemonien treenaamisessa hyödynnetyn countdownin.


Luokkakaavio (kaaviosta jätetty pois aktiviteetit, adapterit ja fragmentit, sillä ymmärsin näin ohjeista):


![harkkatyo_luokkakaavio drawio(1)](https://user-images.githubusercontent.com/72088238/235747349-46216cb2-e09f-498a-a8b4-49ca9aa78760.png)
