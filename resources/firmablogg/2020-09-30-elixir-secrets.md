:title Elixir/Phoenix: Logg config i oppstart
:author alf-kristian 
:published 2020-03-11

:tech [:elixir :erlang :funksjonell-programmering]

:blurb

Logger du config i oppstart av appen din, nei? Det gjøres heller ikke i Phoenix, men det er ikke godt nok. Her er en oppskrift på hvordan gjøre dette. 


:body

Jeg er sjokkert over hvorfor ikke alle dumper sin config til logger i oppstart. Og jeg hadde forventa at et så gjennomtenkt lib som Phoenix i det minste gjorde dette enkelt for sine brukere. Men den gang nei!

Så da gjorde jeg det eneste fornutige, og skrev kode for å gjøre det selv.

## Men hvorfor?

Ok, dette et selvsagt ikke den viktigste egenskapen en applikasjon har, men det er så utrolig nyttig. Lurer du på hva en config er, bare sjekk i loggen. Endrer du en config, men er litt usikker på om den er med, bare sjekk i loggen. Å ha dette på plass er verdt en liten innats i et prosjekt altså. Bruker du koden her, tar noen minutter å teste og sjekke inn.


## Men dette er jo enkelt, kan jeg ikke bare logge configen da?

Klart du kan. Men, har du secrets i configen din, så er det kanskje noe du ikke ønsker at havner i loggaggregatoren. Slettes ikke sikkert alle som har tilgang til den, skal vite secrets, eller tredjeparten du bruker som aggregator skal ha det. Så utordringen er jo da å identifisere og maskere secret. Jeg har tidligere skrevet om hvordan man kan gjøre dette sikkert i Clojure.

Men altså nå er vi på en helt annen plattform, og her funker ting litt annerledes. Så her kommer det til å bli en del kode!

## Hvordan en Elixir/Erlang app er bygget opp.

Først litt bakgrunn. BEAM (Erlang plattformen) kan man tenke på som et OS i seg selv. Systemet ditt (appen), består av din app og en rekke andre apper. Dette er gjerne libber, men som kan definere sine egne prosesser. Om du lurer på hva en prosess er, så skrev litt om det i [Elixir: Prosesser og meldinger](/blogg/2020-03-message-passing-beam/)

Så hver app har sin config, og når vi starter opp, så vil det jo være hendig å logge config for hele system, alle appene. Ikke bare din egen.

Elixir har også sitt eget config bibliotek. Og config defineres i elixir script filer (.exs), som betyr at de f.eks. kan inneholde funksjoner. Dette er veldig fleksibelt og utvidbart. Og det er også veldig separat greie.

## Nok prat, "show me some code"

I motsetning til i Clojure trenger vi ingen magisk metaprogrammering for å gjøre dette. Men vi må ha litt kjennskap til Elixir sine datastrukturer. Da config er fleksibelt, kan det inneholde alle typer verdier, og for å kunne maskere config må vi forstå hva dette kan være.

#### Atomiske verdier
 
Jepp, ganske likt som i andre språk. F.eks.
```elixir
# Tall
 42

# Tekst
"Hello world"

# Atoms, som ligner mye på symboler i andre språk 
 :start_with_colon

```

#### Lister
 
Funksjonelle singly linked lists. Ganske så like som i andre funksjonelle språk egentlig. Fin kompakt syntaks:
 
 ```elixir
 [1, 2, 3]
```

#### Tupler

Veldig likt tupler i andre språk. Kan ikke utvides på samme måte som en liste, men har veldig raskt indeks oppslag 

 ```elixir
{1, 2, 3} # Tuppel med lengde 3
```



#### Maps
 
funksjonelle maps, key-value par, der key er uniqu...mmm...men det visste du jo :D
 
 ```elixir
# String som keys
%{"foo" => "bar"}

# Atoms som keys
%{:foo => "bar"}

# Og så en liten twist til glede for alle Clojure entusiaster. Samme som utrykket over
%{foo: "bar"}

# Og så har vi noe som heter keyword lists da, tenkte bare nevne det. En slags blanding mellom list og maps
[foo: "bar", baz: "quux"]

```

#### MapsSet
I praksis det samme som et funksjonelt set i andre språk, men da det bygger på Map, så har de navngitt det litt spesielt imo. Ikke så elegant syntaks som de andre.

```elixir
MapSet.new([1, 2, 2]) # MapSet<[1, 2]>
```

Kanskje ikke så vanglig å bruke i config, men vi må ta høyde for det, og greit å vite om uansett.


#### Struct
 
Map'ish verdi, men med faste felter
```elixir
defmodule Foo do
  defstruct [:a, :b, :a]
end

%Foo{a: 1, b: 2, c: 3}
```

Forsåvidt heller ikke så vanlig å bruke i config. Men igjen, noe vi må ta høyde for.

## Secrets i Elixir config

Det er ikke noen konvensjoner om hva som er en secret, har jeg bare valgt å velge alt som høres secret ut. Laget meg et lite regex som sjekker:

```elixir
~r/key|secret|salt|encryption|password|connection_string|creds/

```

Elixir har egen spesiell syntaks for regexer. Egentlig ikke noe spesiel for regexer, dette er såkalt sigils (lenke).

Config er altså et slags globalt map, som vi rekursivt må traverse og maskere secrets. Og det er selvsagt immutable, så ingen fare for at du endrer noen verdier underveis, vi lager et nytt config objekt.

Så la oss begynne med det enkleste. Når vi treffer en secret så må den maskeres. Dette er jo da basert på navnet til verdien.

```elixir
  @secret_matcher ~r/key|secret|salt|encryption|password|connection_string|creds/
  
  def secrets_masker(k, v) do
    s =
      if is_atom(k) do
        # Veldig vanlig at nøkler i maps er atoms, men vi skal jo regex matche på strings
        Atom.to_string(k)
      else
        k
      end

    if is_binary(v) &&
         is_binary(s) &&
         Regex.match?(@secret_matcher, s) do
      {k, ((v || "") |> String.first()) <> "*****"}
    else
      {k, v}
    end
  end

```

`is_binary` tenker du sikkert? Og dette er litt rart, men kommer nok av Erlang. Måten å sjekke om noe er tekst på i Elixir er å sjekke om det er en binary.

Uansett en relativt enkelt funksjon som gjøre sånn som dette:

```elixir 

secrets_masker("test", "foo") # {"test", "foo"}
secrets_masker("testkey", "foo") # {"testkey", "f*****"}
```

Når den treffer en secret, så maskerer den altså alt bortsett fra første karakter. Dette kan være nyttig for å identifisere at man har riktig secret.

Så funksjonen som gjør jobben:

 ```elixir
  def deep_transform(data) do
    cond do
      is_tuple(data) && tuple_size(data) == 2 ->
        {k, v} = data

        if is_binary(v) do
          {k, secrets_masker(k, v)}
        else
          {k, deep_transform(v)}
        end

      is_map(data) && is_struct(data) ->
        data
        |> Enum.reduce(%{}, fn {k, v}, acc ->
          if is_binary(v) do
            Map.put(acc, k, secrets_masker(k, v))
          else
            Map.put(acc, k, deep_transform(v))
          end
        end)

      is_list(data) ->
        Enum.map(data, fn value -> deep_transform(value) end)

      true ->
        data
    end
  end
```

Denne går altså rekursivt igjennom hele config mappet, finner datastrukturer og konverterer disse også.

Og til slutt funksjonen som gjør selve jobben


 ```elixir
   def log_all_applicaion_config() do
     Logger.info(
       "CoruscantWeb has started successfully on pid #{:os.getpid()} git-sha is #{
         Application.get_env(:coruscant_web, :git_sha)
       }"
     )
 
     try do
       conf =
         ([{:my_app, "Top level"}] ++ Application.started_applications())
         |> Enum.map(&elem(&1, 0))
         |> Enum.map(fn app -> {app, Application.get_all_env(app)} end)
         |> Enum.reject(fn {_app, conf} -> Enum.empty?(conf) end)
         |> Enum.map(fn {app, v} -> {app, deep_transform(v)} end)
         |> inspect(pretty: true)
 
       Logger.info("My configuration is #{conf}")
     rescue
       ex -> Logger.warn("Problem logging out config #{inspect(ex)}")
     end
   end
```






## Trivielt?

Elixir er ikke et trivielt språk, det har en del syntaks, datastrukturer og for mange er jo dette en litt annerledes plattform. Og jeg skrev denne koden etter å ha vært i et Elixir prosjekt i ca 4 måneder. Da hadde jeg fått nok ;) Jeg trodde det skulle være mer tricky enn det var, men det var vel verdt jobben. Det er veldig deilig å ha dette både lokalt, test og i prod.

Ikke helt trivielt å skrive, men det tok meg heller ikke speiselt lang tid, da jeg hadde blitt vant til plattformen. Og nå bare funker det.

Om du er i et Elixir/Phoenix prosjekt så har du mitt fulle samtykke til å bruke koden her, eller teknikkene. Jeg håper i det minste det har gitt deg litt å tenke på, om du ikke allere gjør dette.

Om du ikke er i et Elixir/Phoenix prosjekt, gjør du dette? Evt hvorfor ikke?

## Bonus loging

Andre ting å logge er jo selvsagt git sha'en. Lurer du på hvilken versjon som kjører...sjekk i loggen. Og sist, men ikke minst, logg appens pid! Hvorfor det, spør du? Vel, lokalt er det jo kjekt om du har lyst til å drepe prosessen, kanskje teste SIGTERM og SIGKILL, hvordan oppfører det seg. I prod, og i hvert fall om du kjører i Docker, så MÅ prosessen din kjøre på pid 1. Hvis ikke funker ikke SIGTERM og du har ikke graceful shutdown. Er du sikker på at appen din kjører på PID 1?
