Pensjon Elsam Minibuss er laget for å kunne flytte tjenester for Elektronisk Samhandling fra
enterprise service bus til nais

Pakkenavnene i dette prosjektet er valgt for å gjøre det enkelt å navigere til riktig modul
i bussprosjektet til NAV

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
