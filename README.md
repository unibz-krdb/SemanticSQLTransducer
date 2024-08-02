# Semantic SQL Transducer

Based on over a decade of rigorous theoretical research, the *Semantic SQL Transducer* is a well-founded but practical tool providing the materialised lossless conceptual view of an arbitrary relational source data, contributing to a knowledge-centric data stack ([paper](https://arxiv.org/abs/2407.07502), [presentation](https://www.youtube.com/watch?v=L2uwlsEG8ZE)).

*DISCLAIMER: This repository is in its infancy and as such all builds should be regarded as unstable.*

## Usage

**Requirements**

- [Java 21](https://openjdk.org/projects/jdk/21/)
- [Gradle 8.9](https://gradle.org/install/)

**Build**

``` sh
gradle build
```

**Test**

Testing requires a running [postgres](https://www.postgresql.org/) instance containing an empty database *ssqlt_test*.

``` sh
gradle test
```

## License

This project is licensed under [LGPLv2.1](https://github.com/unibz-krdb/SemanticSQLTransducer/blob/main/LICENSE).

## Contribution

We greatly look forward to accepting external contributions, but the project is currently too immature. 

Watch this space!

