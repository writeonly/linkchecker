# LinkChecker
Link Checker - script for testing internal links 

Clone:
```bash
git clone https://github.com/writeonly/linkchecker.git
cd linkchecker
```

Refactor and reformat:
```bash
sbt scalafix test:scalafix it:scalafix &&
sbt scalafmtSbt scalafmt test:scalafmt it:scalafmt
```

Check lint and format:
```bash
sbt 'linkchecker/scalafix --check' 'linkchecker/test:scalafix --check' 'linkchecker/it:scalafix --check' &&
sbt scalafmtSbtCheck linkchecker/scalafmtCheck linkchecker/test:scalafmtCheck linkchecker/it:scalafmtCheck
```

Compile, test and generate coverage report:
```bash
sbt clean compile test:compile it:compile &&
sbt coverage linkchecker/test linkchecker/it:test coverageReport &&
sbt coverageAggregate
```

Check:
```bash
sbt scalastyle test:scalastyle it:scalastyle &&
sbt scapegoat cpd stats
```

All:
```bash
sbt linkchecker/scalafix linkchecker/test:scalafix linkchecker/it:scalafix &&
sbt linkchecker/scalafmtSbt linkchecker/scalafmt linkchecker/test:scalafmt linkchecker/it:scalafmt &&
sbt clean linkchecker/compile linkchecker/test:compile linkchecker/it:compile linkchecker/test &&
sbt coverage linkchecker/test linkchecker/it:test coverageReport &&
sbt coverageAggregate &&
sbt scalastyle test:scalastyle it:scalastyle &&
sbt scapegoat cpd stats
```

Run:
```bash
sbt linkchecker/run
```
