# Hyde
Mr. Hyde - script for testing internal links 

Clone:
```bash
git clone https://github.com/writeonly/hyde.git
cd hyde
```

Refactor and reformat:
```bash
sbt scalafix test:scalafix it:scalafix &&
sbt scalafmtSbt scalafmt test:scalafmt it:scalafmt
```

Check lint and format:
```bash
sbt 'hyde/scalafix --check' 'hyde/test:scalafix --check' 'hyde/it:scalafix --check' &&
sbt scalafmtSbtCheck hyde/scalafmtCheck hyde/test:scalafmtCheck hyde/it:scalafmtCheck
```

Compile, test and generate coverage report:
```bash
sbt clean compile test:compile it:compile &&
sbt coverage hyde/test hyde/it:test coverageReport &&
sbt coverageAggregate
```

Check:
```bash
sbt scalastyle test:scalastyle it:scalastyle &&
sbt scapegoat cpd stats
```

All:
```bash
sbt hyde/scalafix hyde/test:scalafix hyde/it:scalafix &&
sbt hyde/scalafmtSbt hyde/scalafmt hyde/test:scalafmt hyde/it:scalafmt &&
sbt clean hyde/compile hyde/test:compile hyde/it:compile hyde/test &&
sbt coverage hyde/test hyde/it:test coverageReport &&
sbt coverageAggregate &&
sbt scalastyle test:scalastyle it:scalastyle &&
sbt scapegoat cpd stats
```

Run:
```bash
sbt hyde/run
```
