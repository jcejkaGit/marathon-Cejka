# Middleware Engineering "Distributed Architecture and Document Formats"

## Aufgabenstellung
Die detaillierte [Aufgabenstellung](TASK.md) beschreibt die notwendigen Schritte zur Realisierung.

## Implementierung

### Start
Repo als ZIP in VS Code ziehen und Dependency in build.gradle adden
- **implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml'**

### Gradle installation
Durch diesen Guide habe ich Gradle installiert und das Repo mit  *gradle clean* geputzt und mit *gradle bootRun* unteranderem einen Webserver gestartet.

### Lösungsansatz

In der Angabe habe ich diese Zeilen in den Code eingefügt und *gradle bootRun* nocheinmals gestartet. Doch ich musste allerdings MediaType importieren. 
- *import org.springframework.http.MediaType;*

#### JSON
```java
@RequestMapping(value="/timingstation/{timingstationID}/data", produces = MediaType.APPLICATION_JSON_VALUE)  
public TimingstationData timingstationData( @PathVariable String timingstationID ) {  
    return service.getTimingstationData( timingstationID );  
}
```

#### XML
```java
@RequestMapping(value="/timingstation/{timingstationID}/xml", produces = MediaType.APPLICATION_XML_VALUE)  
public TimingstationData timingstationDataXML( @PathVariable String timingstationID ) {  
    return service.getTimingstationData( timingstationID );  
}
```

Man muss Party Objekte generieren und dies macht man in **TimingstationSimulation** . Als erstes habe ich eine Klasse Party erstellt die diese Werte als Attribute speichert und so diese Datenstruktur eines Partyobjekts darstellt.
```java
public class Party {  
    private int partyID;  
    private String timing;  
  
    public void setPartyID(int partyID){  
        this.partyID = partyID;  
    }  
    public int getPartyID(){  
        return this.partyID;  
    }  
    public void setTiming(String timing){  
        this.timing = timing;  
    }  
    public String getTiming(){  
        return this.timing;  
    }  
}
```
Danach generiert man mit den gegebenen Methoden in **TimingstationSimulation** die Werte der Attribute eines Partyobjekts. Diese Objekt füllt man dann in ein **TimingstationData** Objekt, dass wiederum ein CompetitionData Objekt besitzt, das Partyobjekte in einer Arraylist speichert.
```java 
ArrayList<Party> compParty = new ArrayList<Party>();  
for(int i =0;i<partyAnzahl ;i++){  
   Party a1 = new Party();  
   a1.setTiming(new SimpleDateFormat("hh:mm:ss").format(new Date()));  
   a1.setPartyID(getRandomInt(1000,2000));  
   compParty.add(a1);  
}

compData.setParty(compParty);  
data.setCompetitionData(compData);  
return data;
```


## Quellen
