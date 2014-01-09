# kodemaker.no

Våre nye nettsider kommer til verden.

## Teste lokalt

Skaff [leiningen](https://github.com/technomancy/leiningen#leiningen)
om du ikke har den. Deretter:

```shell
lein ring server
```

Voila!

## Provisjonering

Vi bruker [Ansible](www.ansibleworks.com) for å sette opp serveren.
Hvis du sitter på OSX er det så enkelt som `brew install ansible`. Da
får du `1.4.3` eller nyere, noe du også trenger.

### Sette opp din egen server lokalt

Du kan bruke [Vagrant](http://www.vagrantup.com/) og
[VirtualBox](https://www.virtualbox.org/) for å sette opp en virtuell
blank CentOS server lokalt.

```sh
cd provisioning/devbox
vagrant plugin install vagrant-vbguest
vagrant up
echo "\n192.168.33.44 local.kodemaker.no" | sudo tee -a /etc/hosts
```

Legg til din public key i `provisioning/keys`, og føy den til listen
under `Setup authorized_keys for users who may act as deploy user`
tasken i `provisioning/bootstrap.yml`.

Gå så tilbake til `provisioning/` og:

```sh
ansible-playbook -i hosts.ini bootstrap.yml --private-key=~/.vagrant.d/insecure_private_key -u vagrant --sudo
```

Nå kan du `ssh deploy@local.kodemaker.no` og se deg omkring. Sudo
passord er `kodemaker`.

Så kan du fortsette ned til
[Sette opp kodemaker.no](#neste-sette-opp-kodemakerno).

### Provisjonere en server

Så, du har en fresk og fersk CentOS server som vil bli kodemaker.no.
Legg den til i `provisioning/hosts.ini` under `[new-servers]`. Du kan
ta bort `192.168.33.44`, den brukes bare for lokal testing.

Forhåpentligvis har du testet lokalt, og dermed ligger allerede din
public key i `provisioning/keys`.

Så gjenstår det bare å gå til `provisioning/` katalogen og inkantere:

```sh
ansible-playbook -i hosts.ini bootstrap.yml --user root --ask-pass
```

#### Øhh, det gikk ikke helt bra

Nei, du mangler kanskje `sshpass` lokalt hos deg? Det er bare en yum
eller apt unna. Eller hvis du er på OSX:

```sh
brew install https://raw.github.com/eugeneoden/homebrew/eca9de1/Library/Formula/sshpass.rb
```

### Neste: Sette opp kodemaker.no

Når du bootstrapper, så vil root-login og passord-login bli disablet.
Så når vi nå skal sette opp kodemaker no, så må du fleske til med en
annen inkantasjon:

```sh
ansible-playbook -i hosts.ini setup-kodemaker.yml --user deploy --sudo --ask-sudo-pass
```

Nå er det altså ikke SSH-passordet som brukes lenger - den bruker din
private key - men du må oppgi sudo-passordet. Dersom du ikke har gjort
noen endringer, så er det fortsatt `kodemaker`. Men hvis dette er en
offentlig server, så lønner det seg nok å gjøre den endringen. Logg
inn som `deploy` og `passwd`.

#### Bygg og deploy siten

Du må ha en ganske ny versjon av
[leiningen](https://github.com/technomancy/leiningen#leiningen)
installert. Gå til rota av prosjektet, og:

```sh
lein build-site
cd provisioning
ansible-playbook -i hosts.ini deploy-kodemaker.yml --user deploy --sudo --ask-sudo-pass
```

Og nå kan du besøke http://local.kodemaker.no i nettleseren din og
meske deg i de nye sidene våre.
