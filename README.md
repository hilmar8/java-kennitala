Validation and generation library for the Icelandic Kennitala
==============

## Getting Started

### Maven
```xml
<dependency>
    <groupId>net.hilmarh</groupId>
    <artifactId>kennitala</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Usage

The library has two classes, `KennitalaUtil` and `KennitalaValidator`.

#### Validating a kennitala

Validation of a kennitala using Number Derivation (vartölupróf) http://en.wikipedia.org/wiki/Kennitala#Number_derivation

```java
String kennitala = "000000-0000";

// The validation will take care of the hyphen.
boolean kennitalaValid = KennitalaValidator.isValid(kennitala);
```

#### Calculating the age of a kennitala
```java
String kennitala = "0000000000";

int age = KennitalaUtil.age(kennitala);
```

#### Creating a random kennitala
```java
String kennitala = KennitalaUtil.random();
```

#### Creating a kennitala from a birthdate
```java
String kennitala = KennitalaUtil.fromBirthday(1, 2, 1982); // dd MM yyyy
```

## License
Copyright (c) 2014 hilmarh
Licensed under the MIT license.