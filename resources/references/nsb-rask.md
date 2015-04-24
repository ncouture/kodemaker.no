--------------------------------------------------------------------------------
:page-title NSB RASK
:type reference
:logo /logos/nsb.png
:img /references/bengt-lyng.jpg
:name Roar Karlsen
:phone +47 xxx yy zzz
:title Avdelingssjef, NSB Plan og Trafikk
:body

Ved flere anledninger har presentasjon av RASK ført til trampeklapp og jubel,
dette er nyttig for NSB

--------------------------------------------------------------------------------
:type illustrated-column
:title RASK
:body

RASK (RApportSystem for Kjøring) er NSBs nye løsning for rapportering og analyse av all togtrafikk kjørt av NSB.
Hovedhensikten er å tilgjengeliggjøre og sammenstille informasjon rundt faktisk og planlagt togframføring,
samt  synliggjøre sammenhenger mellom data hentet fra ulike delsystemer.
Applikasjonen bruker dataene til å vise nøkkeltall og detaljinformasjon slik at NSB kan forbedre sine interne rutiner og dermed lette rapporteringsarbeidet.
Videre bygger systemet et felles datagrunnlag som kan brukes i fremtidige rapporter, analyser og applikasjoner.

--------------------------------------------------------------------------------
:type reference-meta
:title NSB RASK
:body

Et system for sammenkobling av trafikk- og energidata for NSB. Data brukes til rapportering
og analyse av det som daglig skjer ute i togtrafikken

:team-size 6
:factoid-1 5 Kodemakere
:factoid-2 6500+ timer / 01.2012-

--------------------------------------------------------------------------------
:type illustrated-column
:body

RASK er et datavarehus som sammenstiller data fra mange forskjellige kilder, systemet
har foreløpig to applikasjoner som eksponerer data til brukerne. De to applikasjonene er
Trafikk og MinEnergi.

Trafikk er et rapporteringsverktøy som er laget for å rapportere data om blant annet
togtrafikk, materiellforbruk, avvik og energiforbruk.
Alle ansatte i NSB har tilgang til applikasjonen, og den er i bruk i rapporteringsformål fra og med 01.01.2015

MinEnergi er en applikasjon for lokførerne i NSB. Her kan brukerne se sine egne togframføringer og få oversikt over energiforbruk på turene.
Turene sammenlignes med et gjennomsnittstall slik at man kan få ett innblikk i forbruket og sammenligne med tidligere turer på samme strekning.

--------------------------------------------------------------------------------
:type illustrated-column
:title Godt kjente teknologier
:body

Applikasjonen ble utviklet på nytt fra grunnen av. Backend var [Grails](http://www.grails.org/) kjørende på
[Tomcat](/tomcat/), med Liquibase og Oracle DB.

Frontenden ble laget ved hjelp av
[Grails](http://www.grails.org/), [JavaScript](/javascript/), [Highcharts](http://www.highcharts.com/) og [Twitter Bootstrap](/bootstrap/).

[Groovy](/groovy/), [Java](/java/), SQL/Oracle, Linux, JMS/ActiveMQ, HTML5, JSON, CSS, [Spock](/spock), WebServices, REST

--------------------------------------------------------------------------------
:type grid
:content

/tomcat/                           /photos/tech/tomcat.svg
/javascript/                       /photos/tech/js.svg
/java/                             /photos/tech/java.svg
/groovy/                           /photos/tech/groovy.png 2x
/bootstrap/                        /photos/tech/bootstrap.svg
http://www.grails.org/             /photos/tech/grails.png

--------------------------------------------------------------------------------
:type illustrated-column
:body

Kodemaker har utviklet datavarehuset og de nye applikasjonene gjennom tett samarbeid med NSB, helt fra idéfasen til sluttprodukt.

--------------------------------------------------------------------------------
:type participants
:title Kodemakere hos NSB
:content

kristian

Kristian pumpet Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
irure dolor in reprehenderit ...

ronny

Ronny jobbet med applikasjonen Min energi. Det er en applikasjon som viser strømforbruk for turerene en lokfører har kjørt.
Fokus var på å lage en enkel, brukervennlig løsning, gjennom tett samarbeid med designer, produkteier og pilotbrukere.

stein-tore

Stein Tore har jobbet i prosjektet siden august 2013, han har jobbet med alle deler av systemet,
fra databasedesign til brukergrensesnitt. Han hadde en sentral rolle i innlesing og behandling av
automatiske passasjertellinger og i å lage grunnlaget for applikasjonen Trafikk.

sten-morten

Sten Morten jobbet med blant annet de første integrasjonene, energidata, kjøremotoren og innsamling av
 automatiske passasjertellinger

stian

Stian jobbet med alle lag av applikasjonen fra databasedesign til brukergrensesnitt. Han hadde en 
sentral rolle i å utvikle kjøremotoren for innhenting, prosessering og sammenstilling av data
fra en rekke ulike kilder i korrekt rekkefølge. Dette var viktig for å oppnå et godt og robust 
sluttresultat hvor man automatisk kan håndtere og rapportere ulike feilsituasjoner.

stig

Stig orienterte Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
irure dolor in reprehenderit ...

yen

Yen skrev Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
irure dolor in reprehenderit ...


--------------------------------------------------------------------------------
:type illustrated-column
:title Smidig og pragmatisk prosess
:body

Vi jobbet i sprinter. Ting ble
avklart med oppdragsgiver og brukere før hver sprint. Vi brukte Jira til
 oppgaveoversikt og Confluence til dokumentasjon.

Nye versjoner ble rullet ut etter hver sprint, og oftere ved behov.
--------------------------------------------------------------------------------
