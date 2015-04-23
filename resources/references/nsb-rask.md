--------------------------------------------------------------------------------
:page-title NSB RASK
:type reference
:logo /logos/nsb.png
:img /references/bengt-lyng.jpg
:name Roar Karlsen
:phone +47 xxx yy zzz
:title Avdelingssjef, NSB Plan og Trafikk
:body

Med dette har vi fått verdens beste løsning...

--------------------------------------------------------------------------------
:type illustrated-column
:title RASK
:body

RASK (RApportSystem for Kjøring) er NSBs nye løsning for rapportering og analyse av all togtrafikk kjørt av NSB.  Hovedhensikten er å tilgjengeliggjøre og sammenstille informasjon rundt faktisk og planlagt togframføring, samt  synliggjøre sammenhenger mellom data hentet fra ulike delsystemer. Applikasjonen bruker dataene til å vise nøkkeltall og detaljinformasjon slik at NSB kan forbedre sine interne rutiner og dermed lette rapporteringsarbeidet. Videre bygger systemet et felles datagrunnlag som kan brukes i fremtidige rapporter, analyser og applikasjoner.

--------------------------------------------------------------------------------
:type reference-meta
:title NSB Personalbillett
:body

En ny internapplikasjon for personalbilletter ble bygget opp av to Kodemakere
i team. Utviklingen foregikk hos NSB over en 8 måneders periode.

:team-size 6
:factoid-1 6 Kodemakere
:factoid-2 1200 timer / 08.2013-03.2014

--------------------------------------------------------------------------------
:type illustrated-column
:body

Personalbillett er en applikasjon som brukes internt av administratorer i NSB.
De ordner rabatterte billetter til 30 000 ansatte i NSB og assosierte selskaper
som Nettbuss, Jernbaneverket, Flytoget, og ROM Eiendom.

--------------------------------------------------------------------------------
:type illustrated-column
:title Godt kjente teknologier
:body

Applikasjonen ble utviklet på nytt fra grunnen av. Backend var Java og [Groovy](/groovy/) kjørende på
[Tomcat](/tomcat/), med [Spring](/spring/), [Hibernate](/hibernate/),
Liquibase og [Oracle](/oracle/).

Frontenden ble laget ved hjelp av
Grails, [JavaScript](/javascript/) og [Twitter Bootstrap](/bootstrap/).

[Groovy](/groovy/), Grails, Java, SQL/Oracle, Linux, JMS/ActiveMQ, HTML5, JSON, JavaScript, CSS, Spock, WebServices, REST, HighCharts

--------------------------------------------------------------------------------
:type grid
:content

/tomcat/                           /photos/tech/tomcat.svg
/git/                              /photos/tech/git.svg
/javascript/                       /photos/tech/js.svg
/spring/                           /photos/tech/spring.png 2x
/java/                             /photos/tech/java.svg
/groovy/                           /photos/tech/groovy.png 2x
/bootstrap/                        /photos/tech/bootstrap.svg
/hibernate/                        /photos/tech/hibernate.svg

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
:title Kodemakere hos NSB
:content

yen

Yen skrev Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute 
irure dolor in reprehenderit ...

sten-morten

Sten Morten hoppet Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute 
irure dolor in reprehenderit ...

kristian

Krsitian pumpet Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute 
irure dolor in reprehenderit ...

stian

Stian jobbet med alle lag av applikasjonen fra databasedesign til brukergrensesnitt. Han hadde en 
sentral rolle i å utvikle kjøremotoren for å innhenting, prosessering og sammenstilling av data
fra en rekke ulike kilder i korrekt rekkefølge. Dette var viktig for å oppnå et godt og robust 
sluttresultat hvor man automatisk kan håndtere og rapportere ulike feilsituasjoner.

stein-tore

Stein Tore pendlet Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute 
irure dolor in reprehenderit ...

stig

Stig orienterte Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor 
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud 
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute 
irure dolor in reprehenderit ...

ronny

Ronny jobbet med applikasjonen Min energi. Det er en applikasjon som viser strømforbruk for turerene en lokfører har kjørt.
Fokus var på å lage en enkel, brukervennlig løsning, gjennom tett samarbeid med designer, produkteier og pilotbrukere.

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
