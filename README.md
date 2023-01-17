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
## Quellen
