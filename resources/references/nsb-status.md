--------------------------------------------------------------------------------
:page-title NSB STATUS
:type reference
:logo /logos/nsb.png
:img /references/sven-westgaard.jpg
:name Sven Westgård
:phone +47 480 81 798
:title Trafikkinformasjonssjef
:body

Med dette har vi fått verdens beste løsning...

--------------------------------------------------------------------------------
:type illustrated-column
:title Publisering av trafikkavvik
:body

Status er det nye publiseringsverktøyet til trafikkinformatørene i NSB. Hovedhensikten med applikasjonen er å formidle informasjon om oppståtte trafikkavvik til alle berørte parter på en mest mulig hensiktsmessig måte.
NSB sin spesialgruppe med ansvar for publisering, **trafikkinformatørene**, er hovedbrukerne av applikasjonen.
--------------------------------------------------------------------------------
:type reference-meta
:title NSB Status
:body

Status er et sentralt prosjekt for NSB, og stadig flere bruksområder blir unnfanget når løsningen tas i bruk.  Utviklet av 2 dedikerte Kodemakere. 

:team-size 2
:factoid-1 2 Kodemakere
:factoid-2 6500 timer / 08.2013-03.2014

--------------------------------------------------------------------------------
:type illustrated-column
:body


Status fungerer som et enkelt grensesnitt som skal publisere til alle de ulike NSB spesialsystemene for distribusjon av informasjon. Det skal informeres både intern, eksternt og til de reisende. Trafikkinformatørene skal ikke forholde seg til de spesifikke spesialsystemene sine grensesnitt eller metoder (lengre).  

Status vektlegger trafikkinformatørenes behov for raskt få totaloversikt over eksisterende publisering, for å kunne oppdatere etterhvert som avviket utvikler seg.
Status omfatter stasjon, infrastruktur, tog og tiltak. Systemets administrasjonsmodul bestemmer distribusjon til kanalene basert på malverk med gitte tekster på norsk og engelsk, ikon, prioritet og alvorlighetsgrad.

--------------------------------------------------------------------------------
:type illustrated-column
:title Teknologivalg
:body

Applikasjonen ble utviklet på nytt fra grunnen av. Backend var Java kjørende på
Jetty og [Tomcat](/tomcat/), med [Spring](/spring/), [Hibernate](/hibernate/),
Liquibase og [MS SQL Server](/mssql/). 

Frontenden ble laget ved hjelp av
[Wicket](/wicket/), [JavaScript](/javascript/) og [Twitter Bootstrap](/bootstrap/).

[Groovy](/groovy/), Grails, Java, SQL/Oracle, Linux, JMS/ActiveMQ, HTML5, JSON, JavaScript, CSS, Spock, WebServices, REST, HighCharts

--------------------------------------------------------------------------------
:type grid
:content

/tomcat/                           /photos/tech/tomcat.svg
/git/                              /photos/tech/git.svg
/maven/                            /photos/tech/maven.svg 2x
/wicket/                           /photos/tech/wicket.svg
/javascript/                       /photos/tech/js.svg
/spring/                           /photos/tech/spring.png 2x
/java/                             /photos/tech/java.svg
/bootstrap/                        /photos/tech/bootstrap.svg
/hibernate/                        /photos/tech/hibernate.svg
/mssql/                            /photos/tech/mssql.png

--------------------------------------------------------------------------------
:type illustrated-column
:body

I tillegg til å gjenskape den gamle funksjonaliteten i en ny og mer
brukervennlig drakt, så utviklet vi en del ny funksjonalitet.

Den gamle applikasjonen var en stand-alone applikasjon, mens den nye er bedre
integrert med et databarehus som fungerer som datakilde for personaldata, med
MSAD/LDAP for pålogging, og ikke minst med det sentrale LISA billettsystemet
som er NSBs sentrale billettsystem.

--------------------------------------------------------------------------------
:type participants
:title Kodemakere i NSB Status
:content

finn

Finn skrev Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute 
irure dolor in reprehenderit ...

august

August hoppet Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute 
irure dolor in reprehenderit ...



--------------------------------------------------------------------------------
:type illustrated-column
:title Minimalistisk og pragmatisk prosess
:body

Vi jobbet i en kontinuerlig flyt - ingen faste møter eller seremonier. Ting ble
avklart med oppdragsgiver og brukere ved behov. Vi brukte en virtuell lappetavle
i Trello. Funksjonell spesifikasjon og arkitektur dokumenterte vi i Confluence.

Allerede etter et par dager var første skjelett av applikasjonen klar for
visning til kunden. Etterhvert la vi ut en ny versjon av applikasjonen på
testserver hver uke.

--------------------------------------------------------------------------------
