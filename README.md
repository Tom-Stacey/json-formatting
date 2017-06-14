# Json formatting
  
This is an example project showing various ways to read and write Json to/from case classes using Scala Play. To compile and run tests, just run:
```sh
$ sbt test
```
from the repo's root directory.
  
You can see different stages of complecity in Reads/Writes by checking out to branches named `step-1` to `step-9`. They deal with:
  - `master/step-1` basic Format for one-to-one Json/case class mapping
  - `step-2/step-3` Formatting with different named fields between Json and the case class
  - `step-4/step-5` Reading a specifically formatted date from Json to a Java LocalDate in the case class
  - `step-6/step-7` Validating a case class accross multiple fields within a Json Reads (and test helper code to simplify checking for validation errors)
  - `step-8/step-9` Validating a single field within a Json Reads
  
