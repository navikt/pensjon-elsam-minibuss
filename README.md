Pensjon Elsam Minibuss er laget for å kunne flytte tjenester for Elektronisk Samhandling fra
enterprise service bus til nais.

Pakkenavnene i dette prosjektet er valgt for å gjøre det enkelt å navigere til riktig modul
i bussprosjektet til NAV.

I mapperfilene så finnes kode som ser ut som følgende. Kommentarene referer til navn på map-fil på bussen
samt operasjonen som bussen gjorte. I dette tilfellet så heter filen på bussen
`FaultElementetFinnesIkkeTOFaultNpPersonIkkeFunnet.map` og den hadde 5 `move` operasjoner. Kommentarene er der
for å enkelt kunne sammenligne koden med hva bussen gjorde.

```
// FaultElementetFinnesIkkeTOFaultNpPersonIkkeFunnet
fun FaultStoElementetFinnesIkke.toFaultNpPersonIkkeFunnet(): FaultNpPersonIkkeFunnet =
    FaultNpPersonIkkeFunnet().also {
        it.errorMessage = errorMessage // move (executionOrder=1)
        it.errorSource = errorSource // move (executionOrder=2)
        it.errorType = errorType // move (executionOrder=3)
        it.dateTimeStamp = dateTimeStamp // move (executionOrder=4)
        it.rootCause = rootCause // move (executionOrder=5)
    }
```

## Kjøre pensjon-elsam-minibuss lokalt mot testmiljø

> [!NOTE]
Dette krever tilgang til Nav sine testmiljø via _naisdevice_ eller at man
> kjører applikasjonen fra _VDI_.

Opprett en `.env-fil` med memmeligheter og konfigurasjon for miljøet ved å
kjøre følgende kommando. Kommandoen skriver ut stien til `.env-filen` som ble
opprettet. Kommandoen henter ned oppsett for `Q2` om du ikke angir et miljø.
Legg til miljønavn som et argument om ønsker et annet miljø enn `Q2`.

```shell
./fetch-secrets.sh
```

I IntelliJ, naviger til klassen `Application`, trykk høyre museknapp på startikonet og
velg `Modify Run Configuration...` fra menyen. Under `Environment variables`
legger du til stien til `.env-filen` som ble opprettet. Om
`Environment variables` ikke vises legger du til dette valget ved å trykke på
`Modify options` og velge `Environment variables` fra menyen som vises.
